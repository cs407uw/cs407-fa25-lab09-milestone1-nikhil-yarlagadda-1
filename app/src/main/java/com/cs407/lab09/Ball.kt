package com.cs407.lab09

import androidx.compose.ui.unit.Velocity

/**
 * Represents a ball that can move. (No Android UI imports!)
 *
 * Constructor parameters:
 * - backgroundWidth: the width of the background, of type Float
 * - backgroundHeight: the height of the background, of type Float
 * - ballSize: the width/height of the ball, of type Float
 */
class Ball(
    private val backgroundWidth: Float,
    private val backgroundHeight: Float,
    private val ballSize: Float
) {
    var posX = 0f
    var posY = 0f
    var velocityX = 0f
    var velocityY = 0f
    private var accX = 0f
    private var accY = 0f

    private var isFirstUpdate = true

    init {
        // TODO: Call reset()
        reset()
    }

    /**
     * Updates the ball's position and velocity based on the given acceleration and time step.
     * (See lab handout for physics equations)
     */
    fun updatePositionAndVelocity(xAcc: Float, yAcc: Float, dT: Float) {
        if(isFirstUpdate) {
            isFirstUpdate = false
            accX = xAcc
            accY = yAcc
            velocityX = 0f
            velocityY = 0f
            return
        }
        accX = xAcc
        accY = yAcc

        val vx = velocityX + 0.5*(accX + xAcc)*dT
        val vy = velocityY + 0.5*(accY + yAcc)*dT

        val dx = velocityX * (dT) + (1f/6f)*(dT*dT) * (3*accX + xAcc)
        val dy = velocityY * (dT) + (1f/6f)*(dT*dT) * (3*accY + yAcc)

        velocityX = vx.toFloat()
        velocityY = vy.toFloat()

        posX += dx
        posY += dy
    }

    /**
     * Ensures the ball does not move outside the boundaries.
     * When it collides, velocity and acceleration perpendicular to the
     * boundary should be set to 0.
     */
    fun checkBoundaries() {
        // TODO: implement the checkBoundaries function
        // (Check all 4 walls: left, right, top, bottom)

        if(posX - ballSize/2 < 0f) {
            posX = ballSize/2
            velocityX = 0f
            accX = 0f
        }
        // Right wall
        if(posX + ballSize/2 > backgroundWidth) {
            posX = backgroundWidth - ballSize/2
            velocityX = 0f
            accX = 0f
        }
        // Top wall
        if(posY - ballSize/2 < 0f) {
            posY = ballSize/2
            velocityY = 0f
            accY = 0f
        }
        // Bottom wall
        if(posY + ballSize/2 > backgroundHeight) {
            posY = backgroundHeight - ballSize/2
            velocityY = 0f
            accY = 0f
        }
    }

    /**
     * Resets the ball to the center of the screen with zero
     * velocity and acceleration.
     */
    fun reset() {
        // TODO: implement the reset function
        // (Reset posX, posY, velocityX, velocityY, accX, accY, isFirstUpdate)
        posX = backgroundWidth / 2f
        posY = backgroundHeight / 2f
        velocityX = 0f
        velocityY = 0f
        accX = 0f
        accY = 0f
        isFirstUpdate = true
    }
}