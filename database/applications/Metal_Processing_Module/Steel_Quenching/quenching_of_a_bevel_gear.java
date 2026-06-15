/*
 * quenching_of_a_bevel_gear.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:06 by COMSOL 6.3.0.290. */
public class quenching_of_a_bevel_gear {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Metal_Processing_Module\\Steel_Quenching");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("ht", "HeatTransfer", "geom1");
    model.component("comp1").physics("ht").prop("ShapeProperty").set("order_temperature", "1");
    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics("solid").prop("StructuralTransientBehavior")
         .set("StructuralTransientBehavior", "Quasistatic");
    model.component("comp1").physics().create("audc", "AusteniteDecomposition", "geom1");
    model.component("comp1").physics("audc").prop("SolidMechanics").set("trip", "1");
    model.component("comp1").physics("audc").prop("SolidMechanics").set("plasticity", "0");
    model.component("comp1").physics("audc").prop("SolidMechanics").set("dilstrain", "1");
    model.component("comp1").physics("audc").prop("HeatTransfer").set("latentheat", "1");
    model.component("comp1").physics("audc").prop("ShapeProperty").set("order_straindiscr_disc", "2");

    model.component("comp1").multiphysics().create("lht1", "PhaseTransformationLatentHeat", 3);
    model.component("comp1").multiphysics("lht1").set("Metphase_physics", "audc");
    model.component("comp1").multiphysics("lht1").set("HeatTransfer_physics", "ht");
    model.component("comp1").multiphysics("lht1").selection().all();
    model.component("comp1").multiphysics().create("ptstr1", "PhaseTransformationStrain", 3);
    model.component("comp1").multiphysics("ptstr1").set("Metphase_physics", "audc");
    model.component("comp1").multiphysics("ptstr1").set("SolidMechanics_physics", "solid");
    model.component("comp1").multiphysics("ptstr1").selection().all();

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/ht", true);
    model.study("std1").feature("time").setSolveFor("/physics/solid", true);
    model.study("std1").feature("time").setSolveFor("/physics/audc", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/lht1", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/ptstr1", true);

    model.component("comp1").geom("geom1").run();

    model.component("comp1").mesh("mesh1").create("imp1", "Import");
    model.component("comp1").mesh("mesh1").feature("imp1").set("filename", "quenching_of_a_bevel_gear_mesh.mphbin");
    model.component("comp1").mesh("mesh1").feature("imp1").importData();

    model.param().set("Tinit", "900[degC]");
    model.param().descr("Tinit", "Initial temperature");
    model.param().set("Tamb", "60[degC]");
    model.param().descr("Tamb", "Ambient (oil) temperature");

    model.func().create("int1", "Interpolation");
    model.func("int1").set("source", "file");
    model.func("int1").set("filename", "quenching_of_a_bevel_gear_htc.txt");
    model.func("int1").setIndex("fununit", "W/(m^2*K)", 0);
    model.func("int1").setIndex("argunit", "degC", 0);
    model.func("int1").set("interp", "piecewisecubic");

    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").physics("ht").feature("init1").set("Tinit", "Tinit");
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 2);
    model.component("comp1").physics("ht").feature("hf1").selection().all();
    model.component("comp1").physics("ht").feature("hf1").selection().set(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 13, 14);
    model.component("comp1").physics("ht").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf1").set("h", "int1(T)");
    model.component("comp1").physics("ht").feature("hf1").set("Text", "Tamb");
    model.component("comp1").physics("solid").feature("lemm1").create("plsty1", "Plasticity", 3);
    model.component("comp1").physics("solid").feature("lemm1").feature("plsty1")
         .set("IsotropicHardeningModel", "HardeningFunction");
    model.component("comp1").physics("solid").create("sym1", "SymmetrySolid", 2);
    model.component("comp1").physics("solid").feature("sym1").selection().set(1, 12);
    model.component("comp1").physics("solid").create("disp1", "Displacement0", 0);
    model.component("comp1").physics("solid").feature("disp1").selection().set(13);
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("audc").prop("MaterialProperties").runCommand("makecompoundmaterial");
    model.component("comp1").physics("audc").prop("SolidMechanics").set("plasticity", true);
    model.component("comp1").physics("audc").prop("SolidMechanics").set("dilstraintype", "densitybased");
    model.component("comp1").physics("audc").feature("ptran1").active(false);
    model.component("comp1").physics("audc").feature("ptran2").active(false);
    model.component("comp1").physics("audc").feature("ptran3").active(false);
    model.component("comp1").physics("audc").feature("ptran4").active(false);
    model.component("comp1").physics("audc").create("ptran5", "PhaseTransformation");
    model.component("comp1").physics("audc").feature("ptran5").label("General Steel, Austenite to Ferrite");
    model.component("comp1").physics("audc").feature("ptran5")
         .comments("\n    Alloy calculated with JMatPro\n    JMatPro version 14.0\n    Material type = General Steel\n    ALLOY_ID_WT%  \n    ALLOY_ID_AT%  \n    GRAIN_SIZE_ASTM = \n    GRAIN_SIZE_MICRONS = \n    AUSTENITISATION_TEMP_C = 860.0\n    Cooling (C/s) = \n    FOR MAGNETIC PERMEABILITY:\n    HARDNESS = 400VPN\n    MICROSTRUCTURE = Quenched and tempered (fine)\n  ");

    model.nodeGroup().create("grp1", "Definitions", "comp1");
    model.nodeGroup("grp1").label("General Steel, Austenite to Ferrite");
    model.nodeGroup().move("grp1", 1);

    model.component("comp1").func().create("int2", "Interpolation");

    model.nodeGroup("grp1").add("func", "int2");

    model.component("comp1").func("int2").set("filename", "Transformationdata.txt");
    model.component("comp1").func("int2")
         .set("funcs", new String[][]{{"int2_t0001", "1"}, 
         {"int2_t0005", "2"}, 
         {"int2_t001", "3"}, 
         {"int2_t002", "4"}, 
         {"int2_t003", "5"}, 
         {"int2_t004", "6"}, 
         {"int2_t005", "7"}, 
         {"int2_t01", "8"}, 
         {"int2_t015", "9"}, 
         {"int2_t02", "10"}, 
         {"int2_t025", "11"}, 
         {"int2_t03", "12"}, 
         {"int2_t035", "13"}, 
         {"int2_t04", "14"}, 
         {"int2_t045", "15"}, 
         {"int2_t05", "16"}, 
         {"int2_t055", "17"}, 
         {"int2_t06", "18"}, 
         {"int2_t065", "19"}, 
         {"int2_t07", "20"}, 
         {"int2_t075", "21"}, 
         {"int2_t08", "22"}, 
         {"int2_t085", "23"}, 
         {"int2_t09", "24"}, 
         {"int2_t095", "25"}, 
         {"int2_t099", "26"}, 
         {"int2_xieq", "27"}});
    model.component("comp1").func().create("int3", "Interpolation");

    model.nodeGroup("grp1").add("func", "int3");

    model.component("comp1").func("int3").set("filename", "Transformationdatarates.txt");
    model.component("comp1").func("int3").set("nargs", 2);
    model.component("comp1").func("int3").setIndex("fununit", "1/s", 0);
    model.component("comp1").func("int3").setIndex("argunit", "degC", 0);
    model.component("comp1").func("int3").setIndex("argunit", 1, 1);
    model.component("comp1").func("int3").setIndex("funcs", "int3_A", 0, 0);
    model.component("comp1").func("int3")
         .importData("C:\\Users\\andersw\\AppData\\Local\\Temp\\csjavabridge80355\\Transformationdatarates14586083952362288552.txt");

    model.component("comp1").physics("audc").feature("ptran5").set("t1", "int2_t0001(audc.T)");
    model.component("comp1").physics("audc").feature("ptran5").set("xi1rel", "0.001");
    model.component("comp1").physics("audc").feature("ptran5").set("temperaturelimits", "1");
    model.component("comp1").physics("audc").feature("ptran5").set("Tl", "340.0[degC]");
    model.component("comp1").physics("audc").feature("ptran5").set("Tu", "780.0[degC]");
    model.component("comp1").physics("audc").feature("ptran5").set("t2", "int2_t099(audc.T)");
    model.component("comp1").physics("audc").feature("ptran5").set("xi2rel", "0.99");
    model.component("comp1").physics("audc").feature("ptran5").set("ldformulation", "ttt");
    model.component("comp1").physics("audc").feature("ptran5").set("xieq", "int2_xieq(audc.T)");
    model.component("comp1").physics("audc").feature("ptran5").set("jmakformulation", "ttt");
    model.component("comp1").physics("audc").feature("ptran5").set("xieqjmak", "int2_xieq(audc.T)");
    model.component("comp1").physics("audc").feature("ptran5").set("kvformulation", "kvfactor");
    model.component("comp1").physics("audc").feature("ptran5")
         .set("xi0drate", "0.254129/(int2_t01(audc.T)-int2_t005(audc.T))");
    model.component("comp1").physics("audc").feature("ptran5")
         .set("Auser", "int3_A(audc.T,audc.ptran5.xi/int2_xieq(audc.T))*(audc.ptran5.xi<int2_xieq(audc.T))");
    model.component("comp1").physics("audc").feature("ptran5").set("ptModel", "UserDefined");

    model.component("comp1").func("int2")
         .importData("C:\\Users\\andersw\\AppData\\Local\\Temp\\csjavabridge80355\\Transformationdata16775821407233014963.txt");
    model.component("comp1").func("int2")
         .set("fununit", new String[]{"s", "s", "s", "s", "s", "s", "s", "s", "s", "s", 
         "s", "s", "s", "s", "s", "s", "s", "s", "s", "s", 
         "s", "s", "s", "s", "s", "s", "1"});
    model.component("comp1").func("int2").set("argunit", "degC");
    model.component("comp1").func().create("int4", "Interpolation");

    model.nodeGroup("grp1").add("func", "int4");

    model.component("comp1").func("int4").set("filename", "TRIP.txt");
    model.component("comp1").func("int4").set("funcs", new String[][]{{"int4_TRIP", "1"}});

    model.component("comp1").physics("audc").feature("ptran5").set("Ktrip", "2/3*int4_TRIP(audc.T)");
    model.component("comp1").physics("audc").feature("ptran5").set("trip", true);
    model.component("comp1").physics("audc").prop("SolidMechanics").set("trip", true);

    model.component("comp1").func("int4")
         .importData("C:\\Users\\andersw\\AppData\\Local\\Temp\\csjavabridge80355\\TRIP7671347827998785483.txt");
    model.component("comp1").func("int4").set("fununit", new String[]{"1/MPa"});
    model.component("comp1").func("int4").set("argunit", "degC");
    model.component("comp1").func().create("int5", "Interpolation");

    model.nodeGroup("grp1").add("func", "int5");

    model.component("comp1").func("int5").set("filename", "LatentHeat.txt");
    model.component("comp1").func("int5").set("funcs", new String[][]{{"int5_LatentHeat", "1"}});

    model.component("comp1").physics("audc").feature("ptran5").set("dH", "int5_LatentHeat(audc.T)");
    model.component("comp1").physics("audc").prop("HeatTransfer").set("latentheat", true);

    model.component("comp1").func("int5")
         .importData("C:\\Users\\andersw\\AppData\\Local\\Temp\\csjavabridge80355\\LatentHeat12069770986291418844.txt");
    model.component("comp1").func("int5").set("fununit", new String[]{"J/cm^3"});
    model.component("comp1").func("int5").set("argunit", "degC");

    model.component("comp1").physics("audc").create("ptran6", "PhaseTransformation");
    model.component("comp1").physics("audc").feature("ptran6").label("General Steel, Austenite to Pearlite");
    model.component("comp1").physics("audc").feature("ptran6")
         .comments("\n    Alloy calculated with JMatPro\n    JMatPro version 14.0\n    Material type = General Steel\n    ALLOY_ID_WT%  \n    ALLOY_ID_AT%  \n    GRAIN_SIZE_ASTM = \n    GRAIN_SIZE_MICRONS = \n    AUSTENITISATION_TEMP_C = 860.0\n    Cooling (C/s) = \n    FOR MAGNETIC PERMEABILITY:\n    HARDNESS = 400VPN\n    MICROSTRUCTURE = Quenched and tempered (fine)\n  ");

    model.nodeGroup().create("grp2", "Definitions", "comp1");
    model.nodeGroup("grp2").label("General Steel, Austenite to Pearlite");
    model.nodeGroup().move("grp2", 2);

    model.component("comp1").func().create("int6", "Interpolation");

    model.nodeGroup("grp2").add("func", "int6");

    model.component("comp1").func("int6").set("filename", "Transformationdata.txt");
    model.component("comp1").func("int6")
         .set("funcs", new String[][]{{"int6_t0001", "1"}, 
         {"int6_t0005", "2"}, 
         {"int6_t001", "3"}, 
         {"int6_t002", "4"}, 
         {"int6_t003", "5"}, 
         {"int6_t004", "6"}, 
         {"int6_t005", "7"}, 
         {"int6_t01", "8"}, 
         {"int6_t015", "9"}, 
         {"int6_t02", "10"}, 
         {"int6_t025", "11"}, 
         {"int6_t03", "12"}, 
         {"int6_t035", "13"}, 
         {"int6_t04", "14"}, 
         {"int6_t045", "15"}, 
         {"int6_t05", "16"}, 
         {"int6_t055", "17"}, 
         {"int6_t06", "18"}, 
         {"int6_t065", "19"}, 
         {"int6_t07", "20"}, 
         {"int6_t075", "21"}, 
         {"int6_t08", "22"}, 
         {"int6_t085", "23"}, 
         {"int6_t09", "24"}, 
         {"int6_t095", "25"}, 
         {"int6_t099", "26"}, 
         {"int6_xieq", "27"}});
    model.component("comp1").func().create("int7", "Interpolation");

    model.nodeGroup("grp2").add("func", "int7");

    model.component("comp1").func("int7").set("filename", "Transformationdatarates.txt");
    model.component("comp1").func("int7").set("nargs", 2);
    model.component("comp1").func("int7").setIndex("fununit", "1/s", 0);
    model.component("comp1").func("int7").setIndex("argunit", "degC", 0);
    model.component("comp1").func("int7").setIndex("argunit", 1, 1);
    model.component("comp1").func("int7").setIndex("funcs", "int7_A", 0, 0);
    model.component("comp1").func("int7")
         .importData("C:\\Users\\andersw\\AppData\\Local\\Temp\\csjavabridge80355\\Transformationdatarates7987268836332083503.txt");

    model.component("comp1").physics("audc").feature("ptran6").set("t1", "int6_t0001(audc.T)");
    model.component("comp1").physics("audc").feature("ptran6").set("xi1rel", "0.001");
    model.component("comp1").physics("audc").feature("ptran6").set("temperaturelimits", "1");
    model.component("comp1").physics("audc").feature("ptran6").set("Tl", "340.0[degC]");
    model.component("comp1").physics("audc").feature("ptran6").set("Tu", "740.0[degC]");
    model.component("comp1").physics("audc").feature("ptran6").set("t2", "int6_t099(audc.T)");
    model.component("comp1").physics("audc").feature("ptran6").set("xi2rel", "0.99");
    model.component("comp1").physics("audc").feature("ptran6").set("ldformulation", "ttt");
    model.component("comp1").physics("audc").feature("ptran6").set("xieq", "int6_xieq(audc.T)");
    model.component("comp1").physics("audc").feature("ptran6").set("jmakformulation", "ttt");
    model.component("comp1").physics("audc").feature("ptran6").set("xieqjmak", "int6_xieq(audc.T)");
    model.component("comp1").physics("audc").feature("ptran6").set("kvformulation", "kvfactor");
    model.component("comp1").physics("audc").feature("ptran6")
         .set("xi0drate", "0.254129/(int6_t01(audc.T)-int6_t005(audc.T))");
    model.component("comp1").physics("audc").feature("ptran6")
         .set("Auser", "int7_A(audc.T,audc.ptran6.xi/int6_xieq(audc.T))*(audc.ptran6.xi<int6_xieq(audc.T))");
    model.component("comp1").physics("audc").feature("ptran6").set("ptModel", "UserDefined");

    model.component("comp1").func("int6")
         .importData("C:\\Users\\andersw\\AppData\\Local\\Temp\\csjavabridge80355\\Transformationdata5186757037094584153.txt");
    model.component("comp1").func("int6")
         .set("fununit", new String[]{"s", "s", "s", "s", "s", "s", "s", "s", "s", "s", 
         "s", "s", "s", "s", "s", "s", "s", "s", "s", "s", 
         "s", "s", "s", "s", "s", "s", "1"});
    model.component("comp1").func("int6").set("argunit", "degC");
    model.component("comp1").func().create("int8", "Interpolation");

    model.nodeGroup("grp2").add("func", "int8");

    model.component("comp1").func("int8").set("filename", "TRIP.txt");
    model.component("comp1").func("int8").set("funcs", new String[][]{{"int8_TRIP", "1"}});

    model.component("comp1").physics("audc").feature("ptran6").set("Ktrip", "2/3*int8_TRIP(audc.T)");
    model.component("comp1").physics("audc").feature("ptran6").set("trip", true);
    model.component("comp1").physics("audc").prop("SolidMechanics").set("trip", true);

    model.component("comp1").func("int8")
         .importData("C:\\Users\\andersw\\AppData\\Local\\Temp\\csjavabridge80355\\TRIP8372127213742477754.txt");
    model.component("comp1").func("int8").set("fununit", new String[]{"1/MPa"});
    model.component("comp1").func("int8").set("argunit", "degC");
    model.component("comp1").func().create("int9", "Interpolation");

    model.nodeGroup("grp2").add("func", "int9");

    model.component("comp1").func("int9").set("filename", "LatentHeat.txt");
    model.component("comp1").func("int9").set("funcs", new String[][]{{"int9_LatentHeat", "1"}});

    model.component("comp1").physics("audc").feature("ptran6").set("dH", "int9_LatentHeat(audc.T)");
    model.component("comp1").physics("audc").prop("HeatTransfer").set("latentheat", true);

    model.component("comp1").func("int9")
         .importData("C:\\Users\\andersw\\AppData\\Local\\Temp\\csjavabridge80355\\LatentHeat16190002249771772793.txt");
    model.component("comp1").func("int9").set("fununit", new String[]{"J/cm^3"});
    model.component("comp1").func("int9").set("argunit", "degC");

    model.component("comp1").physics("audc").create("ptran7", "PhaseTransformation");
    model.component("comp1").physics("audc").feature("ptran7").label("General Steel, Austenite to Bainite");
    model.component("comp1").physics("audc").feature("ptran7")
         .comments("\n    Alloy calculated with JMatPro\n    JMatPro version 14.0\n    Material type = General Steel\n    ALLOY_ID_WT%  \n    ALLOY_ID_AT%  \n    GRAIN_SIZE_ASTM = \n    GRAIN_SIZE_MICRONS = \n    AUSTENITISATION_TEMP_C = 860.0\n    Cooling (C/s) = \n    FOR MAGNETIC PERMEABILITY:\n    HARDNESS = 400VPN\n    MICROSTRUCTURE = Quenched and tempered (fine)\n  ");

    model.nodeGroup().create("grp3", "Definitions", "comp1");
    model.nodeGroup("grp3").label("General Steel, Austenite to Bainite");
    model.nodeGroup().move("grp3", 3);

    model.component("comp1").func().create("int10", "Interpolation");

    model.nodeGroup("grp3").add("func", "int10");

    model.component("comp1").func("int10").set("filename", "Transformationdata.txt");
    model.component("comp1").func("int10")
         .set("funcs", new String[][]{{"int10_t0001", "1"}, 
         {"int10_t0005", "2"}, 
         {"int10_t001", "3"}, 
         {"int10_t002", "4"}, 
         {"int10_t003", "5"}, 
         {"int10_t004", "6"}, 
         {"int10_t005", "7"}, 
         {"int10_t01", "8"}, 
         {"int10_t015", "9"}, 
         {"int10_t02", "10"}, 
         {"int10_t025", "11"}, 
         {"int10_t03", "12"}, 
         {"int10_t035", "13"}, 
         {"int10_t04", "14"}, 
         {"int10_t045", "15"}, 
         {"int10_t05", "16"}, 
         {"int10_t055", "17"}, 
         {"int10_t06", "18"}, 
         {"int10_t065", "19"}, 
         {"int10_t07", "20"}, 
         {"int10_t075", "21"}, 
         {"int10_t08", "22"}, 
         {"int10_t085", "23"}, 
         {"int10_t09", "24"}, 
         {"int10_t095", "25"}, 
         {"int10_t099", "26"}, 
         {"int10_xieq", "27"}});
    model.component("comp1").func().create("int11", "Interpolation");

    model.nodeGroup("grp3").add("func", "int11");

    model.component("comp1").func("int11").set("filename", "Transformationdatarates.txt");
    model.component("comp1").func("int11").set("nargs", 2);
    model.component("comp1").func("int11").setIndex("fununit", "1/s", 0);
    model.component("comp1").func("int11").setIndex("argunit", "degC", 0);
    model.component("comp1").func("int11").setIndex("argunit", 1, 1);
    model.component("comp1").func("int11").setIndex("funcs", "int11_A", 0, 0);
    model.component("comp1").func("int11")
         .importData("C:\\Users\\andersw\\AppData\\Local\\Temp\\csjavabridge80355\\Transformationdatarates16119065902817431880.txt");

    model.component("comp1").physics("audc").feature("ptran7").set("t1", "int10_t0001(audc.T)");
    model.component("comp1").physics("audc").feature("ptran7").set("xi1rel", "0.001");
    model.component("comp1").physics("audc").feature("ptran7").set("temperaturelimits", "1");
    model.component("comp1").physics("audc").feature("ptran7").set("Tl", "340.0[degC]");
    model.component("comp1").physics("audc").feature("ptran7").set("Tu", "580.0[degC]");
    model.component("comp1").physics("audc").feature("ptran7").set("t2", "int10_t099(audc.T)");
    model.component("comp1").physics("audc").feature("ptran7").set("xi2rel", "0.99");
    model.component("comp1").physics("audc").feature("ptran7").set("ldformulation", "ttt");
    model.component("comp1").physics("audc").feature("ptran7").set("xieq", "int10_xieq(audc.T)");
    model.component("comp1").physics("audc").feature("ptran7").set("jmakformulation", "ttt");
    model.component("comp1").physics("audc").feature("ptran7").set("xieqjmak", "int10_xieq(audc.T)");
    model.component("comp1").physics("audc").feature("ptran7").set("kvformulation", "kvfactor");
    model.component("comp1").physics("audc").feature("ptran7")
         .set("xi0drate", "0.254129/(int10_t01(audc.T)-int10_t005(audc.T))");
    model.component("comp1").physics("audc").feature("ptran7")
         .set("Auser", "int11_A(audc.T,audc.ptran7.xi/int10_xieq(audc.T))*(audc.ptran7.xi<int10_xieq(audc.T))");
    model.component("comp1").physics("audc").feature("ptran7").set("ptModel", "UserDefined");

    model.component("comp1").func("int10")
         .importData("C:\\Users\\andersw\\AppData\\Local\\Temp\\csjavabridge80355\\Transformationdata5442968852522481572.txt");
    model.component("comp1").func("int10")
         .set("fununit", new String[]{"s", "s", "s", "s", "s", "s", "s", "s", "s", "s", 
         "s", "s", "s", "s", "s", "s", "s", "s", "s", "s", 
         "s", "s", "s", "s", "s", "s", "1"});
    model.component("comp1").func("int10").set("argunit", "degC");
    model.component("comp1").func().create("int12", "Interpolation");

    model.nodeGroup("grp3").add("func", "int12");

    model.component("comp1").func("int12").set("filename", "TRIP.txt");
    model.component("comp1").func("int12").set("funcs", new String[][]{{"int12_TRIP", "1"}});

    model.component("comp1").physics("audc").feature("ptran7").set("Ktrip", "2/3*int12_TRIP(audc.T)");
    model.component("comp1").physics("audc").feature("ptran7").set("trip", true);
    model.component("comp1").physics("audc").prop("SolidMechanics").set("trip", true);

    model.component("comp1").func("int12")
         .importData("C:\\Users\\andersw\\AppData\\Local\\Temp\\csjavabridge80355\\TRIP4814933722260100281.txt");
    model.component("comp1").func("int12").set("fununit", new String[]{"1/MPa"});
    model.component("comp1").func("int12").set("argunit", "degC");
    model.component("comp1").func().create("int13", "Interpolation");

    model.nodeGroup("grp3").add("func", "int13");

    model.component("comp1").func("int13").set("filename", "LatentHeat.txt");
    model.component("comp1").func("int13").set("funcs", new String[][]{{"int13_LatentHeat", "1"}});

    model.component("comp1").physics("audc").feature("ptran7").set("dH", "int13_LatentHeat(audc.T)");
    model.component("comp1").physics("audc").prop("HeatTransfer").set("latentheat", true);

    model.component("comp1").func("int13")
         .importData("C:\\Users\\andersw\\AppData\\Local\\Temp\\csjavabridge80355\\LatentHeat3764006965749257661.txt");
    model.component("comp1").func("int13").set("fununit", new String[]{"J/cm^3"});
    model.component("comp1").func("int13").set("argunit", "degC");

    model.component("comp1").physics("audc").create("ptran8", "PhaseTransformation");
    model.component("comp1").physics("audc").feature("ptran8").label("General Steel, Austenite to Martensite");
    model.component("comp1").physics("audc").feature("ptran8")
         .comments("\n    Alloy calculated with JMatPro\n    JMatPro version 14.0\n    Material type = General Steel\n    ALLOY_ID_WT%  \n    ALLOY_ID_AT%  \n    GRAIN_SIZE_ASTM = \n    GRAIN_SIZE_MICRONS = \n    AUSTENITISATION_TEMP_C = 860.0\n    Cooling (C/s) = \n    FOR MAGNETIC PERMEABILITY:\n    HARDNESS = 400VPN\n    MICROSTRUCTURE = Quenched and tempered (fine)\n  ");

    model.nodeGroup().create("grp4", "Definitions", "comp1");
    model.nodeGroup("grp4").label("General Steel, Austenite to Martensite");
    model.nodeGroup().move("grp4", 4);

    model.component("comp1").func().create("int14", "Interpolation");

    model.nodeGroup("grp4").add("func", "int14");

    model.component("comp1").func("int14").set("filename", "Transformationdata.txt");
    model.component("comp1").func("int14").set("funcs", new String[][]{{"int14_M", "1"}});

    model.component("comp1").physics("audc").feature("ptran8").set("ptModel", "KoistinenMarburger");
    model.component("comp1").physics("audc").feature("ptran8").set("kmformulation", "kmm90");
    model.component("comp1").physics("audc").feature("ptran8").set("Ms", "int14_M(0)");
    model.component("comp1").physics("audc").feature("ptran8").set("M90", "int14_M(2)");

    model.component("comp1").func("int14")
         .importData("C:\\Users\\andersw\\AppData\\Local\\Temp\\csjavabridge80355\\Transformationdata6731176055049838317.txt");
    model.component("comp1").func("int14").set("fununit", new String[]{"degC"});
    model.component("comp1").func("int14").set("argunit", "1");
    model.component("comp1").func().create("int15", "Interpolation");

    model.nodeGroup("grp4").add("func", "int15");

    model.component("comp1").func("int15").set("filename", "TRIP.txt");
    model.component("comp1").func("int15").set("funcs", new String[][]{{"int15_TRIP", "1"}});

    model.component("comp1").physics("audc").feature("ptran8").set("Ktrip", "2/3*int15_TRIP(audc.T)");
    model.component("comp1").physics("audc").feature("ptran8").set("trip", true);
    model.component("comp1").physics("audc").prop("SolidMechanics").set("trip", true);

    model.component("comp1").func("int15")
         .importData("C:\\Users\\andersw\\AppData\\Local\\Temp\\csjavabridge80355\\TRIP7656751154174574703.txt");
    model.component("comp1").func("int15").set("fununit", new String[]{"1/MPa"});
    model.component("comp1").func("int15").set("argunit", "degC");
    model.component("comp1").func().create("int16", "Interpolation");

    model.nodeGroup("grp4").add("func", "int16");

    model.component("comp1").func("int16").set("filename", "LatentHeat.txt");
    model.component("comp1").func("int16").set("funcs", new String[][]{{"int16_LatentHeat", "1"}});

    model.component("comp1").physics("audc").feature("ptran8").set("dH", "int16_LatentHeat(audc.T)");
    model.component("comp1").physics("audc").prop("HeatTransfer").set("latentheat", true);

    model.component("comp1").func("int16")
         .importData("C:\\Users\\andersw\\AppData\\Local\\Temp\\csjavabridge80355\\LatentHeat1554149514565716259.txt");
    model.component("comp1").func("int16").set("fununit", new String[]{"J/cm^3"});
    model.component("comp1").func("int16").set("argunit", "degC");

    model.component("comp1").physics("audc").feature("phase1").set("phaseMaterial", "mat1");
    model.component("comp1").physics("audc").feature("phase1").set("IsotropicHardeningModel", "HardeningFunction");
    model.component("comp1").physics("audc").feature("phase2").set("phaseMaterial", "mat2");
    model.component("comp1").physics("audc").feature("phase2").set("IsotropicHardeningModel", "HardeningFunction");
    model.component("comp1").physics("audc").feature("phase3").set("phaseMaterial", "mat3");
    model.component("comp1").physics("audc").feature("phase3").set("IsotropicHardeningModel", "HardeningFunction");
    model.component("comp1").physics("audc").feature("phase4").set("phaseMaterial", "mat4");
    model.component("comp1").physics("audc").feature("phase4").set("IsotropicHardeningModel", "HardeningFunction");
    model.component("comp1").physics("audc").feature("phase5").set("phaseMaterial", "mat5");
    model.component("comp1").physics("audc").feature("phase5").set("IsotropicHardeningModel", "HardeningFunction");

    model.common("cminpt").set("modified", new String[][]{{"strainreferencetemperature", "Tinit"}});

    model.component("comp1").physics("audc").feature("ptran5").set("phasei", "phase1");
    model.component("comp1").physics("audc").feature("ptran5").set("phasej", "phase2");
    model.component("comp1").physics("audc").feature("ptran6").set("phasei", "phase1");
    model.component("comp1").physics("audc").feature("ptran6").set("phasej", "phase3");
    model.component("comp1").physics("audc").feature("ptran7").set("phasei", "phase1");
    model.component("comp1").physics("audc").feature("ptran7").set("phasej", "phase4");
    model.component("comp1").physics("audc").feature("ptran8").set("phasei", "phase1");
    model.component("comp1").physics("audc").feature("ptran8").set("phasej", "phase5");

    model.study("std1").feature("time").set("tlist", "range(0,30,240)");

    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"temperature", "\u6e29\u5ea6", "K", "K"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "\u00b0C", 0, 3);
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"stress", "\u5e94\u529b\u5f20\u91cf", "N/m^2", "N/m^2"}, 1);
    model.result().configuration("prfu1").setIndex("quantityunits", "MPa", 1, 3);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u6e29\u5ea6 (ht)");
    model.result("pg1").feature().create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("showsolutionparams", "on");
    model.result("pg1").feature("vol1").set("solutionparams", "parent");
    model.result("pg1").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg1").feature("vol1").set("smooth", "internal");
    model.result("pg1").feature("vol1").set("showsolutionparams", "on");
    model.result("pg1").feature("vol1").set("data", "parent");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 9, 0);
    model.result("pg2").label("\u5e94\u529b (solid)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").create("vol1", "Volume");
    model.result("pg2").feature("vol1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg2").feature("vol1").set("threshold", "manual");
    model.result("pg2").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg2").feature("vol1").set("colortable", "Rainbow");
    model.result("pg2").feature("vol1").set("colortabletrans", "none");
    model.result("pg2").feature("vol1").set("colorscalemode", "linear");
    model.result("pg2").feature("vol1").set("resolution", "custom");
    model.result("pg2").feature("vol1").set("refine", 2);
    model.result("pg2").feature("vol1").set("colortable", "Prism");
    model.result("pg2").feature("vol1").create("def", "Deform");
    model.result("pg2").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg2").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").setIndex("looplevel", 9, 0);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"audc.phase1.xi"});
    model.result("pg3").label("\u5965\u6c0f\u4f53 (audc)");
    model.result("pg3").feature("surf1").set("coloring", "gradient");
    model.result("pg3").feature("surf1").set("topcolor", "custom");
    model.result("pg3").feature("surf1").set("customtopcolor", new double[]{0.9, 0.4, 0.15});
    model.result("pg3").feature("surf1").set("bottomcolor", "custom");
    model.result("pg3").feature("surf1").set("custombottomcolor", new double[]{0, 0, 0.5});
    model.result("pg3").feature("surf1").set("colortablerev", false);
    model.result("pg3").feature("surf1").set("titletype", "manual");
    model.result("pg3").feature("surf1").set("title", "\u5965\u6c0f\u4f53");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").setIndex("looplevel", 9, 0);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"audc.phase2.xi"});
    model.result("pg4").label("\u94c1\u7d20\u4f53 (audc)");
    model.result("pg4").feature("surf1").set("coloring", "gradient");
    model.result("pg4").feature("surf1").set("topcolor", "custom");
    model.result("pg4").feature("surf1").set("customtopcolor", new double[]{0.9, 0.4, 0.15});
    model.result("pg4").feature("surf1").set("bottomcolor", "custom");
    model.result("pg4").feature("surf1").set("custombottomcolor", new double[]{0, 0, 0.5});
    model.result("pg4").feature("surf1").set("colortablerev", false);
    model.result("pg4").feature("surf1").set("titletype", "manual");
    model.result("pg4").feature("surf1").set("title", "\u94c1\u7d20\u4f53");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "dset1");
    model.result("pg5").setIndex("looplevel", 9, 0);
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"audc.phase3.xi"});
    model.result("pg5").label("\u73e0\u5149\u4f53 (audc)");
    model.result("pg5").feature("surf1").set("coloring", "gradient");
    model.result("pg5").feature("surf1").set("topcolor", "custom");
    model.result("pg5").feature("surf1").set("customtopcolor", new double[]{0.9, 0.4, 0.15});
    model.result("pg5").feature("surf1").set("bottomcolor", "custom");
    model.result("pg5").feature("surf1").set("custombottomcolor", new double[]{0, 0, 0.5});
    model.result("pg5").feature("surf1").set("colortablerev", false);
    model.result("pg5").feature("surf1").set("titletype", "manual");
    model.result("pg5").feature("surf1").set("title", "\u73e0\u5149\u4f53");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").set("data", "dset1");
    model.result("pg6").setIndex("looplevel", 9, 0);
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", new String[]{"audc.phase4.xi"});
    model.result("pg6").label("\u8d1d\u6c0f\u4f53 (audc)");
    model.result("pg6").feature("surf1").set("coloring", "gradient");
    model.result("pg6").feature("surf1").set("topcolor", "custom");
    model.result("pg6").feature("surf1").set("customtopcolor", new double[]{0.9, 0.4, 0.15});
    model.result("pg6").feature("surf1").set("bottomcolor", "custom");
    model.result("pg6").feature("surf1").set("custombottomcolor", new double[]{0, 0, 0.5});
    model.result("pg6").feature("surf1").set("colortablerev", false);
    model.result("pg6").feature("surf1").set("titletype", "manual");
    model.result("pg6").feature("surf1").set("title", "\u8d1d\u6c0f\u4f53");
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").set("data", "dset1");
    model.result("pg7").setIndex("looplevel", 9, 0);
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", new String[]{"audc.phase5.xi"});
    model.result("pg7").label("\u9a6c\u6c0f\u4f53 (audc)");
    model.result("pg7").feature("surf1").set("coloring", "gradient");
    model.result("pg7").feature("surf1").set("topcolor", "custom");
    model.result("pg7").feature("surf1").set("customtopcolor", new double[]{0.9, 0.4, 0.15});
    model.result("pg7").feature("surf1").set("bottomcolor", "custom");
    model.result("pg7").feature("surf1").set("custombottomcolor", new double[]{0, 0, 0.5});
    model.result("pg7").feature("surf1").set("colortablerev", false);
    model.result("pg7").feature("surf1").set("titletype", "manual");
    model.result("pg7").feature("surf1").set("title", "\u9a6c\u6c0f\u4f53");
    model.result("pg1").run();
    model.result().dataset().create("mir1", "Mirror3D");
    model.result().dataset().create("sec1", "Sector3D");
    model.result().dataset("sec1").set("data", "mir1");
    model.result().dataset("sec1").set("sectors", 20);
    model.result("pg2").run();
    model.result("pg2").run();

    model.component("comp1").view("view1").set("showgrid", false);

    model.result("pg2").set("data", "sec1");
    model.result("pg2").set("edges", false);
    model.result("pg2").run();

    model.view("view2").set("showgrid", false);
    model.view("view2").set("showaxisorientation", false);

    model.result("pg2").set("showlegends", false);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").set("data", "sec1");
    model.result("pg4").set("edges", false);
    model.result("pg4").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").set("data", "sec1");
    model.result("pg5").set("edges", false);
    model.result("pg5").run();
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").set("data", "sec1");
    model.result("pg6").set("edges", false);
    model.result("pg6").run();
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").set("data", "sec1");
    model.result("pg7").set("edges", false);
    model.result("pg7").run();
    model.result().dataset().create("mesh1", "Mesh");
    model.result().dataset("mesh1").set("mesh", "mesh1");
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").label("\u7f51\u683c\u56fe 8");
    model.result("pg8").set("data", "mesh1");
    model.result("pg8").set("inherithide", true);
    model.result("pg8").set("showlegendsmaxmin", true);
    model.result("pg8").create("mesh1", "Mesh");
    model.result("pg8").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg8").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg8").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg8").feature("mesh1").set("meshdomain", "volume");
    model.result("pg8").run();
    model.result("pg8").feature("mesh1").set("elemcolor", "green");

    model.component("comp1").view("view1").set("showgrid", true);

    model.result("pg8").run();
    model.result("pg8").create("vol1", "Volume");
    model.result("pg8").feature("vol1").set("data", "sec1");
    model.result("pg8").feature("vol1").set("expr", "1");
    model.result("pg8").feature("vol1").set("coloring", "uniform");
    model.result("pg8").feature("vol1").set("color", "gray");
    model.result("pg8").feature().move("vol1", 0);
    model.result("pg8").run();
    model.result("pg8").run();

    model.component("comp1").view("view1").set("showgrid", false);

    model.result("pg8").run();
    model.result("pg8").feature("vol1").active(false);
    model.result("pg8").run();
    model.result("pg8").run();
    model.result("pg8").run();
    model.result("pg8").feature("vol1").active(true);
    model.result().create("pg9", "PlotGroup3D");
    model.result("pg9").run();
    model.result("pg9").create("pris1", "PrincipalSurface");
    model.result("pg9").feature("pris1").set("placement", "gausspoints");
    model.result("pg9").feature("pris1").set("gporder", 4);
    model.result("pg9").feature("pris1").create("sel1", "Selection");
    model.result("pg9").feature("pris1").feature("sel1").selection().set(11, 13, 14);
    model.result("pg9").run();

    model.title("\u9525\u9f7f\u8f6e\u6dec\u706b");

    model
         .description("\u672c\u4f8b\u4f7f\u7528\u4e09\u7ef4\u6a21\u578b\u6a21\u62df\u9525\u9f7f\u8f6e\u4ece\u5965\u6c0f\u4f53\u72b6\u6001\u7684\u51b7\u5374\u8fc7\u7a0b\u3002\u6a21\u578b\u4e2d\u5bfc\u5165\u4e86\u76f8\u6750\u6599\u5c5e\u6027\u548c\u76f8\u53d8\u6570\u636e\uff0c\u4ee5\u8ba1\u7b97\u6700\u7ec8\u7684\u76f8\u7ec4\u6210\u548c\u6b8b\u4f59\u5e94\u529b\u72b6\u6001\u3002\u4eff\u771f\u6db5\u76d6\u4e86\u70ed\u5e94\u53d8\u3001\u76f8\u5851\u6027\u3001\u76f8\u53d8\u8bf1\u5bfc\u5851\u6027 (TRIP) \u4ee5\u53ca\u76f8\u53d8\u6f5c\u70ed\u7b49\u73b0\u8c61\u3002");

    model.sol("sol1").clearSolutionData();

    model.label("quenching_of_a_bevel_gear.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
