package com.example.mazeapp.Data;
import java.util.ArrayList;
import java.util.List;

public interface RepositoryContract {

    interface FetchMazeListDataCallback {
        void onAppDataFetched(boolean error);
    }

    interface GetMazeListCallback {
        void setMazeList(List<MazeListItem> mazeList);
    }

    interface GetMazeListUsedIdsCallback {
        void onMazeListUsedIdsRecived(ArrayList<Integer> ids);
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

    void loadMazeList(boolean clearFirst, AppRepository.FetchMazeListDataCallback callback);

    void getMazeList(AppRepository.GetMazeListCallback callback);

    void getMazeListUsedIds(AppRepository.GetMazeListUsedIdsCallback callback);

    void getMazeListItem(int id, AppRepository.GetMazeListItemCallback callback);

    void deleteMazeListItem(MazeListItem item, AppRepository.DeleteMazeListItemCallback callback);

    void updateMazeListItem(MazeListItem item, AppRepository.UpdateMazeListItemCallback callback);

    void addMazeListItem(MazeListItem item, AppRepository.AddMazeListItemCallback callback);
}
