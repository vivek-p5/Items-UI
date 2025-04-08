package com.example.itemsui

import androidx.compose.foundation.IndicationNodeFactory
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.ui.node.DelegatableNode

object ScaleIndication:IndicationNodeFactory  {
    override fun create(interactionSource: InteractionSource): DelegatableNode {
        return ScaleNode(interactionSource)
    }

    override fun equals(other: Any?) = other === ScaleIndication

    override fun hashCode() = 100
}