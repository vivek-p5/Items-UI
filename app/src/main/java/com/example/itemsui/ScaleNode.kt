package com.example.itemsui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.node.DrawModifierNode
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ScaleNode(private val interactionSource: InteractionSource) : Modifier.Node(), DrawModifierNode {

    val animatedScalePercent = Animatable(1f)
    var currentPressPosition: Offset = Offset.Zero



    override fun ContentDrawScope.draw() {
        scale(scale = animatedScalePercent.value, pivot = currentPressPosition){
            this@draw.drawContent()
        }
    }

    override fun onAttach() {
        coroutineScope.launch {
            interactionSource.interactions.collectLatest {
                when(it){
                    is PressInteraction.Press -> animateToPress(it.pressPosition)
                    is PressInteraction.Release -> animateToResting()
                    is PressInteraction.Cancel -> animateToResting()
                }
            }
        }
    }

    suspend fun animateToPress(pressPosition: Offset){
        currentPressPosition = pressPosition
        animatedScalePercent.animateTo(0.9f, spring())
    }

    suspend fun animateToResting(){
        animatedScalePercent.animateTo(1f,spring())
    }
}