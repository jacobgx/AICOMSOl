/*
 * living_room_acoustics_llrevit.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:46 by COMSOL 6.3.0.290. */
public class living_room_acoustics_llrevit {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\LiveLink_for_Revit\\Tutorials,_LiveLink_Interface");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("acpr", "PressureAcoustics", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/acpr", true);

    model.component("comp1").geom("geom1").geomRep("cadps");
    model.component("comp1").geom("geom1").create("cad1", "LiveLinkRevit");
    model.component("comp1").geom("geom1").feature("cad1").updateCadParamTable(false, false);
    model.component("comp1").geom("geom1").feature("cad1").importData();

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1").set("entitydim", -1);
    model.component("comp1").geom("geom1").feature("unisel1")
         .set("input", new String[]{"cad1_FloorsGeneric12", "cad1_RoofsGeneric12", "cad1_WallsBasicWallGeneric8", "cad1_WallsBasicWallInterior5Partition2hr"});
    model.component("comp1").geom("geom1").run("unisel1");
    model.component("comp1").geom("geom1").create("unisel2", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel2").set("entitydim", -1);
    model.component("comp1").geom("geom1").feature("unisel2")
         .set("input", new String[]{"cad1_AirLiving_room_5", "cad1_SurfaceDoorsM_DoubleGlass21830x2134mm", "cad1_SurfaceWallsCurtainWallCurtainWall1", "cad1_SurfaceWindowsM_CasementDblwithTrim1220x1220mm", "cad1_SurfaceWindowsM_Skylight0610x0686mm", "unisel1"});
    model.component("comp1").geom("geom1").run("unisel2");
    model.component("comp1").geom("geom1").create("unisel3", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel3").set("entitydim", -1);
    model.component("comp1").geom("geom1").feature("unisel3")
         .set("input", new String[]{"cad1_FurnitureFloorstandingSpeakerFloorstandingSpeaker", "cad1_FurnitureM_Entertainmentcenter1830x1830x0610mm", "cad1_FurnitureM_SofaPensi2134mm", "cad1_FurnitureM_TableCoffee0915x1830x0457mm", "cad1_FurnitureM_TableEnd0762x0762mm", "cad1_FurnitureM_TVFlatScreen1270mm", "cad1_FurnitureM_TVStandM_TVStand"});
    model.component("comp1").geom("geom1").run("unisel3");
    model.component("comp1").geom("geom1").create("csol1", "ConvertToSolid");
    model.component("comp1").geom("geom1").feature("csol1").selection("input").named("unisel2");
    model.component("comp1").geom("geom1").feature("csol1").set("repairtoltype", "relative");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature().create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init();
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(3);
    model.component("comp1").geom("geom1").feature("del1").selection("input").named("unisel1");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").named("unisel2");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").named("unisel3");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").defeaturing("SliverFaces").set("entsize", "5[mm]");
    model.component("comp1").geom("geom1").defeaturing("SliverFaces").find();
    model.component("comp1").geom("geom1").defeaturing("SliverFaces").detail().setGroup(8);
    model.component("comp1").geom("geom1").defeaturing("SliverFaces").deleteAll("dsl1");
    model.component("comp1").geom("geom1").defeaturing("Spikes").set("entsize", "10[mm]");
    model.component("comp1").geom("geom1").defeaturing("Spikes").find();
    model.component("comp1").geom("geom1").defeaturing("Spikes").detail().setGroup(8);
    model.component("comp1").geom("geom1").defeaturing("Spikes").deleteAll("dsp1");
    model.component("comp1").geom("geom1").defeaturing("ReplaceFaces").selection("input")
         .set("dsp1", 30, 31, 32, 33, 156, 189, 195, 196, 265, 280);
    model.component("comp1").geom("geom1").defeaturing("ReplaceFaces").delete("rfa1");
    model.component("comp1").geom("geom1").create("adjsel1", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("adjsel1").set("input", new String[]{"cad1_FloorsGeneric12"});
    model.component("comp1").geom("geom1").feature("adjsel1").set("selshow", false);
    model.component("comp1").geom("geom1").run("adjsel1");
    model.component("comp1").geom("geom1").create("difsel1", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("difsel1")
         .set("add", new String[]{"cad1_FurnitureM_SofaPensi2134mm"});
    model.component("comp1").geom("geom1").feature("difsel1").set("subtract", new String[]{"adjsel1"});
    model.component("comp1").geom("geom1").run("difsel1");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("rfa1", 237, 239);
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("unisel4", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel4").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("unisel4")
         .set("input", new String[]{"cad1_RoofsGeneric12", "cad1_WallsBasicWallGeneric8", "cad1_WallsBasicWallInterior5Partition2hr"});
    model.component("comp1").geom("geom1").run("unisel4");
    model.component("comp1").geom("geom1").create("unisel5", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel5").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("unisel5")
         .set("input", new String[]{"cad1_SurfaceDoorsM_DoubleGlass21830x2134mm", "cad1_SurfaceWallsCurtainWallCurtainWall1", "cad1_SurfaceWindowsM_CasementDblwithTrim1220x1220mm", "cad1_SurfaceWindowsM_Skylight0610x0686mm"});
    model.component("comp1").geom("geom1").run("unisel5");
    model.component("comp1").geom("geom1").create("unisel6", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel6").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("unisel6")
         .set("input", new String[]{"cad1_FurnitureM_Entertainmentcenter1830x1830x0610mm", "cad1_FurnitureM_TableCoffee0915x1830x0457mm", "cad1_FurnitureM_TableEnd0762x0762mm", "cad1_FurnitureM_TVStandM_TVStand"});
    model.component("comp1").geom("geom1").run("fin");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("f0", "100[Hz]", "Driving frequency");
    model.param().set("c0", "343[m/s]", "Speed of sound");
    model.param().set("lambda0", "c0/f0", "Wavelength at f0");
    model.param().set("Nacc", "1[m/s^2]", "Woofer normal acceleration");
    model.param().set("Zncouch", "1000-900*i[Pa*s/m]", "Impedance for the couch");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("rho", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("cs", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat1").propertyGroup()
         .create("NonlinearModel", "NonlinearModel", "Nonlinear model");
    model.component("comp1").material("mat1").propertyGroup().create("idealGas", "idealGas", "Ideal gas");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat1").label("Air");
    model.component("comp1").material("mat1").set("family", "air");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-8.38278E-7+8.35717342E-8*T^1-7.69429583E-11*T^2+4.6437266E-14*T^3-1.06585607E-17*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").label("Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("expr", "pA*0.02897/R_const[K*mol/J]/T");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "293.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("k").label("Piecewise 3");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-0.00227583562+1.15480022E-4*T^1-7.90252856E-8*T^2+4.11702505E-11*T^3-7.43864331E-15*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").label("Analytic 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("expr", "sqrt(1.4*R_const[K*mol/J]/0.02897*T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("fununit", "m/s");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("expr", "-1/rho(pA,T)*d(rho(pA,T),T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").label("Analytic 2a");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("funcname", "muB");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("expr", "0.6*eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"200"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "200", "1600"}});
    model.component("comp1").material("mat1").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("molarmass", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "rho(pA,T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("pressure");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("NonlinearModel").label("Nonlinear model");
    model.component("comp1").material("mat1").propertyGroup("NonlinearModel").set("BA", "def.gamma-1");
    model.component("comp1").material("mat1").propertyGroup("idealGas").label("Ideal gas");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("Rs", "R_const/Mn");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat1").propertyGroup("idealGas").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("idealGas").addInput("pressure");
    model.component("comp1").material("mat1").materialType("nonSolid");

    model.component("comp1").physics("acpr").create("nacc1", "NormalAcceleration", 2);
    model.component("comp1").physics("acpr").feature("nacc1").selection().named("geom1_sel1");
    model.component("comp1").physics("acpr").feature("nacc1").set("nacc", "Nacc");
    model.component("comp1").physics("acpr").create("imp1", "Impedance", 2);
    model.component("comp1").physics("acpr").feature("imp1").selection().named("geom1_difsel1");
    model.component("comp1").physics("acpr").feature("imp1").set("Zn", "Zncouch");
    model.component("comp1").physics("acpr").prop("MeshControl").set("SizeControlParameter", "Frequency");
    model.component("comp1").physics("acpr").prop("MeshControl").set("PhysicsControlledMeshMaximumFrequency", "f0");

    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").view("view1").hideEntities().create("hide1");
    model.component("comp1").view("view1").hideEntities("hide1").geom(2);
    model.component("comp1").view("view1").hideEntities("hide1").add(4);
    model.component("comp1").view("view1").hideEntities("hide1").add(1);
    model.component("comp1").view("view1").hideEntities("hide1").add(6);
    model.component("comp1").view("view1").hideEntities("hide1").add(5);
    model.component("comp1").view("view1").hideEntities("hide1").add(2);
    model.component("comp1").view("view1").hideEntities("hide1").add(15);

    model.study("std1").feature("freq").set("plist", "f0");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s1").feature("i1").active(true);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg1").feature("surf1").set("colortable", "Wave");
    model.result("pg1").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").label("\u58f0\u538b (acpr)");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 1, 0);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"acpr.Lp_t"});
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").feature("surf1").set("colorscalemode", "linear");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").label("\u58f0\u538b\u7ea7 (acpr)");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").setIndex("looplevel", 1, 0);
    model.result("pg3").create("iso1", "Isosurface");
    model.result("pg3").feature("iso1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg3").feature("iso1").set("number", "10");
    model.result("pg3").feature("iso1").set("colortable", "Wave");
    model.result("pg3").feature("iso1").set("colorscalemode", "linearsymmetric");
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").label("\u58f0\u538b\uff0c\u7b49\u503c\u9762 (acpr)");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").run();
    model.result("pg2").feature().remove("surf1");
    model.result("pg2").run();
    model.result("pg2").create("mslc1", "Multislice");
    model.result("pg2").feature("mslc1").set("expr", "acpr.Lp_t");
    model.result("pg2").feature("mslc1").set("descr", "\u603b\u58f0\u538b\u7ea7");
    model.result("pg2").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg2").feature("mslc1").set("xcoord", "-14181 -17000");
    model.result("pg2").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg2").feature("mslc1").set("ycoord", 7542);
    model.result("pg2").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg2").feature("mslc1").set("zcoord", "0 1700");
    model.result("pg2").run();
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").run();
    model.result("pg4").create("str1", "Streamline");
    model.result("pg4").feature("str1").set("expr", new String[]{"acpr.Ix", "acpr.Iy", "acpr.Iz"});
    model.result("pg4").feature("str1").set("descr", "\u5f3a\u5ea6");
    model.result("pg4").feature("str1").set("selnumber", 80);
    model.result("pg4").feature("str1").selection().named("geom1_sel1");
    model.result("pg4").feature("str1").set("linetype", "tube");
    model.result("pg4").feature("str1").set("radiusexpr", "10");
    model.result("pg4").feature("str1").set("tuberadiusscaleactive", true);
    model.result("pg4").feature("str1").create("col1", "Color");
    model.result("pg4").run();
    model.result("pg4").feature("str1").feature("col1").set("expr", "log10(acpr.p_t)");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "1");
    model.result("pg4").feature("surf1").set("titletype", "none");
    model.result("pg4").feature("surf1").set("coloring", "uniform");
    model.result("pg4").feature("surf1").set("color", "custom");
    model.result("pg4").feature("surf1")
         .set("customcolor", new double[]{0.9803921580314636, 0.9411764740943909, 0.9019607901573181});
    model.result("pg4").feature("surf1").create("sel1", "Selection");
    model.result("pg4").feature("surf1").feature("sel1").selection().named("geom1_unisel4");
    model.result("pg4").run();
    model.result("pg4").feature().duplicate("surf2", "surf1");
    model.result("pg4").run();
    model.result("pg4").feature("surf2").set("customcolor", new double[]{0.8784313797950745, 1, 1});
    model.result("pg4").run();
    model.result("pg4").feature("surf2").feature("sel1").selection().named("geom1_unisel5");
    model.result("pg4").run();
    model.result("pg4").feature().duplicate("surf3", "surf2");
    model.result("pg4").run();
    model.result("pg4").feature("surf3").set("customcolor", new double[]{1, 0.6274510025978088, 0.47843137383461});
    model.result("pg4").run();
    model.result("pg4").feature("surf3").feature("sel1").selection().named("geom1_cad1_FloorsGeneric12_bnd");
    model.result("pg4").run();
    model.result("pg4").feature().duplicate("surf4", "surf3");
    model.result("pg4").run();
    model.result("pg4").feature("surf4")
         .set("customcolor", new double[]{0.7686274647712708, 0.4156862795352936, 0.2823529541492462});
    model.result("pg4").run();
    model.result("pg4").feature("surf4").feature("sel1").selection().named("geom1_unisel6");
    model.result("pg4").run();
    model.result("pg4").feature().duplicate("surf5", "surf4");
    model.result("pg4").run();
    model.result("pg4").feature("surf5")
         .set("customcolor", new double[]{0.6980392336845398, 0.13333334028720856, 0.13333334028720856});
    model.result("pg4").run();
    model.result("pg4").feature("surf5").feature("sel1").selection().named("geom1_difsel1");
    model.result("pg4").run();
    model.result("pg4").feature().duplicate("surf6", "surf5");
    model.result("pg4").run();
    model.result("pg4").feature("surf6").set("color", "black");
    model.result("pg4").run();
    model.result("pg4").feature("surf6").feature("sel1").selection()
         .named("geom1_cad1_FurnitureM_TVFlatScreen1270mm_bnd");
    model.result("pg4").run();

    model.study().create("std2");
    model.study("std2").create("eig", "Eigenfrequency");
    model.study("std2").feature("eig").set("plotgroup", "Default");
    model.study("std2").feature("eig").set("ftplistmethod", "manual");
    model.study("std2").feature("eig").set("chkeigregion", true);
    model.study("std2").feature("eig").set("storefact", false);
    model.study("std2").feature("eig").set("linpsolnum", "auto");
    model.study("std2").feature("eig").set("solnum", "auto");
    model.study("std2").feature("eig").set("notsolnum", "auto");
    model.study("std2").feature("eig").set("outputmap", new String[]{});
    model.study("std2").feature("eig").set("ngenAUX", "1");
    model.study("std2").feature("eig").set("goalngenAUX", "1");
    model.study("std2").feature("eig").set("ngenAUX", "1");
    model.study("std2").feature("eig").set("goalngenAUX", "1");
    model.study("std2").feature("eig").setSolveFor("/physics/acpr", true);
    model.study("std2").feature("eig").set("shift", "10[Hz]");
    model.study("std2").feature("eig").set("eigwhich", "lr");
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("e1").feature("i1").active(true);

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").setIndex("looplevel", 1, 0);
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg5").feature("surf1").set("colortable", "Wave");
    model.result("pg5").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg5").set("showlegendsunit", true);
    model.result("pg5").label("\u58f0\u538b (acpr) 1");
    model.result("pg5").feature("surf1").set("colortable", "WaveLight");
    model.result("pg5").create("con1", "Contour");
    model.result("pg5").feature("con1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg5").feature("con1").set("colortable", "Wave");
    model.result("pg5").feature("con1").set("colorscalemode", "linearsymmetric");
    model.result("pg5").feature("con1").set("colorlegend", false);
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").setIndex("looplevel", 1, 0);
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", new String[]{"acpr.Lp_t"});
    model.result("pg6").feature("surf1").set("colortable", "Rainbow");
    model.result("pg6").feature("surf1").set("colorscalemode", "linear");
    model.result("pg6").set("showlegendsunit", true);
    model.result("pg6").label("\u58f0\u538b\u7ea7 (acpr) 1");
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").set("data", "dset2");
    model.result("pg7").setIndex("looplevel", 1, 0);
    model.result("pg7").create("iso1", "Isosurface");
    model.result("pg7").feature("iso1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg7").feature("iso1").set("number", "10");
    model.result("pg7").feature("iso1").set("colortable", "Wave");
    model.result("pg7").feature("iso1").set("colorscalemode", "linearsymmetric");
    model.result("pg7").set("showlegendsunit", true);
    model.result("pg7").label("\u58f0\u538b\uff0c\u7b49\u503c\u9762 (acpr) 1");
    model.result().evaluationGroup().create("std2EvgFrq", "EvaluationGroup");
    model.result().evaluationGroup("std2EvgFrq").set("data", "dset2");
    model.result().evaluationGroup("std2EvgFrq").label("\u7279\u5f81\u9891\u7387 (\u7814\u7a76 2)");
    model.result().evaluationGroup("std2EvgFrq").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("expr", "2*pi*freq", 0);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("unit", "rad/s", 0);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("descr", "\u89d2\u9891\u7387", 0);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("expr", "imag(freq)/abs(freq)", 1);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("unit", "1", 1);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("descr", "\u963b\u5c3c\u6bd4", 1);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("expr", "abs(freq)/imag(freq)/2", 2);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("unit", "1", 2);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("descr", "\u54c1\u8d28\u56e0\u5b50", 2);
    model.result().evaluationGroup("std2EvgFrq").run();
    model.result("pg5").run();
    model.result("pg6").run();
    model.result("pg6").set("looplevel", new int[]{6});
    model.result("pg6").create("iso1", "Isosurface");
    model.result("pg6").feature("iso1").set("expr", "acpr.Lp_t");
    model.result("pg6").feature("iso1").set("descr", "\u603b\u58f0\u538b\u7ea7");
    model.result("pg6").feature("iso1").set("number", 6);
    model.result("pg6").run();
    model.result("pg7").run();
    model.result("pg7").set("looplevel", new int[]{3});
    model.result("pg7").run();
    model.result("pg7").feature("iso1").set("number", 6);
    model.result("pg7").run();
    model.result("pg7").create("surf1", "Surface");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg7").feature("surf1").set("expr", "acpr.absp_t");
    model.result("pg7").feature("surf1").set("descr", "\u7edd\u5bf9\u603b\u58f0\u538b");
    model.result("pg7").run();

    model.title("\u8d77\u5c45\u5ba4\u7684\u58f0\u5b66\u95ee\u9898");

    model
         .description("\u7cbe\u5fc3\u8bbe\u8ba1\u626c\u58f0\u5668\u5728\u5ba4\u5185\u7684\u6446\u653e\u4f4d\u7f6e\uff0c\u53ef\u4ee5\u9632\u6b62\u7a97\u6237\u6296\u52a8\u5e76\u589e\u5f3a\u58f0\u97f3\u6548\u679c\u4f53\u9a8c\u3002\u672c\u6559\u7a0b\u5206\u6790\u8d77\u5c45\u5ba4\u5bf9\u4e24\u4e2a\u626c\u58f0\u5668\u4f4e\u97f3\u5355\u5143\u7684\u4f4e\u9891\u7387\u54cd\u5e94\uff0c\u5e76\u6c42\u89e3\u8d77\u5c45\u5ba4\u7684\u7279\u5f81\u9891\u7387\u3002\u8d77\u5c45\u5ba4\u7684\u51e0\u4f55\u7ed3\u6784\u53ca\u5bb6\u5177\u901a\u8fc7 LiveLink\u2122 \u63a5\u53e3\u4e0e Revit\u00ae \u540c\u6b65\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("living_room_acoustics_llrevit.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
