package co.com.udea.model.geographicalzones;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Builder(toBuilder = true)
@RequiredArgsConstructor
@AllArgsConstructor
public class GeographicalZones {
    private String id;
    private String geoZoneName;
    private List<List<String>> geoZoneCoords;
}
