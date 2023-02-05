package io.modicon.userservice.service;

import io.modicon.ekatalogservice.api.dto.UrlDto;
import io.modicon.userservice.api.dto.AddItemToUser;
import io.modicon.userservice.api.dto.ItemWithPricesDto;
import io.modicon.userservice.api.dto.UserDto;
import io.modicon.userservice.client.EKatalogClient;
import io.modicon.userservice.exception.ApiException;
import io.modicon.userservice.model.ItemEntity;
import io.modicon.userservice.model.UserEntity;
import io.modicon.userservice.model.UserItemId;
import io.modicon.userservice.model.UserItemPriceEntity;
import io.modicon.userservice.repository.ItemRepository;
import io.modicon.userservice.repository.UserItemRepository;
import io.modicon.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final UserItemRepository userItemRepository;
    private final EKatalogService eKatalogService;

    @Override
    @Transactional
    public UserDto createUser(UserDto user) {
        Optional<UserEntity> userExisting = userRepository.findById(user.userId());
        if (userExisting.isPresent()) return UserMapper.mapToDto(userExisting.get());

        UserEntity savedUser = userRepository.save(UserMapper.mapToEntity(user));
        log.info("save user {}", savedUser);

        return UserMapper.mapToDto(savedUser);
    }

    @Override
    @Transactional
    public UserDto deleteUser(String userId) {
        Optional<UserEntity> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            userRepository.deleteById(userId);
            UserEntity deletedUser = optionalUser.get();
            log.info("delete user {}", deletedUser);
            return UserMapper.mapToDto(deletedUser);
        }
        log.info("user with id {} not found", userId);
        throw ApiException.exception(HttpStatus.NOT_FOUND, "user with id %s not found", userId);
    }

    @Override
    @Transactional
    public ItemWithPricesDto addItemToUser(AddItemToUser userItem, String userId) {
        Optional<UserEntity> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            log.info("user with id {} not found", userId);
            throw ApiException.exception(HttpStatus.NOT_FOUND, "user with id %s not found", userId);
        }
        UserEntity user = optionalUser.get();

        String itemUrl = userItem.itemUrl;
        UserItemId userItemId = new UserItemId(userId, itemUrl);
        ItemEntity item;
        Optional<ItemEntity> optionalItem = itemRepository.findById(itemUrl);
        if (optionalItem.isEmpty()) {
            var itemsWithPrices = eKatalogService.getItemsFromEKatalog(itemUrl);
            item = itemRepository.save(ItemMapper.mapToEntity(itemsWithPrices.getItem(), itemsWithPrices.getShops()));
        } else {
            item = optionalItem.get();
        }

        userItemRepository.save(new UserItemPriceEntity(
                userItemId,
                user,
                item,
                userItem.price
        ));

        return ItemMapper.mapToDto(item);
    }

    @Override
    @Transactional
    public void deleteUsersItem(String itemUrl, String userId) {
        Optional<UserEntity> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            log.info("user with id {} not found", userId);
            throw ApiException.exception(HttpStatus.NOT_FOUND, "user with id %s not found", userId);
        }

        Optional<ItemEntity> optionalItem = itemRepository.findById(itemUrl);
        if (optionalItem.isEmpty()) {
            log.info("item with id {} not found", itemUrl);
            throw ApiException.exception(HttpStatus.NOT_FOUND, "item with id %s not found", itemUrl);
        }

        UserItemId userItemId = new UserItemId(userId, itemUrl);
        Optional<UserItemPriceEntity> optionalUserItemPrice = userItemRepository.findById(userItemId);
        if (optionalUserItemPrice.isEmpty()) {
            throw ApiException.exception(HttpStatus.NOT_FOUND, "user with id %s does not have item with id %s", userId, itemUrl);
        }
        userItemRepository.deleteById(userItemId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ItemWithPricesDto> getUserItems(String userId) {
        Optional<UserEntity> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            log.info("user with id {} not found", userId);
            throw ApiException.exception(HttpStatus.NOT_FOUND, "user with id %s not found", userId);
        }

        List<UserItemPriceEntity> userItems = userItemRepository.findByUser(optionalUser.get());

        return userItems.stream().map(ut -> ItemMapper.mapToDto(ut.getItem())).toList();
    }
}
