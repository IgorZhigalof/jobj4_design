package ru.job4j.generic;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RoleStoreTest {
    @Test
    void whenAddARoleIsSuccessful() {
        Store<Role> store = new RoleStore();
        store.add(new Role("1", "King"));
        assertThat(store.findById("1").getRoleName()).isEqualTo("King");
    }

    @Test
    void whenTryToOverrideByAddIsUnsuccessful() {
        Store<Role> store = new RoleStore();
        store.add(new Role("1", "King"));
        store.add(new Role("1", "Queen"));
        assertThat(store.findById("1").getRoleName()).isEqualTo("King");
    }

    @Test
    void whenTryToOverrideByReplaceIsSuccessful() {
        Store<Role> store = new RoleStore();
        store.add(new Role("1", "King"));
        assertThat(store.replace("1", new Role("1", "Queen"))).isTrue();
        assertThat(store.findById("1").getRoleName()).isEqualTo("Queen");
    }

    @Test
    void whenTryToReplaceNonExistRole() {
        Store<Role> store = new RoleStore();
        store.add(new Role("1", "King"));
        assertThat(store.replace("10", new Role("1", "Queen"))).isFalse();
        assertThat(store.findById("1").getRoleName()).isEqualTo("King");
    }

    @Test
    void whenDeleteIsSuccessful() {
        Store<Role> store = new RoleStore();
        store.add(new Role("1", "King"));
        store.delete("1");
        assertThat(store.findById("1")).isNull();
    }

    @Test
    void whenTryToDeleteNonExistRole() {
        Store<Role> store = new RoleStore();
        store.add(new Role("1", "King"));
        store.delete("10");
        assertThat(store.findById("1").getRoleName()).isEqualTo("King");
        assertThat(store.findById("10")).isNull();
    }

    @Test
    void whenTryToFindNonExistRole() {
        Store<Role> store = new RoleStore();
        store.add(new Role("1", "King"));
        assertThat(store.findById("10")).isNull();
    }
}