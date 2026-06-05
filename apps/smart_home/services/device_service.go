package services

import (
	"encoding/json"
	"net/http"
	"time"
)

type DeviceService struct {
	BaseURL    string
	HTTPClient *http.Client
}

type Device struct {
	ID           string `json:"id"`
	HouseID      string `json:"houseId"`
	TypeID       string `json:"typeId"`
	Name         string `json:"name"`
	SerialNumber string `json:"serialNumber"`
	Status       string `json:"status"`
}

func NewDeviceService(baseURL string) *DeviceService {
	return &DeviceService{
		BaseURL: baseURL,
		HTTPClient: &http.Client{
			Timeout: 10 * time.Second,
		},
	}
}

func (s *DeviceService) GetDevices() ([]Device, error) {
	resp, err := s.HTTPClient.Get(s.BaseURL + "/devices")
	if err != nil {
		return nil, err
	}
	defer resp.Body.Close()

	var devices []Device
	err = json.NewDecoder(resp.Body).Decode(&devices)

	return devices, err
}