package com.shoxrux.presentation.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shoxrux.presentation.R
import com.shoxrux.presentation.ui.colors.BrandColor
import com.shoxrux.presentation.ui.colors.BrandSecondary
import com.shoxrux.presentation.ui.colors.TransParent
import com.shoxrux.presentation.ui.colors.White
import com.shoxrux.presentation.ui.trheme.AppTypography
import com.threemoly.composemask.universalTransformation

@Composable
fun TextFieldWithHeader(
    header: String,
    value: String,
    onValueChange: (String) -> Unit,
    isEmpty: Boolean
) {

    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    Column(modifier = Modifier.fillMaxWidth()) {

        Text(
            text = header,
            style = AppTypography.titleSmall
        )
        Card(
            modifier = Modifier
                .padding(top = 5.dp)
                .fillMaxWidth()
                .height(55.dp),
            shape = RoundedCornerShape(12.dp),
            border = if (isFocused) {
                BorderStroke(width = 1.dp, color = BrandColor)
            } else if (isEmpty) {
                BorderStroke(width = 1.dp, color = Color.Red)
            } else {
                null
            }
        ) {

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BrandSecondary),
                value = value,
                textStyle = AppTypography.bodySmall,
                singleLine = true,
                onValueChange = {
                    onValueChange(it)
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = BrandSecondary,
                    unfocusedContainerColor = BrandSecondary,
                    focusedIndicatorColor = TransParent,
                    unfocusedIndicatorColor = TransParent
                ),
                interactionSource = interactionSource
            )
        }

    }
}

@Composable
fun BigTextField(
    header: String,
    value: String,
    onValueChange: (String) -> Unit
) {

    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    Column(modifier = Modifier.fillMaxWidth()) {

        Text(
            text = header,
            style = AppTypography.titleSmall
        )
        Card(
            modifier = Modifier
                .padding(top = 5.dp)
                .fillMaxWidth()
                .height(145.dp),
            shape = RoundedCornerShape(12.dp),
            border = if (isFocused) {
                BorderStroke(width = 1.dp, color = BrandColor)
            } else {
                null
            }
        ) {

            TextField(

                modifier = Modifier
                    .fillMaxSize()
                    .background(BrandSecondary),
                value = value,
                textStyle = AppTypography.bodySmall,
                onValueChange = {
                    onValueChange(it)
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = BrandSecondary,
                    unfocusedContainerColor = BrandSecondary,
                    focusedIndicatorColor = TransParent,
                    unfocusedIndicatorColor = TransParent
                ),
                interactionSource = interactionSource
            )
        }

    }
}

@Composable
fun ButtonWithUnderText(
    onButtonClick: () -> Unit,
    buttonText: String,
    underText: String,
    underTextClickable: String,
    underTextOnClick: () -> Unit
) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            onClick = {
                onButtonClick.invoke()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = BrandColor,
                contentColor = White
            )
        ) {

            Text(
                text = buttonText,
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                fontSize = 18.sp,
                color = White

            )

        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier.padding(end = 5.dp),
                text = underText,
                style = AppTypography.bodySmall
            )

            Text(
                modifier = Modifier.clickable {
                    underTextOnClick.invoke()
                },
                text = underTextClickable,
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.poppins_bold)),
                color = BrandColor
            )

        }
    }
}

@Composable
fun MaskedTextField(
    header: String,
    value: String,
    onValueChange: (String) -> Unit,
) {

    val visualTransformation = universalTransformation(
        mask = "mm-dd-yyyy",
        maskCharToKeep = '-'
    )

    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    Column(modifier = Modifier.fillMaxWidth()) {

        Text(
            text = header,
            style = AppTypography.titleSmall
        )
        Card(
            modifier = Modifier
                .padding(top = 5.dp)
                .fillMaxWidth()
                .height(55.dp),
            shape = RoundedCornerShape(12.dp),
            border = if (isFocused) {
                BorderStroke(width = 1.dp, color = BrandColor)
            } else {
                null
            }
        ) {

            OutlinedTextField(
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Phone
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BrandSecondary),
                value = value,
                textStyle = AppTypography.bodySmall,
                singleLine = true,
                onValueChange = {
                    onValueChange(it)
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = BrandSecondary,
                    unfocusedContainerColor = BrandSecondary,
                    focusedIndicatorColor = TransParent,
                    unfocusedIndicatorColor = TransParent
                ),
                interactionSource = interactionSource,
                visualTransformation = visualTransformation

            )
        }

    }
}

@Composable
fun HeaderWithBackButton(onBackClick: () -> Unit, header: String) {

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {

        IconButton(
            modifier = Modifier.align(Alignment.CenterStart),
            onClick = onBackClick
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_arrow_left),
                contentDescription = null
            )
        }

        Text(
            modifier = Modifier.align(Alignment.Center),
            text = header,
            style = AppTypography.headlineSmall
        )

    }

}

@Composable
fun AppButton(onClick: () -> Unit, buttonText: String) {

    Button(
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = BrandColor,
            contentColor = BrandSecondary
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        onClick = {
            onClick.invoke()
        }
    ) {
        Text(
            text = buttonText,
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.poppins_regular)),
            color = BrandSecondary
        )
    }

}