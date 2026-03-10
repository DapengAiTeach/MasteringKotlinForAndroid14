package com.zhangdapeng.helloandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
        enableEdgeToEdge()
        setContent {
            HelloAndroidTheme {
                GridDemoScreen()
            }
        }
    }
}

/**
 * 数据类：网格项
 */
data class GridItem(
    val id: String,
    val name: String,
    val color: Color
)

/**
 * 模拟数据
 */
fun getSampleGridItems(): List<GridItem> = listOf(
    GridItem("1", "相机", Color(0xFFEF5350)),
    GridItem("2", "相册", Color(0xFF66BB6A)),
    GridItem("3", "地图", Color(0xFF42A5F5)),
    GridItem("4", "音乐", Color(0xFFFFA726)),
    GridItem("5", "视频", Color(0xFFAB47BC)),
    GridItem("6", "设置", Color(0xFF26A69A)),
    GridItem("7", "邮件", Color(0xFFEC407A)),
    GridItem("8", "日历", Color(0xFF7E57C2)),
    GridItem("9", "天气", Color(0xFF29B6F6)),
    GridItem("10", "时钟", Color(0xFF66BB6A)),
    GridItem("11", "计算器", Color(0xFFFF7043)),
    GridItem("12", "备忘录", Color(0xFF8D6E63))
)

/**
 * ============================================
 * 网格列表界面（长按删除版本）
 * ============================================
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GridDemoScreen() {
    
    // 网格数据列表
    val items = remember {
        mutableStateListOf<GridItem>().apply {
            addAll(getSampleGridItems())
        }
    }
    
    /**
     * 待删除的项
     * null 表示没有待删除的项，不显示对话框
     * 有值时显示删除确认对话框
     */
    var itemToDelete by remember { mutableStateOf<GridItem?>(null) }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("应用网格（长按删除）") }
            )
        }
    ) { innerPadding ->
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(
                items = items,
                key = { it.id }
            ) { item ->
                GridItemCard(
                    item = item,
                    onLongClick = {
                        // 长按弹出删除确认
                        itemToDelete = item
                    }
                )
            }
        }
    }
    
    /**
     * 删除确认对话框
     * 当 itemToDelete 不为 null 时显示
     */
    if (itemToDelete != null) {
        AlertDialog(
            onDismissRequest = { itemToDelete = null },
            title = { Text("确认删除") },
            text = { Text("确定要删除「${itemToDelete?.name}」吗？") },
            confirmButton = {
                TextButton(
                    onClick = {
                        itemToDelete?.let { items.remove(it) }
                        itemToDelete = null
                    }
                ) {
                    Text("删除", color = Color(0xFFEF5350))
                }
            },
            dismissButton = {
                TextButton(onClick = { itemToDelete = null }) {
                    Text("取消")
                }
            }
        )
    }
}

/**
 * ============================================
 * 网格项卡片（支持长按）
 * ============================================
 * 
 * @param item 数据
 * @param onLongClick 长按回调
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GridItemCard(item: GridItem, onLongClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            /**
             * combinedClickable 支持单击和长按
             * - onClick: 单击事件（这里留空）
             * - onLongClick: 长按事件
             * - onLongClickLabel: 无障碍描述
             */
            .combinedClickable(
                onClick = { /* 单击可扩展为打开应用 */ },
                onLongClick = onLongClick,
                onLongClickLabel = "长按删除${item.name}"
            ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(item.color.copy(alpha = 0.2f)),
            contentAlignment = Alignment.Center
        ) {
            // 中间内容：图标 + 文字
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // 圆形图标
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(item.color),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = item.name.first().toString(),
                        color = Color.White,
                        style = MaterialTheme.typography.titleLarge
                    )
                }
                
                // 名称文字
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}

/**
 * 预览
 */
@Preview(showBackground = true)
@Composable
fun GridDemoScreenPreview() {
    HelloAndroidTheme {
        GridDemoScreen()
    }
}
