package com.example.civiceye

import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.civiceye.ui.theme.CiviceyeTheme

import kotlinx.coroutines.delay


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CiviceyeTheme {
                CiviceyeApp()
            }
        }
    }
}


// ======================================================
// APP NAVIGATION
// ======================================================

@Composable
fun CiviceyeApp() {

    var page by remember {
        mutableStateOf("splash")
    }

    when (page) {

        // ==================================================
        // PAGE 1 - SPLASH
        // ==================================================

        "splash" -> {

            SplashScreen(

                onGetStarted = {
                    page = "role"
                },

                onAdmin = {
                    page = "admin"
                }
            )
        }


        // ==================================================
        // PAGE 2 - ROLE SELECTION
        // ==================================================

        "role" -> {

            RoleSelectionScreen(

                onCitizen = {
                    page = "login"
                },

                onAdmin = {
                    page = "admin"
                },

                onBack = {
                    page = "splash"
                }
            )
        }


        // ==================================================
        // PAGE 3 - CITIZEN LOGIN
        // ==================================================

        "login" -> {

            CitizenLoginScreen(

                onBack = {
                    page = "role"
                },

                onLoginWithOtp = {
                    page = "otp"
                },

                onLoginSuccess = {
                    page = "home"
                }
            )
        }


        // ==================================================
        // PAGE 4 - OTP
        // ==================================================

        "otp" -> {

            OTPVerificationScreen(

                onBack = {
                    page = "login"
                },

                onVerified = {
                    page = "home"
                }
            )
        }


        // ==================================================
        // PAGE 5 - CITIZEN HOME
        // ==================================================

        "home" -> {

            CitizenHomeScreen()
        }


        // ==================================================
        // ADMIN
        // ==================================================

        "admin" -> {

            AdminLoginScreen(

                onBack = {
                    page = "splash"
                }
            )
        }
    }
}


// ======================================================
// PAGE 1 - SPLASH SCREEN
// ======================================================

@Composable
fun SplashScreen(
    onGetStarted: () -> Unit,
    onAdmin: () -> Unit
) {

    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {

        Image(
            painter = painterResource(
                id = R.drawable.splash_background
            ),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )


        Text(
            text = "CIVICEYE 👁",
            color = Color(0xFF123D78),
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,

            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(
                    y = maxHeight * 0.095f
                )
        )


        Text(
            text = "────  •  ────",
            color = Color(0xFF2479D1),
            fontSize = 18.sp,

            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(
                    y = maxHeight * 0.175f
                )
        )


        Text(
            text = "See it. Report it.",
            color = Color(0xFF071A35),
            fontSize = 27.sp,
            fontWeight = FontWeight.Bold,

            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(
                    y = maxHeight * 0.215f
                )
        )


        Text(
            text = "It’ll get fixed.",
            color = Color(0xFF17385E),
            fontSize = 19.sp,

            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(
                    y = maxHeight * 0.270f
                )
        )


        Text(
            text = "Together for a better city",
            color = Color(0xFF17385E),
            fontSize = 19.sp,

            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(
                    y = maxHeight * 0.305f
                )
        )


        Button(
            onClick = {
                onGetStarted()
            },

            modifier = Modifier
                .width(maxWidth * 0.70f)
                .height(maxHeight * 0.080f)
                .align(Alignment.TopCenter)
                .offset(
                    y = maxHeight * 0.685f
                ),

            shape = RoundedCornerShape(14.dp),

            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1677E8)
            )
        ) {

            Text(
                text = "🚀  Get Started",
                color = Color.White,
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold
            )
        }


        Button(
            onClick = {
                onAdmin()
            },

            modifier = Modifier
                .width(maxWidth * 0.70f)
                .height(maxHeight * 0.080f)
                .align(Alignment.TopCenter)
                .offset(
                    y = maxHeight * 0.785f
                ),

            shape = RoundedCornerShape(14.dp),

            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White
            ),

            border = BorderStroke(
                2.dp,
                Color(0xFF1769E8)
            )
        ) {

            Text(
                text = "♙  I’m Admin",
                color = Color(0xFF102C57),
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold
            )
        }


        Text(
            text = "🛡️✓  Your city. Your voice. Our action.",
            color = Color(0xFF17385E),
            fontSize = 17.sp,

            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(
                    y = maxHeight * 0.890f
                )
        )
    }
}


// ======================================================
// PAGE 2 - ROLE SELECTION
// ======================================================

@Composable
fun RoleSelectionScreen(
    onCitizen: () -> Unit,
    onAdmin: () -> Unit,
    onBack: () -> Unit
) {

    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {

        Image(
            painter = painterResource(
                id = R.drawable.role_selection
            ),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )


        Text(
            text = "←",
            color = Color(0xFF102C57),
            fontSize = 32.sp,

            modifier = Modifier
                .offset(
                    x = maxWidth * 0.035f,
                    y = maxHeight * 0.025f
                )
                .clickable {
                    onBack()
                }
        )


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(maxHeight * 0.205f)
                .offset(
                    y = maxHeight * 0.285f
                )
                .clickable {
                    onCitizen()
                }
        )


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(maxHeight * 0.205f)
                .offset(
                    y = maxHeight * 0.495f
                )
                .clickable {
                    onAdmin()
                }
        )
    }
}


// ======================================================
// PAGE 3 - CITIZEN LOGIN
// ======================================================

@Composable
fun CitizenLoginScreen(
    onBack: () -> Unit,
    onLoginWithOtp: () -> Unit,
    onLoginSuccess: () -> Unit
) {

    var mobileNumber by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var passwordVisible by remember {
        mutableStateOf(false)
    }

    var message by remember {
        mutableStateOf("")
    }


    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {

        Image(
            painter = painterResource(
                id = R.drawable.verify_login
            ),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )


        Text(
            text = "←",
            color = Color(0xFF102C57),
            fontSize = 30.sp,

            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(
                    x = maxWidth * 0.035f,
                    y = maxHeight * 0.025f
                )
                .clickable {
                    onBack()
                }
        )


        Text(
            text = "CIVICEYE 👁",
            color = Color(0xFF1554B8),
            fontSize = 39.sp,
            fontWeight = FontWeight.Bold,

            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(
                    y = maxHeight * 0.065f
                )
        )


        Text(
            text = "Welcome Back!",
            color = Color(0xFF0C2865),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,

            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(
                    y = maxHeight * 0.175f
                )
        )


        Text(
            text = "Login to continue",
            color = Color(0xFF60779B),
            fontSize = 21.sp,

            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(
                    y = maxHeight * 0.235f
                )
        )


        OutlinedTextField(

            value = mobileNumber,

            onValueChange = {

                if (
                    it.length <= 10 &&
                    it.all { character ->
                        character.isDigit()
                    }
                ) {
                    mobileNumber = it
                }
            },

            placeholder = {

                Text(
                    text = "Mobile Number",
                    color = Color(0xFF60779B),
                    fontSize = 19.sp
                )
            },

            singleLine = true,

            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone
            ),

            modifier = Modifier
                .fillMaxWidth(0.86f)
                .height(68.dp)
                .align(Alignment.TopCenter)
                .offset(
                    y = maxHeight * 0.295f
                ),

            shape = RoundedCornerShape(15.dp),

            colors = OutlinedTextFieldDefaults.colors(

                focusedBorderColor = Color(0xFFBCD0E5),

                unfocusedBorderColor = Color(0xFFBCD0E5),

                focusedContainerColor =
                    Color.White.copy(alpha = 0.94f),

                unfocusedContainerColor =
                    Color.White.copy(alpha = 0.94f)
            )
        )


        OutlinedTextField(

            value = password,

            onValueChange = {
                password = it
            },

            placeholder = {

                Text(
                    text = "Password",
                    color = Color(0xFF60779B),
                    fontSize = 19.sp
                )
            },

            singleLine = true,

            visualTransformation =
                if (passwordVisible) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },

            trailingIcon = {

                Text(
                    text =
                        if (passwordVisible) {
                            "👁"
                        } else {
                            "◉"
                        },

                    fontSize = 19.sp,

                    modifier = Modifier.clickable {
                        passwordVisible =
                            !passwordVisible
                    }
                )
            },

            modifier = Modifier
                .fillMaxWidth(0.86f)
                .height(68.dp)
                .align(Alignment.TopCenter)
                .offset(
                    y = maxHeight * 0.395f
                ),

            shape = RoundedCornerShape(15.dp),

            colors = OutlinedTextFieldDefaults.colors(

                focusedBorderColor = Color(0xFFBCD0E5),

                unfocusedBorderColor = Color(0xFFBCD0E5),

                focusedContainerColor =
                    Color.White.copy(alpha = 0.94f),

                unfocusedContainerColor =
                    Color.White.copy(alpha = 0.94f)
            )
        )


        Text(
            text = "Forgot Password?",
            color = Color(0xFF1677E8),
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,

            modifier = Modifier
                .align(Alignment.TopEnd)
                .offset(
                    x = -(maxWidth * 0.07f),
                    y = maxHeight * 0.475f
                )
                .clickable {

                    message = "Password reset selected"
                }
        )


        Button(

            onClick = {

                if (
                    mobileNumber.length == 10 &&
                    password.isNotEmpty()
                ) {

                    onLoginSuccess()

                } else {

                    message =
                        "Enter valid mobile number and password"
                }
            },

            modifier = Modifier
                .fillMaxWidth(0.86f)
                .height(68.dp)
                .align(Alignment.TopCenter)
                .offset(
                    y = maxHeight * 0.520f
                ),

            shape = RoundedCornerShape(15.dp),

            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1677E8)
            )
        ) {

            Text(
                text = "Login",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        }


        Text(
            text = "────────   or   ────────",
            color = Color(0xFF60779B),
            fontSize = 16.sp,

            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(
                    y = maxHeight * 0.595f
                )
        )


        Button(

            onClick = {

                if (mobileNumber.length == 10) {

                    onLoginWithOtp()

                } else {

                    message =
                        "Enter valid mobile number"
                }
            },

            modifier = Modifier
                .fillMaxWidth(0.86f)
                .height(68.dp)
                .align(Alignment.TopCenter)
                .offset(
                    y = maxHeight * 0.635f
                ),

            shape = RoundedCornerShape(15.dp),

            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White
            ),

            border = BorderStroke(
                2.dp,
                Color(0xFF1677E8)
            )
        ) {

            Text(
                text = "▣  Login with OTP",
                color = Color(0xFF1677E8),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }


        Text(
            text = "Don't have an account?  Sign Up",
            color = Color(0xFF1677E8),
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,

            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(
                    y = maxHeight * 0.730f
                )
                .clickable {

                    message = "Sign Up selected"
                }
        )


        if (message.isNotEmpty()) {

            Text(
                text = message,
                color = Color(0xFF123D78),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,

                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 25.dp)
            )
        }
    }
}


// ======================================================
// PAGE 4 - OTP VERIFICATION
// ======================================================

@Composable
fun OTPVerificationScreen(
    onBack: () -> Unit,
    onVerified: () -> Unit
) {

    var otp by remember {
        mutableStateOf("")
    }

    var message by remember {
        mutableStateOf("")
    }

    var secondsLeft by remember {
        mutableIntStateOf(28)
    }


    LaunchedEffect(secondsLeft) {

        if (secondsLeft > 0) {

            delay(1000)

            secondsLeft--
        }
    }


    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {

        Image(
            painter = painterResource(
                id = R.drawable.verify_login
            ),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )


        Text(
            text = "←",
            color = Color(0xFF102C57),
            fontSize = 32.sp,

            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(
                    x = maxWidth * 0.035f,
                    y = maxHeight * 0.025f
                )
                .clickable {
                    onBack()
                }
        )


        Text(
            text = "CIVICEYE 👁",
            color = Color(0xFF1554B8),
            fontSize = 39.sp,
            fontWeight = FontWeight.Bold,

            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(
                    y = maxHeight * 0.065f
                )
        )


        Text(
            text = "Verify OTP",
            color = Color(0xFF0C2865),
            fontSize = 31.sp,
            fontWeight = FontWeight.Bold,

            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(
                    y = maxHeight * 0.175f
                )
        )


        Text(
            text = "Enter the 6-digit code sent to",
            color = Color(0xFF60779B),
            fontSize = 19.sp,

            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(
                    y = maxHeight * 0.235f
                )
        )


        Text(
            text = "+91 98765 43210",
            color = Color(0xFF102C57),
            fontSize = 19.sp,
            fontWeight = FontWeight.Bold,

            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(
                    y = maxHeight * 0.275f
                )
        )


        BasicTextField(

            value = otp,

            onValueChange = { newValue ->

                if (
                    newValue.length <= 6 &&
                    newValue.all { it.isDigit() }
                ) {

                    otp = newValue

                    message = ""
                }
            },

            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),

            modifier = Modifier
                .fillMaxWidth(0.84f)
                .height(65.dp)
                .align(Alignment.TopCenter)
                .offset(
                    y = maxHeight * 0.335f
                ),

            decorationBox = {

                Row(
                    modifier = Modifier.fillMaxSize(),

                    horizontalArrangement =
                        Arrangement.SpaceBetween,

                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    repeat(6) { index ->

                        val digit =
                            if (index < otp.length) {
                                otp[index].toString()
                            } else {
                                ""
                            }


                        Box(
                            modifier = Modifier
                                .width(48.dp)
                                .height(58.dp)
                                .background(
                                    color = Color.White,
                                    shape = RoundedCornerShape(9.dp)
                                )
                                .border(
                                    BorderStroke(
                                        2.dp,
                                        Color(0xFF9DB6D3)
                                    ),
                                    RoundedCornerShape(9.dp)
                                ),

                            contentAlignment =
                                Alignment.Center
                        ) {

                            Text(
                                text = digit,
                                color = Color(0xFF102C57),
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        )


        if (secondsLeft > 0) {

            Text(
                text =
                    "Resend OTP in 00:" +
                            secondsLeft
                                .toString()
                                .padStart(2, '0'),

                color = Color(0xFF60779B),
                fontSize = 17.sp,

                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(
                        y = maxHeight * 0.435f
                    )
            )

        } else {

            Text(
                text = "Resend OTP",

                color = Color(0xFF1677E8),
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,

                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(
                        y = maxHeight * 0.435f
                    )
                    .clickable {

                        secondsLeft = 28

                        otp = ""

                        message =
                            "OTP resent successfully"
                    }
            )
        }


        Button(

            onClick = {

                if (otp.length == 6) {

                    onVerified()

                } else {

                    message =
                        "Please enter 6-digit OTP"
                }
            },

            modifier = Modifier
                .fillMaxWidth(0.80f)
                .height(62.dp)
                .align(Alignment.TopCenter)
                .offset(
                    y = maxHeight * 0.505f
                ),

            shape = RoundedCornerShape(13.dp),

            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1677E8)
            )
        ) {

            Text(
                text = "Verify",
                color = Color.White,
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold
            )
        }


        if (message.isNotEmpty()) {

            Text(
                text = message,
                color = Color(0xFF123D78),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,

                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(
                        y = maxHeight * 0.600f
                    )
            )
        }
    }
}


// ======================================================
// PAGE 5 - CITIZEN HOME
// ======================================================

@Composable
fun CitizenHomeScreen() {

    var searchText by remember {
        mutableStateOf("")
    }


    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {

        // ==================================================
        // PAGE 5 BACKGROUND
        // ==================================================

        Image(
            painter = painterResource(
                id = R.drawable.dash_board
            ),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )


        // ==================================================
        // SEARCH BAR
        // ==================================================

        Box(
            modifier = Modifier
                .fillMaxWidth(0.82f)
                .height(52.dp)
                .align(Alignment.TopCenter)
                .offset(
                    y = maxHeight * 0.205f
                )
                .background(
                    Color.White.copy(alpha = 0.96f),
                    RoundedCornerShape(14.dp)
                )
                .padding(
                    start = 48.dp,
                    end = 16.dp
                ),

            contentAlignment = Alignment.CenterStart
        ) {

            BasicTextField(

                value = searchText,

                onValueChange = {
                    searchText = it
                },

                singleLine = true,

                textStyle =
                    androidx.compose.ui.text.TextStyle(
                        color = Color(0xFF17385E),
                        fontSize = 16.sp
                    ),

                decorationBox = { innerTextField ->

                    if (searchText.isEmpty()) {

                        Text(
                            text =
                                "Search complaints, issues, villages...",
                            color = Color(0xFF7890A8),
                            fontSize = 15.sp
                        )
                    }

                    innerTextField()
                },

                modifier = Modifier.fillMaxWidth()
            )
        }


        // ==================================================
        // QUICK SEARCH
        // ==================================================

        Text(
            text = "Quick Search",
            color = Color(0xFF102C57),
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold,

            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(
                    x = maxWidth * 0.07f,
                    y = maxHeight * 0.285f
                )
        )


        // ==================================================
        // QUICK SEARCH CARDS
        // ==================================================

        Row(
            modifier = Modifier
                .fillMaxWidth(0.88f)
                .align(Alignment.TopCenter)
                .offset(
                    y = maxHeight * 0.335f
                ),

            horizontalArrangement =
                Arrangement.spacedBy(8.dp)
        ) {


            // NEARBY ISSUES

            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(105.dp)
                    .background(
                        Color.White.copy(alpha = 0.96f),
                        RoundedCornerShape(15.dp)
                    )
                    .border(
                        1.dp,
                        Color(0xFFD8E5F2),
                        RoundedCornerShape(15.dp)
                    )
                    .clickable {
                    }
                    .padding(10.dp)
            ) {

                Column(
                    horizontalAlignment =
                        Alignment.CenterHorizontally,

                    modifier = Modifier.fillMaxWidth()
                ) {

                    Text(
                        text = "📍",
                        fontSize = 28.sp
                    )

                    Spacer(
                        modifier = Modifier.height(5.dp)
                    )

                    Text(
                        text = "Nearby\nIssues",
                        color = Color(0xFF17385E),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }


            // SEARCH BY VILLAGE

            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(105.dp)
                    .background(
                        Color.White.copy(alpha = 0.96f),
                        RoundedCornerShape(15.dp)
                    )
                    .border(
                        1.dp,
                        Color(0xFFD8E5F2),
                        RoundedCornerShape(15.dp)
                    )
                    .clickable {
                    }
                    .padding(10.dp)
            ) {

                Column(
                    horizontalAlignment =
                        Alignment.CenterHorizontally,

                    modifier = Modifier.fillMaxWidth()
                ) {

                    Text(
                        text = "🏘️",
                        fontSize = 28.sp
                    )

                    Spacer(
                        modifier = Modifier.height(5.dp)
                    )

                    Text(
                        text = "Search by\nVillage",
                        color = Color(0xFF17385E),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }


            // PROBLEM TYPE

            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(105.dp)
                    .background(
                        Color.White.copy(alpha = 0.96f),
                        RoundedCornerShape(15.dp)
                    )
                    .border(
                        1.dp,
                        Color(0xFFD8E5F2),
                        RoundedCornerShape(15.dp)
                    )
                    .clickable {
                    }
                    .padding(10.dp)
            ) {

                Column(
                    horizontalAlignment =
                        Alignment.CenterHorizontally,

                    modifier = Modifier.fillMaxWidth()
                ) {

                    Text(
                        text = "🔎",
                        fontSize = 28.sp
                    )

                    Spacer(
                        modifier = Modifier.height(5.dp)
                    )

                    Text(
                        text = "Problem\nType",
                        color = Color(0xFF17385E),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }


        // ==================================================
        // REPORT + MY COMPLAINTS
        // ==================================================

        Row(
            modifier = Modifier
                .fillMaxWidth(0.88f)
                .align(Alignment.TopCenter)
                .offset(
                    y = maxHeight * 0.465f
                ),

            horizontalArrangement =
                Arrangement.spacedBy(10.dp)
        ) {


            // REPORT AN ISSUE

            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(82.dp)
                    .background(
                        Color(0xCCEAF6FF),
                        RoundedCornerShape(16.dp)
                    )
                    .border(
                        1.dp,
                        Color(0xFF1677E8),
                        RoundedCornerShape(16.dp)
                    )
                    .clickable {
                    }
                    .padding(12.dp)
            ) {

                Column(
                    modifier = Modifier.fillMaxWidth(),

                    horizontalAlignment =
                        Alignment.CenterHorizontally,

                    verticalArrangement =
                        Arrangement.Center
                ) {

                    Text(
                        text = "📷",
                        fontSize = 25.sp
                    )

                    Text(
                        text = "Report an Issue",
                        color = Color.Black,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }


            // MY COMPLAINTS

            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(82.dp)
                    .background(
                        Color( 0xE6EAF6FF),
                        RoundedCornerShape(16.dp)
                    )
                    .border(
                        width=1.dp,
                        Color(0xFF16E84A),
                        shape=RoundedCornerShape(size=16.dp)
                    )

                    .clickable {
                    }
                    .padding(12.dp)
            ) {

                Column(
                    modifier = Modifier.fillMaxWidth(),

                    horizontalAlignment =
                        Alignment.CenterHorizontally,

                    verticalArrangement =
                        Arrangement.Center
                ) {

                    Text(
                        text = "📋",
                        fontSize = 25.sp
                    )

                    Text(
                        text = "My Complaints",
                        color = Color(0xFF17385E),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }


        // ==================================================
        // RECENT COMPLAINTS
        // ==================================================

        Text(
            text = "Recent Complaints",
            color = Color(0xFF102C57),
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold,

            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(
                    x = maxWidth * 0.07f,
                    y = maxHeight * 0.575f
                )
        )


        Text(
            text = "View All",
            color = Color(0xFF1677E8),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,

            modifier = Modifier
                .align(Alignment.TopEnd)
                .offset(
                    x = -(maxWidth * 0.07f),
                    y = maxHeight * 0.58f
                )
                .clickable {
                }
        )


        // ==================================================
        // COMPLAINT 1
        // ==================================================

        Box(
            modifier = Modifier
                .fillMaxWidth(0.88f)
                .height(70.dp)
                .align(Alignment.TopCenter)
                .offset(
                    y = maxHeight * 0.625f
                )
                .background(
                    Color.White.copy(alpha = 0.96f),
                    RoundedCornerShape(13.dp)
                )
                .border(
                    1.dp,
                    Color(0xFFE0E8F0),
                    RoundedCornerShape(13.dp)
                )
                .padding(10.dp)
        ) {

            Column {

                Text(
                    text = "Garbage not collected",
                    color = Color(0xFF17385E),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text =
                        "MG Road, Near Bus Stand  •  CIV12345",
                    color = Color(0xFF71859B),
                    fontSize = 11.sp
                )

                Text(
                    text =
                        "In Progress   •   21 Apr 2025",
                    color = Color(0xFFE09A22),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }


        // ==================================================
        // COMPLAINT 2
        // ==================================================

        Box(
            modifier = Modifier
                .fillMaxWidth(0.88f)
                .height(70.dp)
                .align(Alignment.TopCenter)
                .offset(
                    y = maxHeight * 0.705f
                )
                .background(
                    Color.White.copy(alpha = 0.96f),
                    RoundedCornerShape(13.dp)
                )
                .border(
                    1.dp,
                    Color(0xFFE0E8F0),
                    RoundedCornerShape(13.dp)
                )
                .padding(10.dp)
        ) {

            Column {

                Text(
                    text = "Street light not working",
                    color = Color(0xFF17385E),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text =
                        "Gandhi Nagar  •  CIV12678",
                    color = Color(0xFF71859B),
                    fontSize = 11.sp
                )

                Text(
                    text =
                        "Pending   •   20 Apr 2025",
                    color = Color(0xFFE09A22),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }


        // ==================================================
        // COMPLAINT 3
        // ==================================================

        Box(
            modifier = Modifier
                .fillMaxWidth(0.88f)
                .height(70.dp)
                .align(Alignment.TopCenter)
                .offset(
                    y = maxHeight * 0.785f
                )
                .background(
                    Color.White.copy(alpha = 0.96f),
                    RoundedCornerShape(13.dp)
                )
                .border(
                    1.dp,
                    Color(0xFFE0E8F0),
                    RoundedCornerShape(13.dp)
                )
                .padding(10.dp)
        ) {

            Column {

                Text(
                    text = "Water leakage",
                    color = Color(0xFF17385E),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text =
                        "Indira Colony  •  CIV12789",
                    color = Color(0xFF71859B),
                    fontSize = 11.sp
                )

                Text(
                    text =
                        "Completed   •   18 Apr 2025",
                    color = Color(0xFF269653),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }


        // ==================================================
        // BOTTOM NAVIGATION
        // ==================================================

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp)
                .align(Alignment.BottomCenter)
                .background(Color.White)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 12.dp,
                        vertical = 8.dp
                    ),

                horizontalArrangement =
                    Arrangement.SpaceAround,

                verticalAlignment =
                    Alignment.CenterVertically
            ) {


                // HOME

                Column(
                    horizontalAlignment =
                        Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "⌂",
                        fontSize = 25.sp,
                        color = Color(0xFF1677E8)
                    )

                    Text(
                        text = "Home",
                        fontSize = 11.sp,
                        color = Color(0xFF1677E8)
                    )
                }


                // MY COMPLAINTS

                Column(
                    horizontalAlignment =
                        Alignment.CenterHorizontally,

                    modifier = Modifier.clickable {
                    }
                ) {

                    Text(
                        text = "▣",
                        fontSize = 23.sp,
                        color = Color(0xFF6E8197)
                    )

                    Text(
                        text = "My Complaints",
                        fontSize = 10.sp,
                        color = Color(0xFF6E8197)
                    )
                }


                // REPORT BUTTON

                Box(
                    modifier = Modifier
                        .size(54.dp)
                        .background(
                            Color(0xFF1677E8),
                            RoundedCornerShape(18.dp)
                        )
                        .clickable {
                        },

                    contentAlignment =
                        Alignment.Center
                ) {

                    Text(
                        text = "+",
                        color = Color.White,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                }


                // ALERTS

                Column(
                    horizontalAlignment =
                        Alignment.CenterHorizontally,

                    modifier = Modifier.clickable {
                    }
                ) {

                    Text(
                        text = "🔔",
                        fontSize = 22.sp
                    )

                    Text(
                        text = "Alerts",
                        fontSize = 10.sp,
                        color = Color(0xFF6E8197)
                    )
                }


                // PROFILE

                Column(
                    horizontalAlignment =
                        Alignment.CenterHorizontally,

                    modifier = Modifier.clickable {
                    }
                ) {

                    Text(
                        text = "\uD83D\uDC64",
                        fontSize = 23.sp,
                        color = Color(0xFF6E8197)
                    )

                    Text(
                        text = "Profile",
                        fontSize = 10.sp,
                        color = Color(0xFF6E8197)
                    )
                }
            }
        }
    }
}


// ======================================================
// ADMIN LOGIN
// ======================================================

@Composable
fun AdminLoginScreen(
    onBack: () -> Unit
) {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Text(
            text = "←",
            color = Color(0xFF102C57),
            fontSize = 30.sp,

            modifier = Modifier
                .padding(
                    start = 15.dp,
                    top = 20.dp
                )
                .clickable {
                    onBack()
                }
        )


        Text(
            text = "Admin Password Login",
            color = Color(0xFF102C57),
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,

            modifier = Modifier.align(
                Alignment.Center
            )
        )
    }
}