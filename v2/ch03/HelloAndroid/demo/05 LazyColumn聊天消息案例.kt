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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zhangdapeng.helloandroid.ui.theme.HelloAndroidTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HelloAndroidTheme {
                ChatScreen()
            }
        }
    }
}

/**
 * 聊天消息案例 - LazyColumn 实现聊天界面
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen() {
    val messages = remember {
        mutableStateListOf(
            ChatMessage("1", "你好！最近怎么样？", false, "张三", Color(0xFF66BB6A), 1700000000000),
            ChatMessage("2", "挺好的，你呢？", true, "我", Color(0xFF42A5F5), 1700000100000),
            ChatMessage("3", "也不错，周末有空一起打球吗？", false, "张三", Color(0xFF66BB6A), 1700000200000),
            ChatMessage("4", "可以啊，周六下午怎么样？", true, "我", Color(0xFF42A5F5), 1700000300000),
            ChatMessage("5", "没问题，那周六下午2点球场见！", false, "张三", Color(0xFF66BB6A), 1700000400000),
            ChatMessage("6", "好的，到时候见！", true, "我", Color(0xFF42A5F5), 1700000500000),
            ChatMessage("7", "对了，记得带水", false, "张三", Color(0xFF66BB6A), 1700000600000),
            ChatMessage("8", "没问题，我带两桶", true, "我", Color(0xFF42A5F5), 1700000700000)
        )
    }
    var inputText by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("聊天消息案例") }
            )
        },
        bottomBar = {
            // 底部输入框
            ChatInputBar(
                text = inputText,
                onTextChange = { inputText = it },
                onSend = {
                    if (inputText.isNotBlank()) {
                        messages.add(
                            ChatMessage(
                                id = System.currentTimeMillis().toString(),
                                content = inputText,
                                isMe = true,
                                sender = "我",
                                avatarColor = Color(0xFF42A5F5),
                                timestamp = System.currentTimeMillis()
                            )
                        )
                        inputText = ""
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            reverseLayout = false
        ) {
            items(
                items = messages,
                key = { it.id }
            ) { message ->
                ChatMessageItem(message = message)
            }
        }
    }
}

/**
 * 聊天消息项
 * 根据 isMe 决定左右对齐
 */
@Composable
fun ChatMessageItem(message: ChatMessage) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (message.isMe) Arrangement.End else Arrangement.Start
    ) {
        if (!message.isMe) {
            // 对方头像（左侧）
            Avatar(name = message.sender, color = message.avatarColor)
            Spacer(modifier = Modifier.width(8.dp))
        }

        Column(
            horizontalAlignment = if (message.isMe) Alignment.End else Alignment.Start
        ) {
            // 发送者名称
            Text(
                text = message.sender,
                style = MaterialTheme.typography.labelSmall,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 2.dp)
            )

            // 消息气泡
            MessageBubble(
                content = message.content,
                isMe = message.isMe
            )

            // 时间戳
            Text(
                text = formatTime(message.timestamp),
                style = MaterialTheme.typography.labelSmall,
                color = Color.Gray,
                modifier = Modifier.padding(top = 2.dp)
            )
        }

        if (message.isMe) {
            // 我的头像（右侧）
            Spacer(modifier = Modifier.width(8.dp))
            Avatar(name = message.sender, color = message.avatarColor)
        }
    }
}

/**
 * 头像组件
 */
@Composable
fun Avatar(name: String, color: Color) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(color),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = name.first().toString(),
            color = Color.White,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

/**
 * 消息气泡
 */
@Composable
fun MessageBubble(content: String, isMe: Boolean) {
    Card(
        modifier = Modifier.widthIn(max = 280.dp),
        shape = RoundedCornerShape(
            topStart = 16.dp,
            topEnd = 16.dp,
            bottomStart = if (isMe) 16.dp else 4.dp,
            bottomEnd = if (isMe) 4.dp else 16.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = if (isMe) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.surfaceVariant
            }
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Text(
            text = content,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            color = if (isMe) Color.White else MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

/**
 * 底部输入栏
 */
@Composable
fun ChatInputBar(
    text: String,
    onTextChange: (String) -> Unit,
    onSend: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = text,
                onValueChange = onTextChange,
                modifier = Modifier.weight(1f),
                placeholder = { Text("输入消息...") },
                maxLines = 3
            )
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(
                onClick = onSend,
                enabled = text.isNotBlank()
            ) {
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = "发送",
                    tint = if (text.isNotBlank()) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        Color.Gray
                    }
                )
            }
        }
    }
}

/**
 * 数据类
 */
data class ChatMessage(
    val id: String,
    val content: String,
    val isMe: Boolean,
    val sender: String,
    val avatarColor: Color,
    val timestamp: Long
)

/**
 * 格式化时间
 */
fun formatTime(timestamp: Long): String {
    val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
    return sdf.format(Date(timestamp))
}

@Preview(showBackground = true)
@Composable
fun ChatScreenPreview() {
    HelloAndroidTheme {
        ChatScreen()
    }
}
