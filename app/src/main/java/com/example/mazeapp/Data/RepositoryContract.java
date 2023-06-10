package com.example.mazeapp.Data;
import java.util.List;

public interface RepositoryContract {

    interface FetchMazeListDataCallback {
        void onAppDataFetched(boolean error);
    }

    /*interface GetProductListCallback {
        void setProductList(List<ProductItem> products);
    }

    interface GetProductCallback {
        void setProduct(ProductItem product);
    }*/

    interface GetMazeListCallback {
        void setMazeList(List<MazeListItem> mazeList);
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

    /*interface DeleteProductCallback {
        void onProductDeleted();
    }

    interface UpdateProductCallback {
        void onProductUpdated();
    }*/


    void loadMazeList(boolean clearFirst, AppRepository.FetchMazeListDataCallback callback);

    //void getProductList(CategoryItem category, CatalogRepository.GetProductListCallback callback);
    //void getProductList(int categoryId, CatalogRepository.GetProductListCallback callback);

    //void getProduct(int id, CatalogRepository.GetProductCallback callback);

    void getMazeListItem(int id, AppRepository.GetMazeListItemCallback callback);

    void getMazeList(AppRepository.GetMazeListCallback callback);

    //void deleteProduct(ProductItem product, CatalogRepository.DeleteProductCallback callback);

    //void updateProduct(ProductItem product, CatalogRepository.UpdateProductCallback callback);

    void deleteMazeListItem(MazeListItem category, AppRepository.DeleteMazeListItemCallback callback);

    void updateMazeListItem(MazeListItem item, AppRepository.UpdateMazeListItemCallback callback);
}
