package com.zhangdapeng.helloandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
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
                    ColumnLayoutDemo(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

/**
 * Column 布局综合演示
 * 
 * Column 是 Jetpack Compose 中用于垂直排列子项的布局组件
 * 主要参数：
 * - modifier: 修饰符，控制布局大小、间距等
 * - verticalArrangement: 垂直方向排列方式
 * - horizontalAlignment: 水平方向对齐方式
 */
@Composable
fun ColumnLayoutDemo(modifier: Modifier = Modifier) {
    // 可滚动容器，展示多个 Column 示例
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Column 布局演示",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        // 1. 基础 Column - 默认排列
        ColumnExample(
            title = "1. 基础排列（默认顶部对齐）",
            arrangement = Arrangement.Top,
            alignment = Alignment.Start
        )

        // 2. 居中对齐
        ColumnExample(
            title = "2. 居中对齐",
            arrangement = Arrangement.Top,
            alignment = Alignment.CenterHorizontally
        )

        // 3. 底部对齐
        ColumnExample(
            title = "3. 子项居右",
            arrangement = Arrangement.Top,
            alignment = Alignment.End
        )

        // 4. 均匀分布
        ColumnExample(
            title = "4. 均匀分布（SpaceEvenly）",
            arrangement = Arrangement.SpaceEvenly,
            alignment = Alignment.CenterHorizontally
        )

        // 5. 权重示例
        WeightColumnExample()

        // 6. 登录表单示例（实际应用场景）
        LoginFormExample()
    }
}

/**
 * 基础 Column 示例卡片
 */
@Composable
fun ColumnExample(
    title: String,
    arrangement: Arrangement.Vertical,
    alignment: Alignment.Horizontal
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
            // 演示 Column
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFE3F2FD))
                    .padding(8.dp),
                verticalArrangement = arrangement,
                horizontalAlignment = alignment
            ) {
                ColorBox(color = Color.Red, text = "A")
                ColorBox(color = Color.Green, text = "B")
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
 * 权重（Weight）使用示例
 * weight 可以让子项按比例分配可用空间
 */
@Composable
fun WeightColumnExample() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "5. 权重分配（Weight）",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "红色: 1份 | 绿色: 2份 | 蓝色: 1份",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(8.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFF5F5F5))
                    .padding(4.dp)
            ) {
                // 红色占 1 份
                BoxWithWeight(
                    color = Color(0xFFEF5350),
                    weight = 1f,
                    text = "weight = 1f"
                )
                Spacer(modifier = Modifier.height(4.dp))
                // 绿色占 2 份
                BoxWithWeight(
                    color = Color(0xFF66BB6A),
                    weight = 2f,
                    text = "weight = 2f"
                )
                Spacer(modifier = Modifier.height(4.dp))
                // 蓝色占 1 份
                BoxWithWeight(
                    color = Color(0xFF42A5F5),
                    weight = 1f,
                    text = "weight = 1f"
                )
            }
        }
    }
}

@Composable
fun ColumnScope.BoxWithWeight(color: Color, weight: Float, text: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
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
 * 实际应用场景：登录表单
 */
@Composable
fun LoginFormExample() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "6. 实际应用：登录表单",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // 模拟输入框
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFF5F5F5))
                    .padding(horizontal = 12.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "用户名", color = Color.Gray)
            }

            // 模拟输入框
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFF5F5F5))
                    .padding(horizontal = 12.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "密码", color = Color.Gray)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("登录")
            }

            Text(
                text = "忘记密码？",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ColumnLayoutDemoPreview() {
    HelloAndroidTheme {
        ColumnLayoutDemo()
    }
}
