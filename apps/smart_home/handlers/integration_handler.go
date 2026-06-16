package handlers

import (
	"net/http"

	"smarthome/services"

	"github.com/gin-gonic/gin"
)

type IntegrationHandler struct {
	DeviceService    *services.DeviceService
	TelemetryService *services.TelemetryService
}

func NewIntegrationHandler(
	deviceService *services.DeviceService,
	telemetryService *services.TelemetryService,
) *IntegrationHandler {
	return &IntegrationHandler{
		DeviceService:    deviceService,
		TelemetryService: telemetryService,
	}
}

func (h *IntegrationHandler) RegisterRoutes(router *gin.RouterGroup) {
	router.GET("/devices", h.GetDevices)
	router.GET("/telemetry", h.GetTelemetry)
}

func (h *IntegrationHandler) GetDevices(c *gin.Context) {
	devices, err := h.DeviceService.GetDevices()
	if err != nil {
		c.JSON(http.StatusInternalServerError, gin.H{"error": err.Error()})
		return
	}

	c.JSON(http.StatusOK, devices)
}

func (h *IntegrationHandler) GetTelemetry(c *gin.Context) {
	telemetry, err := h.TelemetryService.GetTelemetry()
	if err != nil {
		c.JSON(http.StatusInternalServerError, gin.H{"error": err.Error()})
		return
	}

	c.JSON(http.StatusOK, telemetry)
}