package com.ashutosh.socialhub.dao;

import java.util.List;

import com.ashutosh.socialhub.domain.Friend;

public interface FriendDAO {
	public boolean save(Friend friend);
	public boolean update(Friend friend);
	public boolean delete(Friend friend);
	public Friend get(int id);
	public List<Friend> list();

}
