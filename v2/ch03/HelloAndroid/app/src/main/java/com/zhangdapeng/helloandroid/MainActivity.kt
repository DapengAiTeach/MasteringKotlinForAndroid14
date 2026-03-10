package com.zhangdapeng.helloandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.zhangdapeng.helloandroid.ui.theme.HelloAndroidTheme

/**
 * 主 Activity - 应用入口
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HelloAndroidTheme {
                ConstraintLayoutDemoScreen()
            }
        }
    }
}

/**
 * ============================================
 * ConstraintLayout 约束布局演示
 * ============================================
 * 
 * ConstraintLayout 是一种灵活的布局方式
 * 特点：通过约束（Constraint）来定位子组件
 * 
 * 与传统布局对比：
 * - LinearLayout/Column/Row：按顺序排列
 * - ConstraintLayout：通过相对位置关系定位
 * 
 * 适用场景：
 * - 复杂布局，减少嵌套层级
 * - 需要相对定位（如A在B的左边）
 * - 需要百分比布局
 */
@Composable
fun ConstraintLayoutDemoScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        /**
         * ============================================
         * ConstraintLayout 容器
         * ============================================
         * 
         * 需要导入依赖：androidx.constraintlayout:constraintlayout-compose
         */
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            /**
             * ============================================
             * 第1步：创建引用（References）
             * ============================================
             * 
             * createRefs() 创建多个引用
             * 这些引用用于在约束中标识组件
             * 
             * 相当于给每个组件起个名字，方便互相引用
             */
            val (title, avatar, username, description, buttonLeft, buttonRight) = createRefs()
            
            /**
             * ============================================
             * 第2步：标题（顶部居中）
             * ============================================
             * 
             * constrainAs(title) { ... } 为这个组件应用约束
             */
            Text(
                text = "ConstraintLayout 约束布局",
                style = MaterialTheme.typography.headlineSmall,
                /**
                 * Modifier.constrainAs 是核心
                 * 在 lambda 中定义约束规则
                 */
                modifier = Modifier.constrainAs(title) {
                    /**
                     * top.linkTo(parent.top) 
                     * 表示这个组件的顶部与父容器的顶部对齐
                     */
                    top.linkTo(parent.top, margin = 32.dp)
                    /**
                     * start.linkTo(parent.start)
                     * end.linkTo(parent.end)
                     * 左右都约束到父容器，实现水平居中
                     */
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )
            
            /**
             * ============================================
             * 第3步：头像（圆形）
             * ============================================
             * 
             * 约束：在标题下方，左侧
             */
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF42A5F5))
                    .constrainAs(avatar) {
                        /**
                         * 顶部约束到 title 的底部
                         * margin = 32.dp 表示间距 32dp
                         */
                        top.linkTo(title.bottom, margin = 32.dp)
                        /**
                         * 左侧约束到父容器左侧
                         */
                        start.linkTo(parent.start)
                    }
            )
            
            /**
             * ============================================
             * 第4步：用户名
             * ============================================
             * 
             * 约束：在头像右侧，顶部与头像对齐
             */
            Text(
                text = "张三",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.constrainAs(username) {
                    /**
                     * 顶部与 avatar 顶部对齐
                     */
                    top.linkTo(avatar.top)
                    /**
                     * 左侧约束到 avatar 的右侧
                     * margin = 16.dp 表示间距
                     */
                    start.linkTo(avatar.end, margin = 16.dp)
                    /**
                     * 右侧约束到父容器右侧
                     * 这样文字可以占满剩余空间
                     */
                    end.linkTo(parent.end)
                    /**
                     * 宽度填充约束范围内的空间
                     * fillToConstraints = match_constraint
                     */
                    width = Dimension.fillToConstraints
                }
            )
            
            /**
             * ============================================
             * 第5步：描述文字
             * ============================================
             * 
             * 约束：在用户名下方，左侧与用户名对齐
             */
            Text(
                text = "Android 开发工程师，热爱编程，喜欢探索新技术。这是我的个人简介。",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                modifier = Modifier.constrainAs(description) {
                    /**
                     * 顶部约束到 username 的底部
                     */
                    top.linkTo(username.bottom, margin = 8.dp)
                    /**
                     * 左右都与 username 对齐
                     */
                    start.linkTo(username.start)
                    end.linkTo(username.end)
                    /**
                     * 宽度填充约束范围
                     */
                    width = Dimension.fillToConstraints
                }
            )
            
            /**
             * ============================================
             * 第6步：底部按钮 - 左
             * ============================================
             * 
             * 约束：在父容器底部左侧
             */
            Button(
                onClick = { },
                modifier = Modifier.constrainAs(buttonLeft) {
                    /**
                     * 底部约束到父容器底部
                     */
                    bottom.linkTo(parent.bottom, margin = 32.dp)
                    /**
                     * 左侧约束到父容器左侧
                     */
                    start.linkTo(parent.start)
                    /**
                     * 右侧约束到 buttonRight 的左侧
                     * 实现两个按钮并排，中间有间距
                     */
                    end.linkTo(buttonRight.start, margin = 16.dp)
                    /**
                     * 宽度填充两个按钮之间的空间
                     */
                    width = Dimension.fillToConstraints
                }
            ) {
                Text("取消")
            }
            
            /**
             * ============================================
             * 第7步：底部按钮 - 右
             * ============================================
             * 
             * 约束：在父容器底部右侧
             */
            Button(
                onClick = { },
                modifier = Modifier.constrainAs(buttonRight) {
                    /**
                     * 底部与 buttonLeft 对齐
                     */
                    bottom.linkTo(buttonLeft.bottom)
                    /**
                     * 右侧约束到父容器右侧
                     */
                    end.linkTo(parent.end)
                    /**
                     * 宽度与 buttonLeft 相同（都是 fillToConstraints）
                     */
                    width = Dimension.fillToConstraints
                }
            ) {
                Text("确认")
            }
        }
    }
}

/**
 * ============================================
 * 预览函数
 * ============================================
 */
@Preview(showBackground = true)
@Composable
fun ConstraintLayoutDemoScreenPreview() {
    HelloAndroidTheme {
        ConstraintLayoutDemoScreen()
    }
}
