/*
 * one_family_house.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:26 by COMSOL 6.3.0.290. */
public class one_family_house {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Building_and_Room_Acoustics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("ade", "AcousticDiffusionEquation", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/ade", true);

    model.component("comp1").geom("geom1").insertFile("one_family_house_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("imp1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.param().label("\u53c2\u6570 1 - \u6e90");
    model.param().set("P_s", "0.1[W]");
    model.param().descr("P_s", "\u6e90\u529f\u7387");
    model.param().create("par2");
    model.param("par2").label("\u53c2\u6570 2 - \u5ba4\u5185\u8fb9\u754c");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("a_wall", "0.1", "\u5438\u58f0\u7cfb\u6570\uff0c\u58c1");
    model.param("par2").set("a_ceiling", "0.15", "\u5438\u58f0\u7cfb\u6570\uff0c\u5929\u82b1\u677f");
    model.param("par2").set("a_window", "0.05", "\u5438\u58f0\u7cfb\u6570\uff0c\u7a97\u6237");
    model.param("par2").set("a_stairs", "0.05", "\u5438\u58f0\u7cfb\u6570\uff0c\u697c\u68af");
    model.param("par2").set("a_floor", "0.2", "\u5438\u58f0\u7cfb\u6570\uff0c\u6728\u5730\u677f");
    model.param("par2").set("TL_heavy", "10[dB]", "\u4f20\u8f93\u635f\u8017\uff0c\u91cd\u9694\u677f");
    model.param("par2").set("TL_light", "5[dB]", "\u4f20\u8f93\u635f\u8017\uff0c\u8f7b\u9694\u677f");

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

    model.component("comp1").physics("ade").selection().set(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
    model.component("comp1").physics("ade").feature("room1").selection().set(5);
    model.component("comp1").physics("ade").feature("room1").feature("wall1").selection().all();
    model.component("comp1").physics("ade").feature("room1").feature("wall1").set("alpha", "a_wall");
    model.component("comp1").physics("ade").feature("room1").create("wall2", "Wall", 2);
    model.component("comp1").physics("ade").feature("room1").feature("wall2").selection().named("geom1_sel1");
    model.component("comp1").physics("ade").feature("room1").feature("wall2").set("alpha", "a_window");
    model.component("comp1").physics("ade").feature("room1").create("wall3", "Wall", 2);
    model.component("comp1").physics("ade").feature("room1").feature("wall3").selection().named("geom1_sel2");
    model.component("comp1").physics("ade").feature("room1").feature("wall3").set("alpha", "a_floor");
    model.component("comp1").physics("ade").feature("room1").create("wall4", "Wall", 2);
    model.component("comp1").physics("ade").feature("room1").feature("wall4").selection().named("geom1_sel4");
    model.component("comp1").physics("ade").feature("room1").feature("wall4").set("alpha", "a_ceiling");
    model.component("comp1").physics("ade").feature().duplicate("room2", "room1");
    model.component("comp1").physics("ade").feature("room2").selection().set(8);
    model.component("comp1").physics("ade").feature().duplicate("room3", "room1");
    model.component("comp1").physics("ade").feature("room3").selection().set(7);
    model.component("comp1").physics("ade").feature("room3").feature("wall3").selection().named("geom1_sel3");
    model.component("comp1").physics("ade").feature("room3").feature("wall3").set("alpha", "a_stairs");
    model.component("comp1").physics("ade").feature().duplicate("room4", "room1");
    model.component("comp1").physics("ade").feature("room4").selection().set(9);
    model.component("comp1").physics("ade").feature().duplicate("room5", "room1");
    model.component("comp1").physics("ade").feature("room5").selection().set(4);
    model.component("comp1").physics("ade").feature().duplicate("room6", "room1");
    model.component("comp1").physics("ade").feature("room6").selection().set(3, 10);
    model.component("comp1").physics("ade").feature().duplicate("room7", "room1");
    model.component("comp1").physics("ade").feature("room7").selection().set(2);
    model.component("comp1").physics("ade").feature().duplicate("room8", "room1");
    model.component("comp1").physics("ade").feature("room8").selection().set(6);
    model.component("comp1").physics("ade").feature().duplicate("room9", "room1");
    model.component("comp1").physics("ade").feature("room9").selection().set(12);
    model.component("comp1").physics("ade").feature().duplicate("room10", "room1");
    model.component("comp1").physics("ade").feature("room10").selection().set(11);
    model.component("comp1").physics("ade").create("mrctl1", "MappedRoomCoupling", 2);
    model.component("comp1").physics("ade").feature("mrctl1").selection().named("geom1_sel5");
    model.component("comp1").physics("ade").feature("mrctl1").set("TL", "TL_heavy");
    model.component("comp1").physics("ade").feature().duplicate("mrctl2", "mrctl1");
    model.component("comp1").physics("ade").feature("mrctl2").selection().named("geom1_sel6");
    model.component("comp1").physics("ade").feature().duplicate("mrctl3", "mrctl2");
    model.component("comp1").physics("ade").feature("mrctl3").selection().named("geom1_sel7");
    model.component("comp1").physics("ade").feature().duplicate("mrctl4", "mrctl3");
    model.component("comp1").physics("ade").feature("mrctl4").selection().named("geom1_sel8");
    model.component("comp1").physics("ade").feature().duplicate("mrctl5", "mrctl4");
    model.component("comp1").physics("ade").feature("mrctl5").selection().named("geom1_sel9");
    model.component("comp1").physics("ade").feature().duplicate("mrctl6", "mrctl5");
    model.component("comp1").physics("ade").feature("mrctl6").selection().named("geom1_sel10");
    model.component("comp1").physics("ade").feature().duplicate("mrctl7", "mrctl6");
    model.component("comp1").physics("ade").feature("mrctl7").selection().named("geom1_sel11");
    model.component("comp1").physics("ade").feature().duplicate("mrctl8", "mrctl7");
    model.component("comp1").physics("ade").feature("mrctl8").selection().named("geom1_sel12");
    model.component("comp1").physics("ade").feature("mrctl8").set("TL", "TL_light");
    model.component("comp1").physics("ade").feature().duplicate("mrctl9", "mrctl8");
    model.component("comp1").physics("ade").feature("mrctl9").selection().named("geom1_sel14");
    model.component("comp1").physics("ade").create("rctl1", "RoomCoupling", 2);
    model.component("comp1").physics("ade").feature("rctl1").selection().named("geom1_sel13");
    model.component("comp1").physics("ade").feature("rctl1").set("TL", "TL_light");
    model.component("comp1").physics("ade").feature("rctl1").set("bol_force_cont", true);
    model.component("comp1").physics("ade").create("ps1", "PointSource", 0);
    model.component("comp1").physics("ade").feature("ps1").selection().set(49);
    model.component("comp1").physics("ade").feature("ps1").set("qp", "P_s");

    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76 1 - \u7a33\u6001");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u58f0\u538b\u7ea7 (ade)");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").feature().create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").label("\u591a\u5207\u9762");
    model.result("pg1").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg1").feature("mslc1").set("expr", "ade.Lp");
    model.result("pg1").feature("mslc1").set("smooth", "internal");
    model.result("pg1").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg1").feature("mslc1").set("data", "parent");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u58f0\u80fd\u5bc6\u5ea6 (ade)");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").feature().create("mslc1", "Multislice");
    model.result("pg2").feature("mslc1").label("\u591a\u5207\u9762");
    model.result("pg2").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg2").feature("mslc1").set("smooth", "internal");
    model.result("pg2").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg2").feature("mslc1").set("data", "parent");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u5c40\u90e8\u80fd\u6d41 (ade)");
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").feature().create("arwv1", "ArrowVolume");
    model.result("pg3").feature("arwv1").label("\u4f53\u7bad\u5934");
    model.result("pg3").feature("arwv1").set("showsolutionparams", "on");
    model.result("pg3").feature("arwv1").set("arrowlength", "logarithmic");
    model.result("pg3").feature("arwv1").set("color", "black");
    model.result("pg3").feature("arwv1").set("showsolutionparams", "on");
    model.result("pg3").feature("arwv1").set("data", "parent");
    model.result("pg1").run();
    model.result("pg1").label("\u58f0\u538b\u7ea7 (ade) - \u5207\u9762");
    model.result("pg1").run();
    model.result("pg1").feature("mslc1").set("xnumber", "0");
    model.result("pg1").feature("mslc1").set("ynumber", "0");
    model.result("pg1").feature("mslc1").set("znumber", "4");
    model.result("pg1").run();
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").run();
    model.result("pg4").label("\u58f0\u538b\u7ea7 (ade) - \u8fb9\u754c");
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "ade.Lp");
    model.result("pg4").run();
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("mslc1").set("xnumber", "0");
    model.result("pg2").feature("mslc1").set("ynumber", "2");
    model.result("pg2").feature("mslc1").set("znumber", "3");
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("arwv1").active(false);
    model.result("pg3").run();
    model.result("pg3").create("str1", "Streamline");
    model.result("pg3").feature("str1").set("selnumber", 100);
    model.result("pg3").feature("str1").selection().set(66, 68, 133, 139);
    model.result("pg3").feature("str1").set("linetype", "tube");
    model.result("pg3").feature("str1").create("col1", "Color");
    model.result("pg3").run();
    model.result("pg3").feature("str1").feature("col1").set("expr", "sqrt(ade.Jx^2+ade.Jy^2+ade.Jz^2)");
    model.result("pg3").feature("str1").feature("col1").set("colorscalemode", "logarithmic");
    model.result("pg3").run();
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").run();
    model.result("pg5").label("\u6df7\u54cd\u65f6\u95f4");
    model.result("pg5").create("vol1", "Volume");
    model.result("pg5").feature("vol1").set("expr", "ade.T60_EN");
    model.result("pg5").feature("vol1").set("colortable", "AuroraBorealis");
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").run();
    model.result("pg6").label("\u6a21\u578b\u7f29\u7565\u56fe");
    model.result("pg3").run();
    model.result("pg6").run();
    model.result("pg6").feature().copy("str1", "pg3/str1");
    model.result("pg6").run();
    model.result("pg6").feature("str1").set("selnumber", 50);
    model.result("pg6").run();
    model.result("pg6").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg6").run();
    model.result("pg6").create("slc1", "Slice");
    model.result("pg6").feature("slc1").set("expr", "ade.Lp");
    model.result("pg6").feature("slc1").set("quickplane", "xy");
    model.result("pg6").feature("slc1").set("quickzmethod", "coord");
    model.result("pg6").feature("slc1").set("quickz", 0.3);
    model.result("pg6").feature("slc1").set("colorlegend", false);
    model.result("pg6").run();
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").create("sel1", "Selection");
    model.result("pg6").feature("surf1").feature("sel1").selection().named("geom1_sel3");
    model.result("pg6").run();
    model.result("pg6").feature("surf1").set("expr", "1");
    model.result("pg6").feature("surf1").set("colorlegend", false);
    model.result("pg6").feature("surf1").set("coloring", "uniform");
    model.result("pg6").feature("surf1").set("color", "gray");
    model.result("pg6").feature().duplicate("surf2", "surf1");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").feature("surf2").feature("sel1").selection()
         .set(16, 17, 20, 21, 23, 25, 29, 32, 45, 61, 123, 201);
    model.result("pg6").run();

    model.study().create("std2");
    model.study("std2").create("eigv", "Eigenvalue");
    model.study("std2").feature("eigv").setSolveFor("/physics/ade", true);
    model.study("std2").feature("eigv").set("neigsactive", true);
    model.study("std2").feature("eigv").set("neigs", 10);
    model.study("std2").feature("eigv").set("shiftactive", true);
    model.study("std2").feature("eigv").set("eigwhich", "lr");
    model.study("std2").label("\u7814\u7a76 2 - \u7279\u5f81\u503c");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").label("\u58f0\u80fd\u5bc6\u5ea6\u6a21\u5f0f (ade)");
    model.result("pg7").set("data", "dset2");
    model.result("pg7").setIndex("looplevel", 1, 0);
    model.result("pg7").set("showlegendsunit", true);
    model.result("pg7").feature().create("mslc1", "Multislice");
    model.result("pg7").feature("mslc1").label("\u591a\u5207\u9762");
    model.result("pg7").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg7").feature("mslc1").set("smooth", "internal");
    model.result("pg7").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg7").feature("mslc1").set("data", "parent");
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").set("data", "dset2");
    model.result().evaluationGroup("eg1").set("showlooplevelinput", new String[]{"off", "off", "off"});
    model.result().evaluationGroup("eg1").set("data", "dset2");
    model.result().evaluationGroup("eg1").feature().create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1").label("\u6df7\u54cd\u65f6\u95f4");
    model.result().evaluationGroup("eg1").feature("gev1").set("showsolutionparams", "on");
    model.result().evaluationGroup("eg1").feature("gev1").set("expr", new String[]{"ade.T60"});
    model.result().evaluationGroup("eg1").feature("gev1").set("descr", new String[]{"Reverberation time"});
    model.result().evaluationGroup("eg1").feature("gev1").set("showsolutionparams", "on");
    model.result().evaluationGroup("eg1").feature("gev1").set("data", "parent");
    model.result("pg7").run();
    model.result("pg7").set("looplevel", new int[]{3});
    model.result("pg7").run();
    model.result("pg7").feature("mslc1").set("xnumber", "0");
    model.result("pg7").feature("mslc1").set("ynumber", "0");
    model.result("pg7").feature("mslc1").set("znumber", "2");
    model.result("pg7").run();
    model.result().evaluationGroup("eg1").label("\u6df7\u54cd\u65f6\u95f4");
    model.result().evaluationGroup("eg1").run();

    model.study().create("std3");
    model.study("std3").create("time", "Transient");
    model.study("std3").feature("time").setSolveFor("/physics/ade", true);
    model.study("std3").feature("time").set("tlist", "range(0,0.01,1)");
    model.study("std3").feature("time").set("useadvanceddisable", true);
    model.study("std3").feature("time").set("disabledphysics", new String[]{"ade/ps1"});
    model.study("std3").feature("time").set("useinitsol", true);
    model.study("std3").feature("time").set("initmethod", "sol");
    model.study("std3").feature("time").set("initstudy", "std1");
    model.study("std3").label("\u7814\u7a76 3 - \u77ac\u6001");
    model.study("std3").setGenPlots(false);
    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").run();
    model.result("pg8").label("\u80fd\u91cf\u8870\u51cf\u66f2\u7ebf\uff08\u5f52\u4e00\u5316\uff09");
    model.result("pg8").set("data", "dset3");
    model.result("pg8").set("titletype", "manual");
    model.result("pg8").set("title", "\u80fd\u91cf\u8870\u51cf\u66f2\u7ebf");
    model.result("pg8").set("ylabelactive", true);
    model.result("pg8").set("ylabel", "\u58f0\u538b\u7ea7 (dB)");
    model.result("pg8").create("ptgr1", "PointGraph");
    model.result("pg8").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg8").feature("ptgr1").set("linewidth", "preference");
    model.result("pg8").feature("ptgr1").selection().set(100, 377, 415);
    model.result("pg8").feature("ptgr1").set("expr", "ade.Lp-with('first',ade.Lp)");
    model.result("pg8").feature("ptgr1").set("linewidth", 2);
    model.result("pg8").feature("ptgr1").set("legend", true);
    model.result("pg8").feature("ptgr1").set("legendprefix", "\u70b9");
    model.result("pg8").run();

    model.param().create("par3");
    model.param("par3").label("\u53c2\u6570 3 - \u5ba4\u5916\u8fb9\u754c");

//    To import content from file, use:
//    model.param("par3").loadFile("FILENAME");
    model.param("par3").set("a_ground", "0.2", "\u5438\u58f0\u7cfb\u6570\uff0c\u5730\u9762");
    model.param("par3").set("s_ground", "0.15", "\u6563\u5c04\u7cfb\u6570\uff0c\u5730\u9762");
    model.param("par3").set("a_extwall", "0.1", "\u5438\u58f0\u7cfb\u6570\uff0c\u5916\u5899");
    model.param("par3").set("s_extwall", "0.1", "\u6563\u5c04\u7cfb\u6570\uff0c\u5916\u5899");
    model.param("par3").set("s_window", "0.1", "\u6563\u5c04\u7cfb\u6570\uff0c\u7a97\u6237");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u8f90\u5c04\u7a97\u6237 1");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").set(43);
    model.component("comp1").selection().duplicate("sel2", "sel1");
    model.component("comp1").selection("sel2").label("\u8f90\u5c04\u7a97\u6237 2");
    model.component("comp1").selection("sel2").set(110, 146);

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop1").selection().named("sel1");
    model.component("comp1").cpl().duplicate("intop2", "intop1");
    model.component("comp1").cpl("intop2").selection().named("sel2");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").label("\u53d8\u91cf 1 - \u5ba4\u5916\u8f90\u5c04");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("Pwin1", "intop1(ade.psq/ade.rho/ade.c)", "\u7a97\u6237 1 \u5185\u7684\u529f\u7387");
    model.component("comp1").variable("var1")
         .set("Pwin2", "intop2(ade.psq/ade.rho/ade.c)", "\u7a97\u6237 2 \u5185\u7684\u529f\u7387");
    model.component("comp1").variable("var1").set("TLwin", "20[dB]", "\u7a97\u6237\u7684\u4f20\u8f93\u635f\u8017");
    model.component("comp1").variable("var1").set("tau", "10^(-TLwin/10)", "\u529f\u7387\u4f20\u8f93\u7cfb\u6570");
    model.component("comp1").variable("var1")
         .set("Prad1", "Pwin1*tau", "\u7a97\u6237 1 \u7684\u8f90\u5c04\u529f\u7387");
    model.component("comp1").variable("var1")
         .set("Prad2", "Pwin2*tau", "\u7a97\u6237 2 \u7684\u8f90\u5c04\u529f\u7387");

    model.component("comp1").physics().create("rac", "RayAcoustics", "geom1");

    model.study("std1").feature("stat").setSolveFor("/physics/rac", false);
    model.study("std2").feature("eigv").setSolveFor("/physics/rac", false);
    model.study("std3").feature("time").setSolveFor("/physics/rac", false);

    model.component("comp1").physics("rac").selection().set(1);

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").physics("rac").feature("mp1").set("FluidModel", "Atmosphere");
    model.component("comp1").physics("rac").feature("mp1").set("minput_relativehumidity", 0.45);
    model.component("comp1").physics("rac").create("relb1", "ReleaseFromBoundary", 2);
    model.component("comp1").physics("rac").feature("relb1").selection().named("sel1");
    model.component("comp1").physics("rac").feature("relb1").setIndex("Nr", 200, 0);
    model.component("comp1").physics("rac").feature("relb1").set("RayDirectionVector", "Hemispherical");
    model.component("comp1").physics("rac").feature("relb1").set("SpecifyInletTangentialNormal", true);
    model.component("comp1").physics("rac").feature("relb1").setIndex("Nw", 500, 0);
    model.component("comp1").physics("rac").feature("relb1").set("rax", new int[]{0, 0, 1});
    model.component("comp1").physics("rac").feature("relb1").set("Psrc", "Prad1");
    model.component("comp1").physics("rac").feature().duplicate("relb2", "relb1");
    model.component("comp1").physics("rac").feature("relb2").selection().named("sel2");
    model.component("comp1").physics("rac").feature("relb2").set("Psrc", "Prad2");
    model.component("comp1").physics("rac").create("wall2", "Wall", 2);
    model.component("comp1").physics("rac").feature("wall2").label("\u5899\u58c1 2 - \u63a5\u5730");
    model.component("comp1").physics("rac").feature("wall2").selection().set(3);
    model.component("comp1").physics("rac").feature("wall2").set("WallCondition", "MixedDiffuseSpecular");
    model.component("comp1").physics("rac").feature("wall2").set("gammas", "1-s_ground");
    model.component("comp1").physics("rac").feature("wall2").set("alphas", "a_ground");
    model.component("comp1").physics("rac").feature("wall2").set("alphad", "a_ground");
    model.component("comp1").physics("rac").feature("wall2").create("spl1", "SoundPressureLevelBoundary", 2);
    model.component("comp1").physics("rac").create("wall3", "Wall", 2);
    model.component("comp1").physics("rac").feature("wall3").label("\u5899\u58c1 3 - \u5916\u5899");
    model.component("comp1").physics("rac").feature("wall3").selection()
         .set(33, 34, 35, 40, 41, 42, 51, 52, 53, 73, 74, 103, 104, 105, 106, 107, 108, 109, 143, 144, 145, 175, 226);
    model.component("comp1").physics("rac").feature("wall3").set("WallCondition", "MixedDiffuseSpecular");
    model.component("comp1").physics("rac").feature("wall3").set("gammas", "1-s_extwall");
    model.component("comp1").physics("rac").feature("wall3").set("alphas", "a_extwall");
    model.component("comp1").physics("rac").feature("wall3").set("alphad", "a_extwall");
    model.component("comp1").physics("rac").feature().duplicate("wall4", "wall3");
    model.component("comp1").physics("rac").feature("wall4").label("\u5899\u58c1 4 - \u7a97\u6237");
    model.component("comp1").physics("rac").feature("wall4").selection().set(43, 54, 110, 111, 146);
    model.component("comp1").physics("rac").feature("wall4").set("gammas", "1-s_window");
    model.component("comp1").physics("rac").feature("wall4").set("alphas", "a_window");
    model.component("comp1").physics("rac").feature("wall4").set("alphad", "a_window");
    model.component("comp1").physics("rac").create("wall5", "Wall", 2);
    model.component("comp1").physics("rac").feature("wall5").label("\u5899\u58c1 5 - \u7a7a\u6c14");
    model.component("comp1").physics("rac").feature("wall5").selection().set(1, 2, 4, 5, 259);
    model.component("comp1").physics("rac").feature("wall5").set("WallCondition", "Disappear");

    model.study().create("std4");
    model.study("std4").create("rtrac", "RayTracing");
    model.study("std4").feature("rtrac").setSolveFor("/physics/ade", false);
    model.study("std4").feature("rtrac").setSolveFor("/physics/rac", true);
    model.study("std4").feature("rtrac").set("tunit", "s");
    model.study("std4").feature("rtrac").set("tlist", "0 0.2");
    model.study("std4").feature("rtrac").set("usesol", true);
    model.study("std4").feature("rtrac").set("notsolmethod", "sol");
    model.study("std4").feature("rtrac").set("notstudy", "std1");
    model.study("std4").label("\u7814\u7a76 4 - \u5c04\u7ebf\u58f0\u5b66");
    model.study("std4").createAutoSequences("all");

    model.sol("sol4").runAll();

    model.result().dataset().create("ray1", "Ray");
    model.result().dataset("ray1").set("solution", "sol4");
    model.result().dataset("ray1").set("posdof", new String[]{"comp1.qx", "comp1.qy", "comp1.qz"});
    model.result().dataset("ray1").set("geom", "geom1");
    model.result().dataset("ray1").set("rgeom", "pgeom_rac");
    model.result().dataset("ray1").set("rgeomspec", "fromphysics");
    model.result().dataset("ray1").set("physicsinterface", "rac");
    model.result().create("pg9", "PlotGroup3D");
    model.result("pg9").set("data", "ray1");
    model.result("pg9").setIndex("looplevel", 2, 0);
    model.result("pg9").label("\u5c04\u7ebf\u8f68\u8ff9 (rac)");
    model.result("pg9").set("showlegendsunit", true);
    model.result("pg9").create("rtrj1", "RayTrajectories");
    model.result("pg9").feature("rtrj1").set("linetype", "line");
    model.result("pg9").feature("rtrj1").set("extrasteps", "none");
    model.result("pg9").feature("rtrj1").create("col1", "Color");
    model.result("pg9").feature("rtrj1").feature("col1").set("expr", "t");
    model.result("pg9").feature("rtrj1").create("filt1", "RayTrajectoriesFilter");
    model.result("pg9").run();
    model.result("pg9").setIndex("looplevel", "interp", 0);
    model.result("pg9").set("interp", new double[]{0.003});
    model.result("pg9").run();
    model.result("pg9").feature("rtrj1").set("linetype", "none");
    model.result("pg9").feature("rtrj1").set("pointtype", "point");
    model.result("pg9").run();
    model.result("pg9").feature("rtrj1").feature("col1").set("expr", "rac.Q");
    model.result("pg9").run();
    model.result().create("pg10", "PlotGroup3D");
    model.result("pg10").run();
    model.result("pg10").label("\u5ba4\u5916\u58f0\u538b\u7ea7");
    model.result("pg10").set("data", "dset4");
    model.result("pg10").set("showlegendsunit", true);
    model.result("pg10").create("surf1", "Surface");
    model.result("pg10").feature("surf1").set("expr", "rac.wall2.spl1.Lp");
    model.result("pg10").feature("surf1").set("rangecoloractive", true);
    model.result("pg10").feature("surf1").set("rangecolormin", 65);
    model.result("pg10").feature("surf1").set("rangecolormax", 115);
    model.result("pg10").feature().duplicate("surf2", "surf1");
    model.result("pg10").run();
    model.result("pg10").feature("surf2").set("data", "dset1");
    model.result("pg10").feature("surf2").set("expr", "ade.Lp");
    model.result("pg10").feature("surf2").set("titletype", "none");
    model.result("pg10").feature("surf2").set("colorlegend", false);
    model.result("pg10").run();
    model.result("pg6").run();

    model.title("\u5355\u6237\u4f4f\u5b85\u58f0\u5b66\u5206\u6790");

    model
         .description("\u672c\u6a21\u578b\u6f14\u793a\u201c\u58f0\u5b66\u6269\u6563\u65b9\u7a0b\u201d\u7269\u7406\u573a\u63a5\u53e3\u7684\u5e94\u7528\uff0c\u5206\u6790\u4e86\u7531 10\u00a0\u4e2a\u623f\u95f4\u7ec4\u6210\u7684\u4e24\u5c42\u5355\u6237\u4f4f\u5b85\u7684\u58f0\u5b66\u95ee\u9898\u3002\n\n\u5176\u4e2d\u5206\u6790\u4f4d\u4e8e\u5ba2\u5385\u7684\u5355\u6781\u6e90\u7684\u7a33\u6001\u58f0\u538b\u7ea7\u548c\u80fd\u91cf\u5bc6\u5ea6\u5206\u5e03\uff0c\u968f\u540e\u4f7f\u7528\u7279\u5f81\u503c\u6c42\u89e3\u5668\u7814\u7a76\u4e86\u4e0d\u540c\u8026\u5408\u623f\u95f4\u7684\u6df7\u54cd\u65f6\u95f4 T60\uff0c\u8fd8\u53ef\u4ee5\u901a\u8fc7\u77ac\u6001\u7814\u7a76\u53d1\u73b0\u77ac\u6001\u80fd\u91cf\u8870\u51cf\u3002\n\n\u6b64\u5916\uff0c\u901a\u8fc7\u624b\u52a8\u8026\u5408\u5230\u201c\u5c04\u7ebf\u58f0\u5b66\u201d\u7269\u7406\u573a\u63a5\u53e3\uff0c\u53ef\u4ee5\u786e\u5b9a\u5728\u4f4f\u5b85\u5916\u90e8\u8f90\u5c04\u7684\u58f0\u538b\u7ea7\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();

    model.label("one_family_house.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
