package com.zhangdapeng.helloandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.text.style.TextAlign
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
                    BoxLayoutDemo(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

/**
 * Box 层叠布局综合演示
 *
 * Box 是 Jetpack Compose 中用于堆叠子项的布局组件
 * 特点：子项会按顺序层叠（后覆盖前）
 *
 * 主要参数：
 * - modifier: 修饰符
 * - contentAlignment: 内容默认对齐方式
 * - propagateMinConstraints: 是否将最小约束传递给子项
 */
@Composable
fun BoxLayoutDemo(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Box 层叠布局演示",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        // 1. 基础层叠 - 简单堆叠
        BasicStackExample()

        // 2. 对齐方式演示（9种位置）
        AlignmentExample()

        // 3. 使用 Offset 偏移定位
        OffsetExample()

        // 4. 实际应用：带徽章的头像
        AvatarWithBadgeExample()

        // 5. 实际应用：图片上叠加文字
        ImageOverlayExample()

        // 6. 实际应用：卡片阴影效果
        CardWithShadowExample()
    }
}

/**
 * 1. 基础层叠示例
 * 展示 Box 的基本特性：子项按顺序堆叠
 */
@Composable
fun BasicStackExample() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "1. 基础层叠（后覆盖前）",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentAlignment = Alignment.Center // 默认居中对齐
            ) {
                // 底层 - 大红色方块
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFFEF5350))
                )
                // 中层 - 绿色方块
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFF66BB6A))
                )
                // 顶层 - 蓝色方块
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFF42A5F5))
                )
            }

            Text(
                text = "红(底层) → 绿(中层) → 蓝(顶层)",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

/**
 * 2. 对齐方式演示
 * Box 支持 9 种对齐位置
 */
@Composable
fun AlignmentExample() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "2. 对齐方式（9种位置）",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // 第一行：Top 对齐
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                AlignmentBox(Alignment.TopStart, "左上")
                AlignmentBox(Alignment.TopCenter, "中上")
                AlignmentBox(Alignment.TopEnd, "右上")
            }

            Spacer(modifier = Modifier.height(8.dp))

            // 第二行：Center 对齐
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                AlignmentBox(Alignment.CenterStart, "左中")
                AlignmentBox(Alignment.Center, "中心")
                AlignmentBox(Alignment.CenterEnd, "右中")
            }

            Spacer(modifier = Modifier.height(8.dp))

            // 第三行：Bottom 对齐
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                AlignmentBox(Alignment.BottomStart, "左下")
                AlignmentBox(Alignment.BottomCenter, "中下")
                AlignmentBox(Alignment.BottomEnd, "右下")
            }
        }
    }
}

@Composable
fun AlignmentBox(alignment: Alignment, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFFE3F2FD))
        ) {
            // 使用 Modifier.align 定位子项
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .align(alignment)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color(0xFF1976D2))
            )
        }
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

/**
 * 3. 使用 Offset 偏移定位
 */
@Composable
fun OffsetExample() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "3. 使用 Offset 精确定位",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFF5F5F5))
            ) {
                // 左上角圆点
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .offset(x = 16.dp, y = 16.dp)
                        .clip(CircleShape)
                        .background(Color.Red)
                )
                // 右上角圆点
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.TopEnd)
                        .offset(x = (-16).dp, y = 16.dp)
                        .clip(CircleShape)
                        .background(Color.Green)
                )
                // 左下角圆点
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.BottomStart)
                        .offset(x = 16.dp, y = (-16).dp)
                        .clip(CircleShape)
                        .background(Color.Blue)
                )
                // 右下角圆点
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.BottomEnd)
                        .offset(x = (-16).dp, y = (-16).dp)
                        .clip(CircleShape)
                        .background(Color.Yellow)
                )
                // 中心文字
                Text(
                    text = "Offset 定位",
                    modifier = Modifier.align(Alignment.Center),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

/**
 * 4. 实际应用：带徽章的头像
 */
@Composable
fun AvatarWithBadgeExample() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "4. 实际应用：带徽章的头像",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                // 未读消息徽章
                Box {
                    // 头像
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF1976D2)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "张",
                            color = Color.White,
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                    // 徽章（右上角）
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .align(Alignment.TopEnd)
                            .clip(CircleShape)
                            .background(Color.Red),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "3",
                            color = Color.White,
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }

                // 在线状态徽章
                Box {
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF66BB6A)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "李",
                            color = Color.White,
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                    // 在线绿点
                    Box(
                        modifier = Modifier
                            .size(16.dp)
                            .align(Alignment.BottomEnd)
                            .offset(x = (-4).dp, y = (-4).dp)
                            .clip(CircleShape)
                            .background(Color(0xFF4CAF50))
                            .padding(2.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(CircleShape)
                                .background(Color.White)
                        )
                    }
                }

                // 会员等级徽章
                Box {
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFFFA726)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "王",
                            color = Color.White,
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                    // VIP 徽章
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .offset(y = 8.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color(0xFFFFD700))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = "VIP",
                            color = Color.Black,
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }
            }
        }
    }
}

/**
 * 5. 实际应用：图片上叠加文字
 */
@Composable
fun ImageOverlayExample() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "5. 实际应用：图片上叠加文字",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            // 模拟卡片
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(12.dp))
            ) {
                // 背景色模拟图片
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFF1976D2))
                )

                // 渐变遮罩（模拟渐变效果）
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Color.Black.copy(alpha = 0.3f)
                        )
                )

                // 左上角标签
                Box(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(12.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color(0xFFFF5722))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "热门",
                        color = Color.White,
                        style = MaterialTheme.typography.labelMedium
                    )
                }

                // 底部文字区域
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .fillMaxWidth()
                        .background(Color.Black.copy(alpha = 0.6f))
                        .padding(12.dp)
                ) {
                    Text(
                        text = "Kotlin 编程入门到精通",
                        color = Color.White,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "张大师 · 10万+人学习",
                        color = Color.LightGray,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}

/**
 * 6. 实际应用：卡片阴影效果
 */
@Composable
fun CardWithShadowExample() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "6. 实际应用：层叠装饰效果",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                // 装饰圆环 1
                Box(
                    modifier = Modifier
                        .size(140.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFBBDEFB))
                )
                // 装饰圆环 2
                Box(
                    modifier = Modifier
                        .size(110.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF90CAF9))
                )
                // 装饰圆环 3
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF64B5F6))
                )
                // 中心内容
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF1976D2)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "100",
                        color = Color.White,
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }

            Text(
                text = "层叠圆环进度指示器",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BoxLayoutDemoPreview() {
    HelloAndroidTheme {
        BoxLayoutDemo()
    }
}
