/*
 * lightning_induced_voltage_airplane.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:34 by COMSOL 6.3.0.290. */
public class lightning_induced_voltage_airplane {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\RF_Module\\ESD_and_Lightning_Surge");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("temw", "TransientElectromagneticWaves", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/temw", true);

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1")
         .set("filename", "lightning_induced_voltage_airplane.mphbin");
    model.component("comp1").geom("geom1").run("imp1");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "zx");
    model.component("comp1").geom("geom1").feature("wp1").set("quicky", "-30[m]");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("type", "curve");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("pos", new int[]{2, 0});
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").selection("input").set("imp1", "wp1");
    model.component("comp1").geom("geom1").feature("rot1").set("axistype", "y");
    model.component("comp1").geom("geom1").feature("rot1").set("rot", -10);
    model.component("comp1").geom("geom1").run("rot1");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1")
         .set("table", new String[][]{{"-60.00", "-20.00", "400.00"}, 
         {"-51.58", "-17.16", "394.33"}, 
         {"-57.75", "-11.40", "387.08"}, 
         {"-50.60", "-14.76", "375.41"}, 
         {"-51.59", "-12.51", "367.29"}, 
         {"-52.98", "-13.30", "350.61"}, 
         {"-48.69", "-10.55", "343.99"}, 
         {"-51.15", "-11.19", "330.89"}, 
         {"-53.77", "-10.15", "322.60"}, 
         {"-45.51", "-9.94", "316.06"}, 
         {"-52.27", "-4.64", "307.24"}, 
         {"-48.32", "-5.69", "291.94"}, 
         {"-47.40", "-10.76", "282.51"}, 
         {"-44.16", "-9.63", "270.73"}, 
         {"-44.48", "-8.87", "262.49"}, 
         {"-48.14", "-6.83", "254.23"}, 
         {"-42.09", "-6.31", "245.41"}, 
         {"-43.69", "-2.73", "230.88"}, 
         {"-41.00", "0.74", "225.24"}, 
         {"-40.18", "1.36", "215.95"}, 
         {"-42.53", "1.70", "204.06"}, 
         {"-40.36", "1.88", "192.03"}, 
         {"-39.17", "0.72", "181.72"}, 
         {"-36.82", "4.02", "172.76"}, 
         {"-34.46", "1.73", "162.97"}, 
         {"-34.73", "-0.82", "150.94"}, 
         {"-32.59", "5.08", "141.07"}, 
         {"-31.87", "1.76", "131.61"}, 
         {"-38.22", "6.13", "121.07"}, 
         {"-37.49", "5.22", "115.81"}, 
         {"-33.50", "4.64", "100.27"}, 
         {"-32.74", "9.75", "95.77"}, 
         {"-29.64", "6.09", "87.60"}, 
         {"-27.31", "6.65", "73.05"}, 
         {"-27.46", "10.79", "63.33"}, 
         {"-27.50", "11.55", "51.13"}, 
         {"-28.40", "13.68", "42.19"}, 
         {"-30.50", "8.07", "32.58"}, 
         {"-25.27", "15.36", "20.69"}, 
         {"-30.00", "10.00", "0.00"}, 
         {"-30.00", "10.00", "-6.00"}, 
         {"-25.96", "18.93", "-9.98"}, 
         {"-34.33", "16.88", "-18.07"}, 
         {"-29.74", "15.50", "-32.26"}, 
         {"-35.10", "16.80", "-44.51"}, 
         {"-35.60", "22.81", "-47.31"}, 
         {"-42.74", "19.39", "-56.34"}, 
         {"-43.91", "22.37", "-68.62"}, 
         {"-45.22", "27.62", "-79.72"}, 
         {"-49.94", "26.12", "-87.59"}, 
         {"-47.99", "29.38", "-97.48"}, 
         {"-52.97", "29.53", "-112.46"}, 
         {"-56.77", "35.54", "-115.12"}, 
         {"-55.05", "36.16", "-129.03"}, 
         {"-61.29", "32.18", "-136.05"}, 
         {"-61.62", "39.42", "-148.34"}, 
         {"-65.28", "41.80", "-154.14"}, 
         {"-66.86", "37.91", "-166.10"}, 
         {"-71.83", "40.43", "-178.33"}, 
         {"-80.00", "40.00", "-200.00"}});
    model.component("comp1").geom("geom1").feature("pol1").set("selresult", true);
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("sph1", "Sphere");
    model.component("comp1").geom("geom1").feature("sph1").set("r", "1[km]");
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").view("view1").hideObjects().create("hide1");
    model.component("comp1").view("view1").hideObjects("hide1").init(2);
    model.component("comp1").view("view1").hideObjects("hide1").set("sph1", 1, 2, 5, 6);
    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").label("\u6ca1\u6709\u524d\u7a97\u7684\u98de\u673a");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1")
         .set(5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 93, 94, 95, 96, 97, 98, 100, 101, 103, 104, 105, 106, 108, 109, 110, 113, 116, 117, 118, 120, 121, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u98de\u673a\u6574\u4f53");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2")
         .set(5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 113, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u5bfc\u7ebf");
    model.component("comp1").selection("sel3").geom(1);
    model.component("comp1").selection("sel3").set(290, 291, 300, 312);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u7a7a\u6c14");
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("electricconductivity", new String[]{"0"});

    model.component("comp1").physics("temw").create("sctr1", "Scattering", 2);
    model.component("comp1").physics("temw").feature("sctr1").selection().set(1, 2, 3, 4, 111, 112, 114, 115);
    model.component("comp1").physics("temw").create("edc1", "EdgeCurrent", 1);
    model.component("comp1").physics("temw").feature("edc1").set("CurrentPulseType", "Lightning");
    model.component("comp1").physics("temw").feature("edc1").selection().named("geom1_pol1_edg");
    model.component("comp1").physics("temw").feature("edc1").set("v_p", "c_const/3");
    model.component("comp1").physics("temw").feature("edc1").set("reverseDirection", true);
    model.component("comp1").physics("temw").create("pec2", "PerfectElectricConductor", 2);
    model.component("comp1").physics("temw").feature("pec2").selection().named("sel1");
    model.component("comp1").physics("temw").create("pec3", "PerfectElectricConductor", 2);
    model.component("comp1").physics("temw").feature("pec3").selection().named("sel2");

    model.component("comp1").mesh("mesh1").autoMeshSize(8);

    model.component("comp1").physics("temw").prop("MeshControl")
         .set("PhysicsControlledMeshMaximumElementSize", "300[m]");

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().named("sel3");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmax", "0.3[m]");
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").selection().named("geom1_pol1_edg");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("hmax", "5[m]");
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size3", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size3").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size3").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size3").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size3").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size3").set("hmax", "3.5[m]");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76 1\uff0c\u5b8c\u5168\u672a\u5c4f\u853d");
    model.study("std1").setGenPlots(false);
    model.study("std1").feature("time").set("tunit", "\u00b5s");
    model.study("std1").feature("time").set("tlist", "range(0,0.05,6)");
    model.study("std1").feature("time").set("useadvanceddisable", true);
    model.study("std1").feature("time").set("disabledphysics", new String[]{"temw/pec2", "temw/pec3"});
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("tstepsgenalpha", "manual");
    model.sol("sol1").feature("t1").set("timestepgenalpha", "0.05[us]");

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/temw", true);
    model.study("std2").label("\u7814\u7a76 2\uff0c\u524d\u7a97\u672a\u5c4f\u853d");
    model.study("std2").setGenPlots(false);
    model.study("std2").feature("time").set("tunit", "\u00b5s");
    model.study("std2").feature("time").set("tlist", "range(0,0.05,6)");
    model.study("std2").feature("time").set("useadvanceddisable", true);
    model.study("std2").feature("time").set("disabledphysics", new String[]{"temw/pec3"});
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("t1").set("tstepsgenalpha", "manual");
    model.sol("sol2").feature("t1").set("timestepgenalpha", "0.05[us]");

    model.study().create("std3");
    model.study("std3").create("time", "Transient");
    model.study("std3").feature("time").setSolveFor("/physics/temw", true);
    model.study("std3").label("\u7814\u7a76 3\uff0c\u5b8c\u5168\u5c4f\u853d");
    model.study("std3").setGenPlots(false);
    model.study("std3").feature("time").set("tunit", "\u00b5s");
    model.study("std3").feature("time").set("tlist", "range(0,0.05,6)");
    model.study("std3").feature("time").set("useadvanceddisable", true);
    model.study("std3").feature("time").set("disabledphysics", new String[]{"temw/pec2"});
    model.study("std3").showAutoSequences("all");

    model.sol("sol3").feature("t1").set("tstepsgenalpha", "manual");
    model.sol("sol3").feature("t1").set("timestepgenalpha", "0.05[us]");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().numerical().create("int1", "IntLine");
    model.result().numerical("int1").set("intsurface", true);
    model.result().numerical("int1").selection().named("sel3");
    model.result().numerical("int1").setIndex("expr", "temw.Ex*t1x+temw.Ey*t1y+temw.Ez*t1z", 0);
    model.result().numerical("int1").setIndex("descr", "\u7535\u538b", 0);
    model.result().numerical().duplicate("int2", "int1");
    model.result().numerical("int2").set("data", "dset2");
    model.result().numerical().duplicate("int3", "int2");
    model.result().numerical("int3").set("data", "dset3");
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u7ebf\u79ef\u5206 1");
    model.result().numerical("int1").set("table", "tbl1");
    model.result().numerical("int1").setResult();
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u7ebf\u79ef\u5206 2");
    model.result().numerical("int2").set("table", "tbl2");
    model.result().numerical("int2").setResult();
    model.result().table().create("tbl3", "Table");
    model.result().table("tbl3").comments("\u7ebf\u79ef\u5206 3");
    model.result().numerical("int3").set("table", "tbl3");
    model.result().numerical("int3").setResult();
    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").label("\u5bfc\u7ebf\u4e0a\u7684\u611f\u5e94\u7535\u538b");
    model.result("pg1").set("data", "none");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "\u611f\u5e94\u7535\u538b\uff0cV");
    model.result("pg1").set("legendpos", "lowerright");
    model.result("pg1").create("tblp1", "Table");
    model.result("pg1").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg1").feature("tblp1").set("linewidth", "preference");
    model.result("pg1").run();
    model.result("pg1").create("tblp2", "Table");
    model.result("pg1").feature("tblp2").set("markerpos", "datapoints");
    model.result("pg1").feature("tblp2").set("linewidth", "preference");
    model.result("pg1").run();
    model.result("pg1").feature("tblp1").set("legend", true);
    model.result("pg1").feature("tblp1").set("legendmethod", "manual");
    model.result("pg1").feature("tblp1").setIndex("legends", "\u5b8c\u5168\u672a\u5c4f\u853d", 0);
    model.result("pg1").feature("tblp1").create("gmrk1", "GraphMarker");
    model.result("pg1").feature("tblp1").feature("gmrk1").set("linewidth", "preference");
    model.result("pg1").run();
    model.result("pg1").feature("tblp1").feature("gmrk1").set("anchorpoint", "middleright");
    model.result("pg1").run();
    model.result("pg1").feature("tblp2").set("table", "tbl2");
    model.result("pg1").feature("tblp2").set("legend", true);
    model.result("pg1").feature("tblp2").set("legendmethod", "manual");
    model.result("pg1").feature("tblp2").setIndex("legends", "\u524d\u7a97\u672a\u5c4f\u853d", 0);
    model.result("pg1").feature("tblp2").create("gmrk1", "GraphMarker");
    model.result("pg1").feature("tblp2").feature("gmrk1").set("linewidth", "preference");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").create("tblp3", "Table");
    model.result("pg1").feature("tblp3").set("markerpos", "datapoints");
    model.result("pg1").feature("tblp3").set("linewidth", "preference");
    model.result("pg1").feature("tblp3").set("table", "tbl3");
    model.result("pg1").feature("tblp3").set("legend", true);
    model.result("pg1").feature("tblp3").set("legendmethod", "manual");
    model.result("pg1").feature("tblp3").setIndex("legends", "\u5b8c\u5168\u5c4f\u853d", 0);
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").set("data", "dset2");
    model.result("pg2").setIndex("looplevel", 51, 0);
    model.result("pg2").label("\u8868\u9762\u7535\u573a");
    model.result("pg2").set("edges", false);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").create("sel1", "Selection");
    model.result("pg2").feature("surf1").feature("sel1").selection().named("sel2");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("rangecoloractive", true);
    model.result("pg2").feature("surf1").set("rangecolormin", 0);
    model.result("pg2").feature("surf1").set("rangecolormax", "3e5");

    model.component("comp1").view("view1").set("showgrid", false);

    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg2").run();
    model.result("pg2").create("line1", "Line");
    model.result("pg2").feature("line1").create("sel1", "Selection");
    model.result("pg2").feature("line1").feature("sel1").selection().named("geom1_pol1_edg");
    model.result("pg2").run();
    model.result("pg2").feature("line1").set("rangecoloractive", true);
    model.result("pg2").feature("line1").set("linetype", "tube");
    model.result("pg2").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg2").feature("line1").set("radiusexpr", "0.5");
    model.result("pg2").feature("line1").set("inheritplot", "surf1");
    model.result("pg2").run();

    model.title("\u98de\u673a\u4e0a\u7535\u7ebf\u7684\u96f7\u7535\u611f\u5e94\u7535\u538b");

    model
         .description("\u672c\u6a21\u578b\u6f14\u793a\u5982\u4f55\u8ba1\u7b97\u4e0d\u540c\u7535\u78c1\u5c4f\u853d\u6761\u4ef6\u4e0b\uff0c\u98de\u673a\u5185\u90e8\u5bfc\u7ebf\u73af\u7684\u611f\u5e94\u7535\u538b\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("lightning_induced_voltage_airplane.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
