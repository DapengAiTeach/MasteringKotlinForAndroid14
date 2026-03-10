package com.zhangdapeng.helloandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
                    RowLayoutDemo(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

/**
 * Row 布局综合演示
 *
 * Row 是 Jetpack Compose 中用于水平排列子项的布局组件
 * 主要参数：
 * - modifier: 修饰符，控制布局大小、间距等
 * - horizontalArrangement: 水平方向排列方式
 * - verticalAlignment: 垂直方向对齐方式
 */
@Composable
fun RowLayoutDemo(modifier: Modifier = Modifier) {
    // 可滚动容器，展示多个 Row 示例
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Row 水平布局演示",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        // 1. 基础 Row - 默认排列
        RowExample(
            title = "1. 基础排列（默认靠左）",
            arrangement = Arrangement.Start,
            alignment = Alignment.Top
        )

        // 2. 居中对齐
        RowExample(
            title = "2. 居中排列",
            arrangement = Arrangement.Center,
            alignment = Alignment.CenterVertically
        )

        // 3. 靠右对齐
        RowExample(
            title = "3. 靠右排列",
            arrangement = Arrangement.End,
            alignment = Alignment.CenterVertically
        )

        // 4. 均匀分布
        RowExample(
            title = "4. 均匀分布（SpaceEvenly）",
            arrangement = Arrangement.SpaceEvenly,
            alignment = Alignment.CenterVertically
        )

        // 5. 两端对齐
        RowExample(
            title = "5. 两端对齐（SpaceBetween）",
            arrangement = Arrangement.SpaceBetween,
            alignment = Alignment.CenterVertically
        )

        // 6. 权重示例
        WeightRowExample()

        // 7. 实际应用场景：底部导航栏
        BottomNavExample()
    }
}

/**
 * 基础 Row 示例卡片
 */
@Composable
fun RowExample(
    title: String,
    arrangement: Arrangement.Horizontal,
    alignment: Alignment.Vertical
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            // 演示 Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFE3F2FD))
                    .padding(8.dp),
                horizontalArrangement = arrangement,
                verticalAlignment = alignment
            ) {
                ColorBox(color = Color.Red, text = "A")
                Spacer(modifier = Modifier.width(8.dp))
                ColorBox(color = Color.Green, text = "B")
                Spacer(modifier = Modifier.width(8.dp))
                ColorBox(color = Color.Blue, text = "C")
            }
        }
    }
}

/**
 * 彩色小方块
 */
@Composable
fun ColorBox(color: Color, text: String) {
    Column(
        modifier = Modifier
            .size(40.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(color),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = text,
            color = Color.White,
            style = MaterialTheme.typography.labelLarge
        )
    }
}

/**
 * 权重（Weight）使用示例 - 水平方向
 * weight 可以让子项按比例分配可用空间
 */
@Composable
fun WeightRowExample() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "6. 权重分配（Weight）",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "红色: 1份 | 绿色: 2份 | 蓝色: 1份",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFF5F5F5))
                    .padding(4.dp)
            ) {
                // 红色占 1 份
                BoxWithWeightRow(
                    color = Color(0xFFEF5350),
                    weight = 1f,
                    text = "1f"
                )
                Spacer(modifier = Modifier.width(4.dp))
                // 绿色占 2 份
                BoxWithWeightRow(
                    color = Color(0xFF66BB6A),
                    weight = 2f,
                    text = "2f"
                )
                Spacer(modifier = Modifier.width(4.dp))
                // 蓝色占 1 份
                BoxWithWeightRow(
                    color = Color(0xFF42A5F5),
                    weight = 1f,
                    text = "1f"
                )
            }
        }
    }
}

@Composable
fun RowScope.BoxWithWeightRow(color: Color, weight: Float, text: String) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .weight(weight)
            .clip(RoundedCornerShape(4.dp))
            .background(color),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = text,
            color = Color.White,
            style = MaterialTheme.typography.labelMedium
        )
    }
}

/**
 * 实际应用场景：底部导航栏
 */
@Composable
fun BottomNavExample() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "7. 实际应用：底部导航栏",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            // 模拟底部导航栏
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFF1976D2))
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                NavItem(icon = "🏠", label = "首页")
                NavItem(icon = "🔍", label = "搜索")
                NavItem(icon = "❤️", label = "收藏")
                NavItem(icon = "☰", label = "我的")
            }
        }
    }
}

@Composable
fun NavItem(icon: String, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = icon, style = MaterialTheme.typography.titleMedium)
        Text(
            text = label,
            color = Color.White,
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RowLayoutDemoPreview() {
    HelloAndroidTheme {
        RowLayoutDemo()
    }
}
