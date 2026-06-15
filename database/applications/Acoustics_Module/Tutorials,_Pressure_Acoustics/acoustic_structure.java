/*
 * acoustic_structure.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:40 by COMSOL 6.3.0.290. */
public class acoustic_structure {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Tutorials,_Pressure_Acoustics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("acpr", "PressureAcoustics", "geom1");
    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.component("comp1").multiphysics().create("asb1", "AcousticStructureBoundary", 2);
    model.component("comp1").multiphysics("asb1").set("Acoustics_physics", "acpr");
    model.component("comp1").multiphysics("asb1").set("Structure_physics", "solid");
    model.component("comp1").multiphysics("asb1").selection().all();

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/acpr", true);
    model.study("std1").feature("freq").setSolveFor("/physics/solid", true);
    model.study("std1").feature("freq").setSolveFor("/multiphysics/asb1", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("f", "60[kHz]", "\u9891\u7387");
    model.param().set("phi", "(-pi/6)[rad]", "\u6ce2\u65b9\u5411\u89d2\uff0cphi");
    model.param().set("theta", "(4*pi/6)[rad]", "\u6ce2\u65b9\u5411\u89d2\uff0ctheta");
    model.param().set("k1", "sin(theta)*cos(phi)", "\u5165\u5c04\u6ce2\u65b9\u5411\u77e2\u91cf X \u5206\u91cf");
    model.param().set("k2", "sin(theta)*sin(phi)", "\u5165\u5c04\u6ce2\u65b9\u5411\u77e2\u91cf Y \u5206\u91cf");
    model.param().set("k3", "cos(theta)", "\u5165\u5c04\u6ce2\u65b9\u5411\u77e2\u91cf Z \u5206\u91cf");
    model.param().set("R", "30[mm]", "\u6a21\u578b\u57df\u534a\u5f84");
    model.param().descr("phi", "\u6ce2\u7684\u65b9\u5411\u89d2\uff0cphi");
    model.param().descr("theta", "\u6ce2\u7684\u65b9\u5411\u89d2\uff0ctheta");
    model.param().descr("k1", "\u5165\u5c04\u6ce2\u65b9\u5411\u77e2\u91cf\uff0cX \u5206\u91cf");
    model.param().descr("k2", "\u5165\u5c04\u6ce2\u65b9\u5411\u77e2\u91cf\uff0cY \u5206\u91cf");
    model.param().descr("k3", "\u5165\u5c04\u6ce2\u65b9\u5411\u77e2\u91cf\uff0cZ \u5206\u91cf");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", 5);
    model.component("comp1").geom("geom1").feature("cyl1").set("h", 20);
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new int[]{0, 0, -10});
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("sph1", "Sphere");
    model.component("comp1").geom("geom1").feature("sph1").set("r", "R");
    model.component("comp1").geom("geom1").run("sph1");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").label("\u6d41\u4f53\u57df");
    model.component("comp1").selection("sel1").set(1);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u56fa\u4f53\u57df");
    model.component("comp1").selection("sel2").set(2);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u8f90\u5c04\u8fb9\u754c");
    model.component("comp1").selection("sel3").all();
    model.component("comp1").selection("sel3").geom("geom1", 3, 2, new String[]{"exterior"});
    model.component("comp1").selection("sel3").all();
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").label("\u56fa\u4f53\u8fb9\u754c");
    model.component("comp1").selection("sel4").set(2);
    model.component("comp1").selection("sel4").geom("geom1", 3, 2, new String[]{"exterior"});
    model.component("comp1").selection("sel4").set(2);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("rho", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("cs", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an3", "Analytic");
    model.component("comp1").material("mat1").label("Water, liquid");
    model.component("comp1").material("mat1").set("family", "water");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"273.15", "413.15", "1.3799566804-0.021224019151*T^1+1.3604562827E-4*T^2-4.6454090319E-7*T^3+8.9042735735E-10*T^4-9.0790692686E-13*T^5+3.8457331488E-16*T^6"}, {"413.15", "553.75", "0.00401235783-2.10746715E-5*T^1+3.85772275E-8*T^2-2.39730284E-11*T^3"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"273.15", "553.75", "12010.1471-80.4072879*T^1+0.309866854*T^2-5.38186884E-4*T^3+3.62536437E-7*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").label("Piecewise 3");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("smooth", "contd1");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("pieces", new String[][]{{"273.15", "293.15", "0.000063092789034*T^3-0.060367639882855*T^2+18.9229382407066*T-950.704055329848"}, {"293.15", "373.15", "0.000010335053319*T^3-0.013395065634452*T^2+4.969288832655160*T+432.257114008512"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").label("Piecewise 4");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"273.15", "553.75", "-0.869083936+0.00894880345*T^1-1.58366345E-5*T^2+7.97543259E-9*T^3"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").label("Interpolation");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("table", new String[][]{{"273", "1403"}, 
         {"278", "1427"}, 
         {"283", "1447"}, 
         {"293", "1481"}, 
         {"303", "1507"}, 
         {"313", "1526"}, 
         {"323", "1541"}, 
         {"333", "1552"}, 
         {"343", "1555"}, 
         {"353", "1555"}, 
         {"363", "1550"}, 
         {"373", "1543"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("interp", "piecewisecubic");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("fununit", new String[]{"m/s"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("expr", "-1/rho(T)*d(rho(T),T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").label("Analytic 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("funcname", "gamma_w");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("expr", "1+(T/Cp(T))*(alpha_p(T)*cs(T))^2");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("fununit", "1");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").label("Analytic 3");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("funcname", "muB");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("expr", "2.79*eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an3")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an3")
         .set("plotargs", new String[][]{{"T", "273.15", "553.75"}});
    model.component("comp1").material("mat1").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(T)", "0", "0", "0", "alpha_p(T)", "0", "0", "0", "alpha_p(T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("ratioofspecificheat", "gamma_w(T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "rho(T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("temperature");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").label("Aluminum 3003-H18");
    model.component("comp1").material("mat2").set("family", "aluminum");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"2.326e7[S/m]", "0", "0", "0", "2.326e7[S/m]", "0", "0", "0", "2.326e7[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"23.2e-6[1/K]", "0", "0", "0", "23.2e-6[1/K]", "0", "0", "0", "23.2e-6[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "893[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "2730[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"155[W/(m*K)]", "0", "0", "0", "155[W/(m*K)]", "0", "0", "0", "155[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "69[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.33");
    model.component("comp1").material("mat1").selection().named("sel1");
    model.component("comp1").material("mat2").selection().named("sel2");

    model.component("comp1").physics("acpr").selection().named("sel1");
    model.component("comp1").physics("acpr").prop("ReferencePressure")
         .set("ReferenceType", "ReferencePressureWater");
    model.component("comp1").physics("acpr").create("swr1", "SphericalWaveRadiation", 2);
    model.component("comp1").physics("acpr").feature("swr1").selection().named("sel3");
    model.component("comp1").physics("acpr").feature("swr1").create("ipf1", "IncidentPressureField", 2);
    model.component("comp1").physics("acpr").feature("swr1").feature("ipf1").set("pamp", 1);
    model.component("comp1").physics("acpr").feature("swr1").feature("ipf1").set("c_mat", "from_mat");
    model.component("comp1").physics("acpr").feature("swr1").feature("ipf1").set("PressureFieldMaterial", "mat1");
    model.component("comp1").physics("acpr").feature("swr1").feature("ipf1")
         .set("dir", new String[]{"k1", "k2", "k3"});
    model.component("comp1").physics("solid").selection().named("sel2");

    model.study("std1").label("\u7814\u7a76 1 - \u58f0\u573a\u786c\u5706\u67f1");
    model.study("std1").setGenPlots(false);
    model.study("std1").feature("freq").set("plist", "f");

    model.component("comp1").mesh("mesh1").autoMeshSize(6);

    model.component("comp1").physics("acpr").prop("MeshControl").set("ElementsPerWavelength", "UserDefined");
    model.component("comp1").physics("acpr").prop("MeshControl").set("nperlambda", 6);

    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").view("view1").set("transparency", false);

    model.study("std1").feature("freq").setSolveFor("/physics/solid", false);
    model.study("std1").feature("freq").setSolveFor("/multiphysics/asb1", false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.study().create("std2");
    model.study("std2").create("freq", "Frequency");
    model.study("std2").feature("freq").setSolveFor("/physics/acpr", true);
    model.study("std2").feature("freq").setSolveFor("/physics/solid", true);
    model.study("std2").feature("freq").setSolveFor("/multiphysics/asb1", true);
    model.study("std2").label("\u7814\u7a76 2 - \u94dd\u5236\u5706\u67f1");
    model.study("std2").setGenPlots(false);
    model.study("std2").feature("freq").set("plist", "f");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset().create("cln1", "CutLine3D");
    model.result().dataset("cln1").setIndex("genpoints", "-R*k1", 0, 0);
    model.result().dataset("cln1").setIndex("genpoints", "-R*k2", 0, 1);
    model.result().dataset("cln1").setIndex("genpoints", "-R*k3", 0, 2);
    model.result().dataset("cln1").setIndex("genpoints", "R*k1", 1, 0);
    model.result().dataset("cln1").setIndex("genpoints", "R*k2", 1, 1);
    model.result().dataset("cln1").setIndex("genpoints", "R*k3", 1, 2);
    model.result().dataset().duplicate("cln2", "cln1");
    model.result().dataset("cln2").set("data", "dset2");
    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").label("\u6cbf\u4f20\u64ad\u65b9\u5411\u7684\u58f0\u538b\u7ea7");
    model.result("pg1").set("titletype", "label");
    model.result("pg1").create("lngr1", "LineGraph");
    model.result("pg1").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg1").feature("lngr1").set("linewidth", "preference");
    model.result("pg1").feature("lngr1").set("data", "cln1");
    model.result("pg1").feature("lngr1").set("expr", "acpr.Lp_t");
    model.result("pg1").feature("lngr1").set("descr", "\u603b\u58f0\u538b\u7ea7");
    model.result("pg1").feature("lngr1").set("xdata", "expr");
    model.result("pg1").feature("lngr1").set("xdataexpr", "x");
    model.result("pg1").feature("lngr1").set("legend", true);
    model.result("pg1").feature("lngr1").set("legendmethod", "manual");
    model.result("pg1").feature("lngr1").setIndex("legends", "\u786c\u5706\u67f1", 0);
    model.result("pg1").feature().duplicate("lngr2", "lngr1");
    model.result("pg1").run();
    model.result("pg1").feature("lngr2").set("data", "cln2");
    model.result("pg1").feature("lngr2").setIndex("legends", "\u94dd\u5236\u5706\u67f1", 0);
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").label("\u58f0\u538b\u7ea7\u548c\u4f4d\u79fb");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").set("edges", false);
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").set("titletype", "label");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "solid.disp");
    model.result("pg2").feature("surf1").set("descr", "\u4f4d\u79fb\u5927\u5c0f");
    model.result("pg2").feature("surf1").set("colortable", "AuroraBorealis");
    model.result("pg2").feature("surf1").set("colorscalemode", "linear");
    model.result("pg2").feature("surf1").create("def1", "Deform");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").create("slc1", "Slice");
    model.result("pg2").feature("slc1").set("expr", "acpr.Lp_t");
    model.result("pg2").feature("slc1").set("descr", "\u603b\u58f0\u538b\u7ea7");
    model.result("pg2").feature("slc1").set("quickplane", "zx");
    model.result("pg2").feature("slc1").set("quickymethod", "coord");
    model.result("pg2").feature("slc1").set("quicky", 5);
    model.result("pg2").feature("slc1").set("colortable", "Rainbow");
    model.result("pg2").feature("slc1").set("colorscalemode", "linear");
    model.result("pg2").run();
    model.result("pg2").create("arws1", "ArrowSurface");
    model.result("pg2").feature("arws1").set("expr", new String[]{"solid.u_ttX", "solid.u_ttY", "solid.u_ttZ"});
    model.result("pg2").feature("arws1").set("descr", "\u52a0\u901f\u5ea6");
    model.result("pg2").feature("arws1").set("arrowbase", "head");
    model.result("pg2").feature("arws1").set("scaleactive", true);
    model.result("pg2").feature("arws1").set("scale", 20);
    model.result("pg2").feature("arws1").set("arrowcount", 5000);
    model.result("pg2").feature("arws1").set("color", "white");
    model.result("pg2").run();
    model.result("pg2").run();

    model.title("\u58f0-\u7ed3\u6784\u76f8\u4e92\u4f5c\u7528");

    model
         .description("\u672c\u4f8b\u901a\u8fc7\u6a21\u62df\u5c06\u56fa\u4f53\u94dd\u5706\u67f1\u4f53\u6d78\u5165\u6c34\u4e2d\u5e76\u66b4\u9732\u4e8e\u58f0\u4fe1\u53f7\u4e2d\u7684\u884c\u4e3a\uff0c\u6f14\u793a\u4e86\u201c\u58f0\u5b66\u6a21\u5757\u201d\u7684\u201c\u58f0-\u56fa\u76f8\u4e92\u4f5c\u7528\u201d\u591a\u7269\u7406\u573a\u63a5\u53e3\u3002\u58f0\u538b\uff08\u58f0\u97f3\uff09\u4e0e\u5f39\u6027\u56fa\u4f53\u76f8\u4e92\u4f5c\u7528\u4f7f\u58c1\u4ea7\u751f\u8fd0\u52a8\uff0c\u8fdb\u800c\u63a8\u52a8\u6c34\u7684\u8fd0\u52a8\uff0c\u5e76\u7531\u6b64\u4ea7\u751f\u989d\u5916\u7684\u58f0\u6ce2\u3002\u8fd9\u79cd\u53cc\u5411\u8026\u5408\u662f\u81ea\u52a8\u8bbe\u7f6e\u7684\u3002\u672c\u4f8b\u8ba1\u7b97\u7ed3\u6784\u7684\u9891\u7387\u54cd\u5e94\uff0c\u7136\u540e\u5c06\u5176\u53cd\u9988\u5230\u58f0\u5b66\u57df\uff0c\u4ee5\u4fbf\u5206\u6790\u6ce2\u578b\u3002\u56e0\u6b64\uff0c\u8be5\u6a21\u578b\u6210\u4e3a\u7814\u7a76\u6563\u5c04\u95ee\u9898\u7684\u4e00\u4e2a\u51fa\u8272\u6848\u4f8b\u3002\u6db2\u4f53\u6216\u6c14\u4f53\u58f0\u5b66\u4e0e\u7ed3\u6784\u7269\u4f53\u4e4b\u95f4\u7684\u76f8\u4e92\u4f5c\u7528\u5728\u8bb8\u591a\u5de5\u7a0b\u5e94\u7528\u4e2d\u53d1\u6325\u7740\u5173\u952e\u4f5c\u7528\uff0c\u4f8b\u5982\u626c\u58f0\u5668\u3001\u52a9\u542c\u5668\u3001\u58f0\u5b66\u4f20\u611f\u5668\u548c\u533b\u5b66\u8d85\u58f0\u8bca\u65ad\u7b49\u9886\u57df\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("acoustic_structure.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
