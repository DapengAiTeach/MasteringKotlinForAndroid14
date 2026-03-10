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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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

/**
 * 主 Activity - 应用入口
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 启用全面屏显示
        enableEdgeToEdge()
        // 设置应用内容
        setContent {
            HelloAndroidTheme {
                ContactListScreen()
            }
        }
    }
}

/**
 * ============================================
 * 第1步：定义数据类（数据模型）
 * ============================================
 * 
 * data class 用于定义存放数据的类
 * @param id 唯一标识符（用于区分不同的联系人）
 * @param name 联系人姓名
 * @param phone 电话号码
 * @param color 头像背景颜色
 */
data class Contact(
    val id: String,
    val name: String,
    val phone: String,
    val color: Color
)

/**
 * ============================================
 * 第2步：准备模拟数据
 * ============================================
 * 
 * 这里创建一些假数据用于演示
 * 实际应用中可能从数据库或网络获取
 */
fun getSampleContacts(): List<Contact> = listOf(
    Contact("1", "张三", "138-0013-8000", Color(0xFFEF5350)),
    Contact("2", "李四", "139-0014-9000", Color(0xFF66BB6A)),
    Contact("3", "王五", "137-0013-7000", Color(0xFF42A5F5)),
    Contact("4", "赵六", "136-0013-6000", Color(0xFFFFA726)),
    Contact("5", "孙七", "135-0013-5000", Color(0xFFAB47BC)),
    Contact("6", "周八", "133-0013-3000", Color(0xFF26A69A)),
    Contact("7", "吴九", "132-0013-2000", Color(0xFFEC407A)),
    Contact("8", "郑十", "131-0013-1000", Color(0xFF7E57C2))
)

/**
 * ============================================
 * 第3步：联系人列表界面（主屏幕）
 * ============================================
 * 
 * @OptIn 注解表示使用了实验性 API（TopAppBar）
 * @Composable 表示这是一个 Jetpack Compose 函数
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactListScreen() {
    
    /**
     * remember { } 用于记住状态
     * mutableStateListOf 创建一个可观察的列表
     * 当列表内容变化时，Compose 会自动刷新界面
     */
    val contacts = remember {
        mutableStateListOf<Contact>().apply {
            addAll(getSampleContacts())
        }
    }
    
    /**
     * Scaffold 是 Material Design 的基础布局结构
     * 提供标准布局：标题栏(TopBar)、内容区域、底部栏(BottomBar)
     */
    Scaffold(
        // 顶部标题栏
        topBar = {
            TopAppBar(
                title = { Text("联系人列表") }
            )
        }
    ) { innerPadding ->
        /**
         * ============================================
         * 第4步：使用 LazyColumn 创建列表
         * ============================================
         * 
         * LazyColumn 是 Compose 提供的"懒加载"列表组件
         * 特点：只渲染屏幕上可见的项，性能更好
         * 
         * 对比：Column 会一次性渲染所有项，数据量大时会卡顿
         */
        LazyColumn(
            /**
             * Modifier 用于修改组件的外观和行为
             * - fillMaxSize() 填满整个可用空间
             * - padding(innerPadding) 避开系统栏（如状态栏）
             */
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            
            /**
             * contentPadding 设置列表内容的内边距
             * 与 modifier.padding 的区别：
             * - contentPadding 是列表内部内容的边距
             * - modifier.padding 是整个列表组件的边距
             */
            contentPadding = PaddingValues(16.dp),
            
            /**
             * verticalArrangement 设置列表项之间的排列方式
             * Arrangement.spacedBy(12.dp) 表示每项之间间隔 12dp
             */
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            /**
             * ============================================
             * 第5步：添加列表项
             * ============================================
             * 
             * items() 是 LazyColumn 提供的函数
             * 用于遍历数据列表，为每个数据创建对应的 UI
             * 
             * 参数说明：
             * - items = contacts: 要显示的数据列表
             * - key = { it.id }: 唯一标识符，用于优化列表性能
             */
            items(
                items = contacts,
                key = { it.id }  // 重要！帮助 Compose 识别每个列表项
            ) { contact ->
                /**
                 * 这里 contact 代表列表中的每一项
                 * ContactItemCard 是自定义的联系人卡片组件
                 * onDelete 是点击删除按钮时的回调
                 */
                ContactItemCard(
                    contact = contact,
                    onDelete = {
                        // 从列表中移除这个联系人
                        contacts.remove(contact)
                    }
                )
            }
        }
    }
}

/**
 * ============================================
 * 第6步：联系人卡片组件
 * ============================================
 * 
 * 这是列表中每一项的具体 UI 实现
 * @param contact 联系人数据
 * @param onDelete 删除回调函数（lambda）
 */
@Composable
fun ContactItemCard(contact: Contact, onDelete: () -> Unit) {
    /**
     * Card 是 Material Design 的卡片组件
     * 提供阴影、圆角等视觉效果
     */
    Card(
        modifier = Modifier.fillMaxWidth(),  // 填满可用宽度
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)  // 阴影高度
    ) {
        /**
         * Row 是水平排列子组件的布局
         * 这里用于：头像 | 文字信息 | 按钮
         */
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),  // 内边距
            verticalAlignment = Alignment.CenterVertically  // 垂直居中
        ) {
            
            // ==================== 头像部分 ====================
            /**
             * Box 是一个容器，可以堆叠多个子组件
             * 这里用来做圆形头像背景
             */
            Box(
                modifier = Modifier
                    .size(48.dp)  // 宽高都是 48dp
                    .clip(CircleShape)  // 裁剪成圆形
                    .background(contact.color),  // 背景色
                contentAlignment = Alignment.Center  // 内容居中
            ) {
                /**
                 * 显示姓名的第一个字
                 * contact.name.first() 获取字符串的第一个字符
                 */
                Text(
                    text = contact.name.first().toString(),
                    color = Color.White,  // 白色文字
                    style = MaterialTheme.typography.titleMedium  // 字体样式
                )
            }
            
            // ==================== 间距 ====================
            Spacer(modifier = Modifier.width(16.dp))
            
            // ==================== 文字信息部分 ====================
            /**
             * Column 是垂直排列子组件的布局
             * 这里用于堆叠姓名和电话号码
             */
            Column(modifier = Modifier.weight(1f)) {  // weight(1f) 占据剩余空间
                // 姓名
                Text(
                    text = contact.name,
                    style = MaterialTheme.typography.bodyLarge
                )
                // 电话号码
                Text(
                    text = contact.phone,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray  // 灰色文字
                )
            }
            
            // ==================== 拨打电话按钮 ====================
            IconButton(onClick = { /* 这里可以实现拨打电话功能 */ }) {
                Icon(
                    imageVector = Icons.Default.Phone,  // 使用系统自带的电话图标
                    contentDescription = "打电话",  // 无障碍描述
                    tint = MaterialTheme.colorScheme.primary  // 使用主题主色
                )
            }
        }
    }
}

/**
 * ============================================
 * 预览函数（在 Android Studio 中预览界面）
 * ============================================
 * 
 * @Preview 注解表示这是预览函数
 * 不需要运行到真机上，在 IDE 中就能看到效果
 */
@Preview(showBackground = true)
@Composable
fun ContactListScreenPreview() {
    HelloAndroidTheme {
        ContactListScreen()
    }
}
