package com.example.mazeapp.Data;
import java.util.ArrayList;
import java.util.List;

public interface RepositoryContract {

    //--------------------------- APP ---------------------------
    interface FetchAppDataCallback {
        void onAppDataFetched(boolean error);
    }

    void loadAppData(boolean clearFirst, FetchAppDataCallback callback);

    //--------------------------- MAZE LIST ---------------------------

    interface GetMazeListCallback {
        void setMazeList(List<MazeListItem> mazeList);
    }
    interface GetUnusedMazeListItemIdCallback {
        void onGetUnusedMazeListItemIdCallback(int id);
    }
    interface GetMazeListItemCallback {
        void setMazeListItem(MazeListItem item);
    }
    interface DeleteMazeListItemCallback {
        void onMazeListItemDeleted();
    }
    interface UpdateMazeListItemCallback {
        void onMazeListItemUpdated();
    }
    interface AddMazeListItemCallback {
        void onMazeListItemAdded();
    }

    void getMazeList(AppRepository.GetMazeListCallback callback);
    void getUnusedMazeListItemId(AppRepository.GetUnusedMazeListItemIdCallback callback);
    void getMazeListItem(int id, AppRepository.GetMazeListItemCallback callback);
    void deleteMazeListItem(MazeListItem item, AppRepository.DeleteMazeListItemCallback callback);
    void updateMazeListItem(MazeListItem item, AppRepository.UpdateMazeListItemCallback callback);
    void addMazeListItem(MazeListItem item, AppRepository.AddMazeListItemCallback callback);

    //--------------------------- USER LIST ---------------------------

    interface GetUnusedUserIdCallback {
        void onGetUnusedUserIdCallback(int id);
    }
    interface GetUserListCallback {
        void onGetUserList(List<UserItem> userList);
    }
    interface AddUserItemCallback {
        void onMazeListItemAdded();
    }

    void getUnusedUserId(AppRepository.GetUnusedUserIdCallback callback);
    void getUserList(AppRepository.GetUserListCallback callback);
    void addUserItem(UserItem item, AppRepository.AddUserItemCallback callback);

}
