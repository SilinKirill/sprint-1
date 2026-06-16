package services

import (
	"encoding/json"
	"net/http"
	"time"
)

type TelemetryService struct {
	BaseURL    string
	HTTPClient *http.Client
}

func NewTelemetryService(baseURL string) *TelemetryService {
	return &TelemetryService{
		BaseURL: baseURL,
		HTTPClient: &http.Client{
			Timeout: 10 * time.Second,
		},
	}
}

func (s *TelemetryService) GetTelemetry() ([]map[string]interface{}, error) {
	resp, err := s.HTTPClient.Get(s.BaseURL + "/telemetry")
	if err != nil {
		return nil, err
	}
	defer resp.Body.Close()

	var telemetry []map[string]interface{}
	err = json.NewDecoder(resp.Body).Decode(&telemetry)

	return telemetry, err
}