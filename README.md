# CornerViewGroup 圆角容器
实现了CornerConstraintLayout和CornerFragmeLayout两个自定义的ViewGroup。不管其中的子view如何布局，都可以自由设定带有圆角的整体布局。
如果设置宽高相等，圆角半径为宽高的一半，那整个布局就是圆形

#### 1.特点
###### 1.易用：  
不会影响原来的布局，只需要替换对应的ViewGroup,就可以使用带有圆角的布局。。
###### 2.易扩展：  
目前针对了ConstraintLayout和FragmentLayout实现了CornerContraintLayout CornerFrameLay头，如果想实现基于其他的VeiwGroup如LinearLayout，RelativeLayout的控件，完全复用CornerContrainerLayout的代码，只需要把对应的继承类ConstaintLayout替换成LinearLayout或者RelativeLayout。


#### 2.使用：有两种方式
##### 1.直接通过jcenter引用
如果项目是androidx 
```java
implementation 'com.leaf:corner:1.1.0'
implementation 'androidx.constraintlayout:constraintlayout:1.1.3'
```
如果不是androidx项目
```java
implementation 'com.leaf:corner:1.0.0'
implementation 'com.android.support.constraint:constraint-layout:1.1.3'
```

##### 2.引入布局，只需要简单的两步
###### 1.直接拷贝CornerConstraintLayout类或者CornerFrameLayout类
###### 2.拷贝attrs文件里面的属性到自己的attrs文件中  
```java
<declare-styleable name="CustomCorner">
        <attr name="topRightRadius" format="dimension" />
        <attr name="topLeftRadius" format="dimension" />
        <attr name="bottomRightRadius" format="dimension" />
        <attr name="bottomLeftRadius" format="dimension" />
        <attr name="strokeWidth" format="dimension" />
        <attr name="strokeColor" format="color" />
        <attr name="radius" format="dimension" />
    </declare-styleable>
```

#### 3.提供的功能，可以在代码中动态的设置圆角或者在布局文件中设置 在第四部分会有对应的使用实例：
1.直接定义四个角的半径  
2.设置为圆形布局  
3.分别定义每个圆角的半径  
4.设置边的颜色和宽度。比如可以设置整体布局有10dp的半透明的边  

#### 4.使用实例：
##### 1.在代码中设置例子  cornerConstraintLayout.setCornderRadius(30, 0, 40, 0);
```java
 private CornerConstraintLayout cornerConstraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cornerConstraintLayout = findViewById(R.id.corner_layout);
        findViewById(R.id.modify_bnt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cornerConstraintLayout.setCornderRadius(30, 0, 40, 0);
            }
        });
    }
 ```
 
##### 2.在布局文件中使用，使用上面的atts文件中的属性可以单独设置任意位置的圆角 

###### 1.设置四个圆角
```java
<com.leaf.corner.CornerConstraintLayout
  android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:radius="20dp"
    app:layout_constraintLeft_toLeftOf="parent"
    app:layout_constraintRight_toRightOf="parent">

    <ImageView
        android:id="@+id/img"
        android:layout_width="300dp"
        android:layout_height="300dp"
        android:layout_gravity="center"
        android:scaleType="centerInside"
        android:src="@mipmap/beautiful_img"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <ImageView
        android:layout_width="300dp"
        android:layout_height="200dp"
        android:layout_gravity="bottom"
        android:scaleType="centerCrop"
        android:src="@mipmap/test1_image"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/img"/>
</com.leaf.corner.CornerConstraintLayout>
```
<img src="https://github.com/redleaf2002/CornerViewGroup/blob/master/88a419bdc21a6db0a4985e031.jpg" width="350" />

######  2.设置圆形，布局的高宽相等，圆角半径为高宽的一半
```java
<com.leaf.corner.CornerConstraintLayout
    android:layout_width="300dp"
    android:layout_height="300dp"
    app:layout_constraintLeft_toLeftOf="parent"
    app:layout_constraintRight_toRightOf="parent"
    app:radius="150dp">
    <ImageView
        android:id="@+id/img"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:scaleType="centerInside"
        android:src="@mipmap/beautiful_img"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent" />
</com.leaf.corner.CornerConstraintLayout>

```
<img src="https://github.com/redleaf2002/CornerViewGroup/blob/master/d4117c8d9cb06ccb710971bac.png" width="350" />

######  3.设置部分圆角，比如整体布局左上和右下有圆角为20dp
```java
<com.leaf.corner.CornerFrameLayout
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:bottomRightRadius="20dp"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:topLeftRadius="20dp">

        <ImageView
            android:id="@+id/img"
            android:layout_width="300dp"
            android:layout_height="300dp"
            android:layout_gravity="center"
            android:scaleType="centerInside"
            android:src="@mipmap/beautiful_img"
            app:layout_constraintLeft_toLeftOf="parent"
            app:layout_constraintRight_toRightOf="parent"
            app:layout_constraintTop_toTopOf="parent"/>

        <ImageView
            android:layout_width="300dp"
            android:layout_height="200dp"
            android:layout_gravity="bottom"
            android:scaleType="centerCrop"
            android:src="@mipmap/test1_image"
            app:layout_constraintLeft_toLeftOf="parent"
            app:layout_constraintRight_toRightOf="parent"
            app:layout_constraintTop_toBottomOf="@+id/img"/>
    </com.leaf.corner.CornerFrameLayout>
```
  <img src="https://github.com/redleaf2002/CornerViewGroup/blob/master/1ef68975d48e7940a25f5e2b3.jpg" width="350" />
  
  ######  4.设置透明边，比如整体布局有有半透明20dp的边
```java
  <com.leaf.corner.CornerConstraintLayout
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:layout_constraintLeft_toLeftOf="parent"
    app:layout_constraintRight_toRightOf="parent"
    app:strokeColor="#80ffffff"
    app:strokeWidth="20dp">

    <ImageView
        android:id="@+id/img"
        android:layout_width="300dp"
        android:layout_height="300dp"
        android:layout_gravity="center"
        android:scaleType="centerInside"
        android:src="@mipmap/beautiful_img"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <ImageView
        android:layout_width="300dp"
        android:layout_height="200dp"
        android:layout_gravity="bottom"
        android:scaleType="centerCrop"
        android:src="@mipmap/test1_image"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/img" />

</com.leaf.corner.CornerConstraintLayout>
 ```
<img src="https://github.com/redleaf2002/CornerViewGroup/blob/master/19b86645d749f3f125a0639f4.jpg" width="350" />
