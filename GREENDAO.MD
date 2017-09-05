### 配置

##### project
    dependencies {
            classpath 'org.greenrobot:greendao-gradle-plugin:3.2.2' //greendao
        }

##### module
        apply plugin: 'org.greenrobot.greendao'

        android {
            greendao {
                //version
                schemaVersion 1
                }
        }

        dependencies {
            compile 'org.greenrobot:greendao:3.2.2'
        }

---

### 使用

##### 创建Bean对象（表名和字段名）

GreenDao需要创建Bean对象之后，该Bean对象就是表名，而它的属性值就是字段名，其实现是通过注释的方式来实现

    @Entity
    public class Shop {
        public static final int TYPE_CART = 0x01;
        public static final int TYPE_ORDER = 0x01;

        @Id(autoincrement = true) //自增主键
        private Long id;
        @Unique //商品名称(唯一)
        private String name;
        @Property(nameInDb = "price") //自定义key
        private String price;
        //已售数量
        private int sell_num;
        //图标url
        private String image_url;
        //商家地址
        private String address;
        //商品列表类型
        private int type;
    }

1. @Entity：告诉GreenDao该对象为实体，只有被@Entity注释的Bean类才能被dao类操作
2. @Id：对象的Id，使用Long类型作为EntityId，否则会报错。(autoincrement = true)表示主键会自增，如果false就会使用旧值
3. @Property：自定义字段名，注意外键不能使用该属性
4. @NotNull：属性不能为空
5. @Transient：使用该注释的属性不会被存入数据库的字段中
6. @Unique：该属性值必须在数据库中是唯一值
7. @Generated：编译后自动生成的构造函数、方法等的注释，提示构造函数、方法等不能被修改

写好实体后,`build gradle` | `make project(ctrl + F9)`,自动生成代码

---

##### 创建数据库

    public class BaseApplication extends Application{

        private static DaoSession daoSession;

        @Override
        public void onCreate() {
            super.onCreate();
            setupDatabase("shop.db");
        }

        /**
         * setupDatabase
         */
        private void setupDatabase(String name) {
            DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, name, null);
            SQLiteDatabase db = helper.getWritableDatabase();
            DaoMaster daoMaster = new DaoMaster(db);
            daoSession = daoMaster.newSession();
        }

        public static DaoSession getDaoInstant() {
            return daoSession;
        }
    }

1. DevOpenHelper：创建SQLite数据库的SQLiteOpenHelper的具体实现
2. DaoMaster：GreenDao的顶级对象，作为数据库对象、用于创建表和删除表
3. DaoSession：管理所有的Dao对象，Dao对象中存在着增删改查等API

---

##### 数据库的增删改查

    public class CartDao {

        /**
         * insert
         *
         * @param shop
         */
        public static void insertCart(Shop shop) {
            MyApp.getDaoInstant().getShopDao().insert(shop);
        }

        /**
         * delete
         *
         * @param id
         */
        public static void deleteCart(long id) {
            MyApp.getDaoInstant().getShopDao().deleteByKey(id);
        }

        /**
         * update
         *
         * @param shop
         */
        public static void updateLove(Shop shop) {
            MyApp.getDaoInstant().getShopDao().update(shop);
        }

        /**
         * quey Type=TYPE_CART
         *
         * @return
         */
        public static List<Shop> queryCart() {
            return MyApp.getDaoInstant().getShopDao().queryBuilder().where(ShopDao.Properties.Type.eq(Shop.TYPE_CART)).list();
        }

        /**
         * queryAll
         */
        public static List<Shop> queryAll() {
            return MyApp.getDaoInstant().getShopDao().loadAll();
        }
    }

---

##### API

1. 增加单个数据
    ```
    ..getShopDao().insert(shop);
    ..getShopDao().insertOrReplace(shop);
    ```
2. 增加多个数据
    ```
    ..getShopDao().insertInTx(shopList);
    ..getShopDao().insertOrReplaceInTx(shopList);
    ```
3. 查询全部
    ```
    List< Shop> list = ..getShopDao().loadAll();
    List< Shop> list = ..getShopDao().queryBuilder().list();
    ```
4. 查询附加单个条件
    ```
    ..getShopDao().queryBuilder().where()
    ..getShopDao().queryBuilder().whereOr()
    ```
5. 查询附加多个条件
    ```
    ..getShopDao().queryBuilder().where(...)
    ..getShopDao().queryBuilder().whereOr(...)
    ```
6. 查询附加排序
    ```
    ..getShopDao().queryBuilder().orderDesc()
    ..getShopDao().queryBuilder().orderAsc()
    ```
7. 查询限制当页个数
    ```
    ..getShopDao().queryBuilder().limit()
    ```
8. 查询总个数
    ```
    ..getShopDao().queryBuilder().count()
    ```
9. 修改单个数据
    ```
    ..getShopDao().update(shop);
    ```
10. 修改多个数据
    ```
    ..getShopDao().updateInTx(shopList);
    ```
11. 删除单个数据
    ```
    ..getShopDao().delete(user);
    ```
12. 删除多个数据
    ```
    ..getShopDao().deleteInTx(userList);
    ```
13. 删除数据ByKey
    ```
    ..getShopDao().deleteByKey();
    ```

---

添加实体类的属性的时候，直接添加，然后在gradle升级一下schemaVersion











