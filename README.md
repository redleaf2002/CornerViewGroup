# CornerViewGroup
自定义可以自由设定每个圆角的CornerConstraintLayout和CornerFragmeLayout，如果想继承其他任何的ViewGroup，只需要拷贝CornerConstraintLayout或者CornerFrameLayout这个类，然后继承改为自己需要的ViewGroup

##### 1.引用 分两步
###### 1.直接拷贝CornerConstraintLayout和CornerFragmeLayout这两个类
###### 2.拷贝atts文件里面的属性 
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


##### 2.使用：直接在布局文件中使用，使用上面的属性可以单独设置任意位置的圆角 设置四个圆角

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
</com.leaf.customviewgroup.CornerConstraintLayout>


##### 3.左上和右下有圆角

<com.leaf.customviewgroup.CornerFrameLayout
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
    </com.leaf.customviewgroup.CornerFrameLayout>
