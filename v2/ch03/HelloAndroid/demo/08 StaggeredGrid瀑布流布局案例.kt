package com.zhangdapeng.helloandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zhangdapeng.helloandroid.ui.theme.HelloAndroidTheme

/**
 * 主 Activity - 应用入口
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 启用全面屏显示
        enableEdgeToEdge()
        setContent {
            HelloAndroidTheme {
                StaggeredGridDemoScreen()
            }
        }
    }
}

/**
 * ============================================
 * 第1步：定义数据类（数据模型）
 * ============================================
 * 
 * @param id 唯一标识符
 * @param title 标题
 * @param height 高度（dp），用于演示交错布局的不同高度
 * @param color 背景颜色
 */
data class StaggeredItem(
    val id: String,
    val title: String,
    val height: Int,  // 高度不同，形成交错效果
    val color: Color
)

/**
 * ============================================
 * 第2步：准备模拟数据
 * ============================================
 * 
 * 注意：每个项目的高度不同（80-200dp）
 * 这是形成交错网格（瀑布流）效果的关键
 */
fun getSampleStaggeredItems(): List<StaggeredItem> = listOf(
    StaggeredItem("1", "短视频", 120, Color(0xFFEF5350)),
    StaggeredItem("2", "摄影", 80, Color(0xFF66BB6A)),
    StaggeredItem("3", "旅行", 160, Color(0xFF42A5F5)),
    StaggeredItem("4", "美食", 100, Color(0xFFFFA726)),
    StaggeredItem("5", "穿搭", 140, Color(0xFFAB47BC)),
    StaggeredItem("6", "家居", 90, Color(0xFF26A69A)),
    StaggeredItem("7", "健身", 180, Color(0xFFEC407A)),
    StaggeredItem("8", "读书", 110, Color(0xFF7E57C2)),
    StaggeredItem("9", "音乐", 130, Color(0xFF29B6F6)),
    StaggeredItem("10", "绘画", 170, Color(0xFF66BB6A)),
    StaggeredItem("11", "手工", 95, Color(0xFFFF7043)),
    StaggeredItem("12", "园艺", 150, Color(0xFF8D6E63))
)

/**
 * ============================================
 * 第3步：交错网格界面（主屏幕）
 * ============================================
 * 
 * Staggered Grid（瀑布流布局）特点：
 * - 每列可以有不同的项目高度
 * - 下一行会自动填充到最短的那一列
 * - 常用于图片墙、商品展示等场景
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StaggeredGridDemoScreen() {
    
    // 准备数据
    val items = getSampleStaggeredItems()
    
    Scaffold(
        // 顶部标题栏
        topBar = {
            TopAppBar(
                title = { Text("StaggeredGrid 交错网格案例") }
            )
        }
    ) { innerPadding ->
        /**
         * ============================================
         * 第4步：使用 LazyVerticalStaggeredGrid 创建交错网格
         * ============================================
         * 
         * LazyVerticalStaggeredGrid 是垂直方向的交错网格组件
         * 与普通网格的区别：每列高度可以不同，自动填充最短列
         * 
         * 需要导入：androidx.compose.foundation.lazy.staggeredgrid
         */
        LazyVerticalStaggeredGrid(
            /**
             * Modifier 设置组件外观
             * - fillMaxSize() 填满整个可用空间
             * - padding(innerPadding) 避开系统栏和标题栏
             */
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            
            /**
             * ============================================
             * 第5步：设置列数（重要！）
             * ============================================
             * 
             * columns 参数决定网格有几列
             * 
             * 方式1：StaggeredGridCells.Fixed(2) - 固定2列
             * 方式2：StaggeredGridCells.Adaptive(100.dp) - 自适应列数
             */
            columns = StaggeredGridCells.Fixed(2),
            
            /**
             * contentPadding 设置网格内容的内边距
             * 相当于给整个网格四周留出空白
             */
            contentPadding = PaddingValues(16.dp),
            
            /**
             * horizontalArrangement 设置列之间的水平间距
             * verticalItemSpacing 设置行之间的垂直间距
             * 
             * 注意：与普通 LazyVerticalGrid 不同
             * 这里用 verticalItemSpacing 而不是 verticalArrangement
             */
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalItemSpacing = 12.dp
        ) {
            /**
             * ============================================
             * 第6步：添加网格项
             * ============================================
             * 
             * items() 函数遍历数据列表
             * 与 LazyColumn / LazyVerticalGrid 用法类似
             */
            items(
                items = items,
                key = { it.id }  // 唯一标识符，优化性能
            ) { item ->
                StaggeredItemCard(item = item)
            }
        }
    }
}

/**
 * ============================================
 * 第7步：交错网格项卡片
 * ============================================
 * 
 * 每个卡片的高度不同（由 item.height 决定）
 * 这是形成交错效果的关键
 * 
 * @param item 数据项，包含高度信息
 */
@Composable
fun StaggeredItemCard(item: StaggeredItem) {
    Card(
        /**
         * 设置卡片高度为 item.height.dp
         * 不同的高度形成交错效果
         */
        modifier = Modifier
            .fillMaxWidth()
            .height(item.height.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(item.color),
            contentAlignment = Alignment.Center
        ) {
            /**
             * 垂直排列：编号 + 标题 + 高度
             */
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // 编号圆圈
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(RoundedCornerShape(50))
                        .background(Color.White.copy(alpha = 0.3f)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = item.id,
                        color = Color.White,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                
                // 标题
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 8.dp)
                )
                
                // 显示高度信息
                Text(
                    text = "${item.height}dp",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.White.copy(alpha = 0.8f),
                    modifier = Modifier.padding(top = 4.dp)
                )
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
fun StaggeredGridDemoScreenPreview() {
    HelloAndroidTheme {
        StaggeredGridDemoScreen()
    }
}
