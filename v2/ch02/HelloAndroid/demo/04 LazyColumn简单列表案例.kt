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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zhangdapeng.helloandroid.ui.theme.HelloAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HelloAndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SimpleListDemo(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

/**
 * 简单列表演示 - LazyColumn 基础用法
 */
@Composable
fun SimpleListDemo(modifier: Modifier = Modifier) {
    // 列表数据
    val items = remember {
        mutableStateListOf(
            ListItem("1", "Item 1", "这是第一个列表项", Color(0xFFEF5350)),
            ListItem("2", "Item 2", "这是第二个列表项", Color(0xFF66BB6A)),
            ListItem("3", "Item 3", "这是第三个列表项", Color(0xFF42A5F5)),
            ListItem("4", "Item 4", "这是第四个列表项", Color(0xFFFFA726)),
            ListItem("5", "Item 5", "这是第五个列表项", Color(0xFFAB47BC)),
            ListItem("6", "Item 6", "这是第六个列表项", Color(0xFFEC407A)),
            ListItem("7", "Item 7", "这是第七个列表项", Color(0xFF26A69A)),
            ListItem("8", "Item 8", "这是第八个列表项", Color(0xFF7E57C2))
        )
    }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // 列表标题
        item {
            Text(
                text = "简单列表案例",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        // 列表项
        items(
            items = items,
            key = { it.id }
        ) { item ->
            ListItemCard(
                item = item,
                onDelete = { items.remove(item) }
            )
        }
    }
}

/**
 * 列表项卡片
 */
@Composable
fun ListItemCard(item: ListItem, onDelete: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 彩色圆形编号
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(item.color),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = item.id,
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium
                )
            }

            // 间距
            androidx.compose.foundation.layout.Spacer(modifier = Modifier.width(16.dp))

            // 文本内容
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = item.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }

            // 删除按钮
            IconButton(onClick = onDelete) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "删除",
                    tint = Color(0xFFEF5350)
                )
            }
        }
    }
}

/**
 * 数据类
 */
data class ListItem(
    val id: String,
    val title: String,
    val description: String,
    val color: Color
)

@Preview(showBackground = true)
@Composable
fun SimpleListDemoPreview() {
    HelloAndroidTheme {
        SimpleListDemo()
    }
}
