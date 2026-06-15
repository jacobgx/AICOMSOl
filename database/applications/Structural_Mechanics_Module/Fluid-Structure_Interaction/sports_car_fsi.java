/*
 * sports_car_fsi.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:09 by COMSOL 6.3.0.290. */
public class sports_car_fsi {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Fluid-Structure_Interaction");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("spf", "LESRBVM", "geom1");
    model.component("comp1").physics().create("ipf", "IncompressiblePotentialFlow", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ipf", true);

    model.param().set("U0", "50[m/s]");
    model.param().descr("U0", "Car velocity");
    model.param().set("R_w", "0.33[m]");
    model.param().descr("R_w", "Wheel radius");
    model.param().set("L_wb", "103.1[in]");
    model.param().descr("L_wb", "Length, wheel base");
    model.param().set("x_w", "40.4[in]");
    model.param().descr("x_w", "Position, front wheel");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1")
         .set("Uwx", "-U0*(z/R_w-1)*(1-exp(-(z-0.01[m])/0.0025[m]))+U0*exp(-(z-0.01[m])/0.0025[m])");
    model.component("comp1").variable("var1").descr("Uwx", "Tire velocity, x-component");
    model.component("comp1").variable("var1").set("Uwz_front", "U0*(x-x_w)/R_w*(1-exp(-(z-0.01[m])/0.0025[m]))");
    model.component("comp1").variable("var1").descr("Uwz_front", "Front tire velocity,z-component");
    model.component("comp1").variable("var1").set("Uwz_rear", "U0*(x-x_w-L_wb)/R_w*(1-exp(-(z-0.01[m])/0.0025[m]))");
    model.component("comp1").variable("var1").descr("Uwz_rear", "Rear tire velocity,z-component");

    model.component("comp1").geom("geom1").geomRep("cadps");
    model.component("comp1").geom("geom1").designBooleans(true);
    model.component("comp1").geom("geom1").insertFile("sports_car_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("igv1");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").run("igv1");

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
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-8.38278E-7+8.35717342E-8*T^1-7.69429583E-11*T^2+4.6437266E-14*T^3-1.06585607E-17*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
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
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-0.00227583562+1.15480022E-4*T^1-7.90252856E-8*T^2+4.11702505E-11*T^3-7.43864331E-15*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("expr", "sqrt(1.4*R_const[K*mol/J]/0.02897*T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("fununit", "m/s");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
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
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("NonlinearModel").set("BA", "def.gamma-1");
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

    model.component("comp1").physics("spf").prop("TurbulenceModelProperty").set("LESWallTreatment", "Automatic");
    model.component("comp1").physics("spf").selection().set(1);
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 2);
    model.component("comp1").physics("spf").feature("inl1").selection().set(1);
    model.component("comp1").physics("spf").feature("inl1").set("U0in", "U0");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 2);
    model.component("comp1").physics("spf").feature("out1").selection().set(265);
    model.component("comp1").physics("spf").create("wallbc2", "WallBC", 2);
    model.component("comp1").physics("spf").feature("wallbc2").selection().set(2, 4, 5);
    model.component("comp1").physics("spf").feature("wallbc2").set("BoundaryCondition", "Slip");
    model.component("comp1").physics("spf").create("wallbc3", "WallBC", 2);
    model.component("comp1").physics("spf").feature("wallbc3").selection().set(3);
    model.component("comp1").physics("spf").feature("wallbc3").set("SlidingWall", true);
    model.component("comp1").physics("spf").feature("wallbc3").set("uvwwall", new String[]{"U0", "0", "0"});
    model.component("comp1").physics("spf").create("wallbc4", "WallBC", 2);
    model.component("comp1").physics("spf").feature("wallbc4").selection().named("geom1_adjsel2");
    model.component("comp1").physics("spf").feature("wallbc4").set("TranslationalVelocityOption", "Manual");
    model.component("comp1").physics("spf").feature("wallbc4").set("utr", new String[]{"Uwx", "0", "Uwz_front"});
    model.component("comp1").physics("spf").feature().duplicate("wallbc5", "wallbc4");
    model.component("comp1").physics("spf").feature("wallbc5").selection().named("geom1_adjsel3");
    model.component("comp1").physics("spf").feature("wallbc5").set("utr", new String[]{"Uwx", "0", "Uwz_rear"});
    model.component("comp1").physics("ipf").prop("PressureProperty").set("UScale", "U0");
    model.component("comp1").physics("ipf").prop("ShapeProperty").set("order_velocitypotential", 1);
    model.component("comp1").physics("ipf").create("velbc1", "Velocity", 2);
    model.component("comp1").physics("ipf").feature("velbc1").selection().set(1);
    model.component("comp1").physics("ipf").feature("velbc1").set("Uin", "U0");
    model.component("comp1").physics("ipf").create("open1", "OpenBoundary", 2);
    model.component("comp1").physics("ipf").feature("open1").selection().set(265);

    model.component("comp1").mesh("mesh1").contribute("geom/detail", false);
    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", 0.5);
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", 0.05);
    model.component("comp1").mesh("mesh1").feature("size").set("hgrad", 1.11);
    model.component("comp1").mesh("mesh1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmax", 0.3);
    model.component("comp1").mesh("mesh1").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmin", 0.05);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp1").set("blnlayers", 5);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp1").set("blhminfact", 2.5);

    model.study("std1").feature("stat").setSolveFor("/physics/spf", false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("Velocity from Potential Flow Solution (ipf)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("slc1", "Slice");
    model.result("pg1").feature("slc1").label("Slice");
    model.result("pg1").feature("slc1").set("showsolutionparams", "on");
    model.result("pg1").feature("slc1").set("expr", "ipf.U");
    model.result("pg1").feature("slc1").set("smooth", "internal");
    model.result("pg1").feature("slc1").set("showsolutionparams", "on");
    model.result("pg1").feature("slc1").set("data", "parent");
    model.result("pg1").run();
    model.result("pg1").label("Potential flow");
    model.result("pg1").set("edges", false);
    model.result("pg1").run();
    model.result("pg1").feature("slc1").set("quickplane", "zx");
    model.result("pg1").feature("slc1").set("quickynumber", 1);
    model.result("pg1").run();
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", "1");
    model.result("pg1").feature("surf1").set("coloring", "uniform");
    model.result("pg1").feature("surf1").set("color", "gray");
    model.result("pg1").feature("surf1").set("titletype", "none");
    model.result("pg1").feature("surf1").create("sel1", "Selection");
    model.result("pg1").feature("surf1").feature("sel1").selection().named("geom1_boxsel16");
    model.result("pg1").run();
    model.result("pg1").feature("slc1").set("colortable", "JupiterAuroraBorealis");
    model.result("pg1").feature("slc1").set("colortabletrans", "reverse");
    model.result("pg1").feature("slc1").set("rangecoloractive", true);
    model.result("pg1").feature("slc1").set("rangecolormin", 0);
    model.result("pg1").feature("slc1").set("rangecolormax", 50);
    model.result("pg1").feature("slc1").create("tran1", "Transparency");
    model.result("pg1").run();
    model.result("pg1").run();

    model.component("comp1").physics().create("spf2", "TurbulentFlowkeps", "geom1");

    model.study("std1").feature("stat").setSolveFor("/physics/spf2", true);

    model.component("comp1").physics("spf2").selection().set(1);
    model.component("comp1").physics("spf2").feature().copy("inl1", "spf/inl1");
    model.component("comp1").physics("spf2").feature().copy("out1", "spf/out1");
    model.component("comp1").physics("spf2").feature().copy("wallbc2", "spf/wallbc2");
    model.component("comp1").physics("spf2").feature().copy("wallbc3", "spf/wallbc3");
    model.component("comp1").physics("spf2").feature().copy("wallbc4", "spf/wallbc4");
    model.component("comp1").physics("spf2").feature().copy("wallbc5", "spf/wallbc5");
    model.component("comp1").physics("spf2").feature("init1").set("u_init", new String[]{"ipf.u", "ipf.v", "ipf.w"});
    model.component("comp1").physics("spf2").feature("init1").set("p_init", "ipf.p");

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/spf", false);
    model.study("std2").feature("stat").setSolveFor("/physics/ipf", false);
    model.study("std2").feature("stat").setSolveFor("/physics/spf2", true);
    model.study("std2").feature("stat").set("useinitsol", true);
    model.study("std2").feature("stat").set("initstudy", "std1");
    model.study("std2").feature("stat").set("usesol", true);
    model.study("std2").feature("stat").set("notsolmethod", "sol");
    model.study("std2").feature("stat").set("notstudy", "std1");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset("dset2").set("geom", "geom1");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("Velocity (spf2)");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("slc1", "Slice");
    model.result("pg2").feature("slc1").label("Slice");
    model.result("pg2").feature("slc1").set("showsolutionparams", "on");
    model.result("pg2").feature("slc1").set("expr", "spf2.U");
    model.result("pg2").feature("slc1").set("smooth", "internal");
    model.result("pg2").feature("slc1").set("showsolutionparams", "on");
    model.result("pg2").feature("slc1").set("data", "parent");
    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").label("Exterior Walls");
    model.result().dataset("surf1").set("data", "dset2");
    model.result().dataset("surf1").selection().geom("geom1", 2);
    model.result().dataset("surf1").selection()
         .set(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255, 256, 257, 258, 259, 260, 261, 262, 263, 264);
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("Pressure (spf2)");
    model.result("pg3").set("data", "surf1");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("Surface");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("expr", "p2");
    model.result("pg3").feature("surf1").set("colortable", "Dipole");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result("pg3").feature("surf1").feature().create("tran1", "Transparency");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("Wall Resolution (spf2)");
    model.result("pg4").set("data", "surf1");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").label("Wall Resolution");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("expr", "spf2.Delta_wPlus");
    model.result("pg4").feature("surf1").set("smooth", "internal");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("slc1").set("quickplane", "zx");
    model.result("pg2").feature("slc1").set("quickynumber", 1);
    model.result("pg2").feature("slc1").set("colortable", "JupiterAuroraBorealis");
    model.result("pg2").feature("slc1").set("colortabletrans", "reverse");
    model.result("pg2").feature("slc1").set("rangecoloractive", true);
    model.result("pg2").feature("slc1").set("rangecolormin", 0);
    model.result("pg2").feature("slc1").set("rangecolormax", 50);
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").set("edges", false);
    model.result("pg2").feature().copy("surf1", "pg1/surf1");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("slc1").create("tran1", "Transparency");
    model.result("pg2").run();
    model.result("pg2").run();

    model.component("comp1").mesh().duplicate("mesh2", "mesh1");
    model.component("comp1").mesh("mesh2").feature("size").set("hmin", 0.06);
    model.component("comp1").mesh("mesh2").feature("size").set("hgrad", 1.13);
    model.component("comp1").mesh("mesh2").feature("size").set("hcurve", 0.5);
    model.component("comp1").mesh("mesh2").feature("size").set("hnarrow", 0.8);
    model.component("comp1").mesh("mesh2").feature("size1").set("hgradactive", true);
    model.component("comp1").mesh("mesh2").feature("size1").set("hcurveactive", true);
    model.component("comp1").mesh("mesh2").feature("size1").set("hnarrowactive", true);
    model.component("comp1").mesh("mesh2").feature("size1").set("hmin", 0.1);
    model.component("comp1").mesh("mesh2").feature("size1").set("hgrad", 1.13);
    model.component("comp1").mesh("mesh2").feature("size1").set("hcurve", 0.5);
    model.component("comp1").mesh("mesh2").feature("size1").set("hnarrow", 0.8);
    model.component("comp1").mesh("mesh2").create("size2", "Size");
    model.component("comp1").mesh("mesh2").feature().move("size2", 2);
    model.component("comp1").mesh("mesh2").feature("size2").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh2").feature("size2").selection().named("geom1_boxsel16");
    model.component("comp1").mesh("mesh2").feature("size2").set("table", "cfd");
    model.component("comp1").mesh("mesh2").feature("size2").set("hauto", 2);
    model.component("comp1").mesh("mesh2").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh2").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh2").feature("size2").set("hmax", 0.15);
    model.component("comp1").mesh("mesh2").feature("size2").set("hminactive", true);
    model.component("comp1").mesh("mesh2").feature("size2").set("hmin", 0.08);
    model.component("comp1").mesh("mesh2").feature("size2").set("hgradactive", true);
    model.component("comp1").mesh("mesh2").feature("size2").set("hgrad", 1.1);
    model.component("comp1").mesh("mesh2").feature("size2").set("hcurveactive", true);
    model.component("comp1").mesh("mesh2").feature("size2").set("hcurve", 0.4);
    model.component("comp1").mesh("mesh2").feature("size2").set("hnarrowactive", true);
    model.component("comp1").mesh("mesh2").feature("size2").set("hnarrow", 0.9);
    model.component("comp1").mesh("mesh2").feature("bl1").feature("blp1").set("blnlayers", 8);
    model.component("comp1").mesh("mesh2").feature("bl1").feature("blp1").set("inittype", "blhmin");
    model.component("comp1").mesh("mesh2").feature("bl1").feature("blp1").set("blhmin", 0.003);

    model.component("comp1").physics("spf").feature("init1").set("u_init", new String[]{"u2", "v2", "w2"});
    model.component("comp1").physics("spf").feature("init1").set("p_init", "p2");

    model.study().create("std3");
    model.study("std3").create("time", "Transient");
    model.study("std3").feature("time").setSolveFor("/physics/spf", true);
    model.study("std3").feature("time").setSolveFor("/physics/ipf", false);
    model.study("std3").feature("time").setSolveFor("/physics/spf2", false);
    model.study("std3").feature("time").set("useinitsol", true);
    model.study("std3").feature("time").set("initstudy", "std2");
    model.study("std3").feature("time").set("usesol", true);
    model.study("std3").feature("time").set("notsolmethod", "sol");
    model.study("std3").feature("time").set("notstudy", "std2");
    model.study("std3").feature("time").set("tlist", "range(0,0.1,0.6), range(0.6,0.002,0.7)");
    model.study("std3").showAutoSequences("all");

    model.sol("sol3").feature("t1").set("maxstepexpressiongenalpha", "3e-5");

    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().dataset("dset3").set("geom", "geom1");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("Velocity (spf)");
    model.result("pg5").set("data", "dset3");
    model.result("pg5").setIndex("looplevel", 58, 0);
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").feature().create("slc1", "Slice");
    model.result("pg5").feature("slc1").label("Slice");
    model.result("pg5").feature("slc1").set("showsolutionparams", "on");
    model.result("pg5").feature("slc1").set("smooth", "internal");
    model.result("pg5").feature("slc1").set("showsolutionparams", "on");
    model.result("pg5").feature("slc1").set("data", "parent");
    model.result().dataset().create("surf2", "Surface");
    model.result().dataset("surf2").label("Exterior Walls 1");
    model.result().dataset("surf2").set("data", "dset3");
    model.result().dataset("surf2").selection().geom("geom1", 2);
    model.result().dataset("surf2").selection()
         .set(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255, 256, 257, 258, 259, 260, 261, 262, 263, 264);
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").label("Pressure (spf)");
    model.result("pg6").set("data", "surf2");
    model.result("pg6").setIndex("looplevel", 58, 0);
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").feature().create("surf1", "Surface");
    model.result("pg6").feature("surf1").label("Surface");
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("expr", "p");
    model.result("pg6").feature("surf1").set("colortable", "Dipole");
    model.result("pg6").feature("surf1").set("smooth", "internal");
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("data", "parent");
    model.result("pg6").feature("surf1").feature().create("tran1", "Transparency");
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").label("Wall Resolution (spf)");
    model.result("pg7").set("data", "dset3");
    model.result("pg7").setIndex("looplevel", 58, 0);
    model.result("pg7").set("frametype", "spatial");
    model.result("pg7").feature().create("surf1", "Surface");
    model.result("pg7").feature("surf1").label("Wall Resolution");
    model.result("pg7").feature("surf1").set("showsolutionparams", "on");
    model.result("pg7").feature("surf1").set("expr", "spf.WRHeightExpr");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg7").feature("surf1").set("smooth", "internal");
    model.result("pg7").feature("surf1").set("showsolutionparams", "on");
    model.result("pg7").feature("surf1").set("data", "parent");
    model.result("pg5").run();
    model.result().setOnlyPlotWhenRequested(true);
    model.result("pg5").feature("slc1").set("quickplane", "zx");
    model.result("pg5").feature("slc1").set("quickynumber", 1);
    model.result("pg5").feature("slc1").set("colortable", "JupiterAuroraBorealis");
    model.result("pg5").feature("slc1").set("colortabletrans", "reverse");
    model.result("pg5").feature("slc1").set("rangecoloractive", true);
    model.result("pg5").feature("slc1").set("rangecolormin", 0);
    model.result("pg5").feature("slc1").set("rangecolormax", 50);
    model.result("pg5").feature("slc1").create("tran1", "Transparency");
    model.result("pg5").set("edges", false);
    model.result("pg5").feature().copy("surf1", "pg2/surf1");
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").set("data", "dset3");
    model.result("pg8").label("Car with velocity slices");
    model.result("pg8").create("slc1", "Slice");
    model.result("pg8").feature("slc1").set("quickplane", "zx");
    model.result("pg8").feature("slc1").set("quickynumber", 1);
    model.result("pg8").feature("slc1").set("colortable", "JupiterAuroraBorealis");
    model.result("pg8").feature().duplicate("slc2", "slc1");
    model.result("pg8").feature("slc2").set("quickplane", "xy");
    model.result("pg8").feature("slc2").set("quickzmethod", "coord");
    model.result("pg8").feature("slc2").set("quickz", ".05");
    model.result("pg8").feature("slc2").set("inheritplot", "slc1");
    model.result("pg8").run();
    model.result("pg8").feature("slc1").create("tran1", "Transparency");
    model.result("pg8").feature("slc2").set("titletype", "none");
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").label("Tires");
    model.result("pg8").feature("surf1").set("expr", "1");
    model.result("pg8").feature("surf1").set("titletype", "none");
    model.result("pg8").feature("surf1").create("sel1", "Selection");
    model.result("pg8").feature("surf1").feature("sel1").selection().named("geom1_sel15");
    model.result("pg8").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg8").feature("surf1").feature("mtrl1").set("appearance", "custom");
    model.result("pg8").feature("surf1").feature("mtrl1").set("family", "rubber");
    model.result("pg8").feature("surf1").feature("mtrl1").set("color", "black");
    model.result("pg8").set("edges", false);
    model.result("pg8").create("surf2", "Surface");
    model.result("pg8").feature("surf2").label("Wheel Bays");
    model.result("pg8").feature("surf2").set("titletype", "none");
    model.result("pg8").feature("surf2").create("sel1", "Selection");
    model.result("pg8").feature("surf2").feature("sel1").selection().named("geom1_sel7");
    model.result("pg8").feature("surf2").set("coloring", "uniform");
    model.result("pg8").feature("surf2").set("color", "custom");
    model.result("pg8").feature("surf2")
         .set("customcolor", new double[]{0.32549020648002625, 0.3294117748737335, 0.41960784792900085});
    model.result("pg8").feature().duplicate("surf3", "surf1");
    model.result("pg8").feature("surf3").label("Windows");
    model.result("pg8").feature("surf3").feature("sel1").selection().named("geom1_sel11");
    model.result("pg8").feature("surf3").feature("mtrl1").set("family", "water");
    model.result("pg8").feature().duplicate("surf4", "surf3");
    model.result("pg8").feature("surf4").label("Headlights");
    model.result("pg8").feature("surf4").feature("sel1").selection().named("geom1_sel9");
    model.result("pg8").feature("surf4").feature("mtrl1").set("family", "plasticshiny");
    model.result("pg8").feature("surf4").feature("mtrl1").set("color", "white");
    model.result("pg8").feature().duplicate("surf5", "surf4");
    model.result("pg8").feature("surf5").label("Rims");
    model.result("pg8").feature("surf5").feature("sel1").selection().named("geom1_unisel1");
    model.result("pg8").feature("surf5").feature("mtrl1").set("family", "rosegold");
    model.result("pg8").feature().duplicate("surf6", "surf5");
    model.result("pg8").feature("surf6").label("Body");
    model.result("pg8").feature("surf6").feature("sel1").selection().named("geom1_difsel3");

    model.component("comp1").view("view1").set("ssao", true);
    model.component("comp1").view("view1").set("environmentmap", "envmap_meadow");

    model.result("pg8").feature().duplicate("surf7", "surf6");
    model.result("pg8").feature("surf7").label("Rear Lights");
    model.result("pg8").feature("surf7").feature("sel1").selection().named("geom1_sel10");
    model.result("pg8").feature("surf7").feature("mtrl1").set("family", "plasticshiny");
    model.result("pg8").feature("surf7").feature("mtrl1").set("color", "red");
    model.result("pg8").run();
    model.result("pg8").feature().duplicate("surf8", "surf2");
    model.result("pg8").feature("surf8").label("Air vents");
    model.result("pg8").feature("surf8").feature("sel1").selection().named("geom1_sel8");
    model.result("pg8").feature("surf8").set("customcolor", new double[]{0, 0, 0});
    model.result("pg8").feature().duplicate("surf9", "surf2");
    model.result("pg8").run();
    model.result("pg8").run();
    model.result().duplicate("pg9", "pg8");
    model.result("pg9").label("Streamlines");
    model.result("pg9").feature("slc1").active(false);
    model.result("pg9").feature("slc2").active(false);
    model.result("pg9").create("str1", "Streamline");
    model.result("pg9").feature("str1").set("posmethod", "start");
    model.result("pg9").feature("str1").set("startmethod", "coord");
    model.result("pg9").feature("str1").set("xcoord", -8);
    model.result("pg9").feature("str1").set("ycoord", 0);
    model.result("pg9").feature("str1").set("zcoord", "range(0,2/39,2)");
    model.result("pg9").feature("str1").set("linetype", "tube");
    model.result("pg9").feature("str1").set("radiusexpr", ".005");
    model.result("pg9").feature("str1").set("tuberadiusscaleactive", true);
    model.result("pg9").feature("str1").set("back", false);
    model.result("pg9").feature("str1").create("col1", "Color");
    model.result("pg9").feature("str1").feature("col1").set("rangecoloractive", true);
    model.result("pg9").feature("str1").feature("col1").set("rangecolormax", 70);
    model.result("pg9").feature("str1").feature("col1").set("colortable", "RainbowLight");
    model.result("pg9").feature().duplicate("str2", "str1");
    model.result("pg9").feature("str2").set("xcoord", 4.31);
    model.result("pg9").feature("str2").set("ycoord", "range(-0.8,0.041025641025641026,0.8)");
    model.result("pg9").feature("str2").set("zcoord", 0.88);
    model.result("pg9").feature("str2").set("inheritplot", "str1");
    model.result("pg9").feature().duplicate("str3", "str2");
    model.result("pg9").feature("str3").set("zcoord", 0.1);
    model.result("pg9").feature().duplicate("str4", "str3");
    model.result("pg9").feature("str4").set("xcoord", -8);
    model.result("pg9").feature("str4").set("ycoord", "range(-1.5,0.043478260869565216,1.5)");
    model.result("pg9").run();
    model.result("pg9").create("surf10", "Surface");
    model.result("pg9").feature("surf10").create("sel1", "Selection");
    model.result("pg9").feature("surf10").feature("sel1").selection().set(3);
    model.result("pg9").feature("surf10").create("mtrl1", "MaterialAppearance");
    model.result("pg9").feature("surf10").feature("mtrl1").set("appearance", "custom");
    model.result("pg9").feature("surf10").feature("mtrl1").set("family", "rubber");
    model.result("pg9").feature("surf10").feature("mtrl1").set("color", "black");
    model.result("pg9").run();
    model.result().duplicate("pg10", "pg8");
    model.result("pg10").label("Wheel Velocity");
    model.result("pg10").feature("slc1").set("colorlegend", false);
    model.result("pg10").feature("slc2").active(false);
    model.result("pg10").create("surf10", "Surface");
    model.result("pg10").feature("surf10").set("expr", "Uwx");
    model.result("pg10").feature("surf10").create("sel1", "Selection");
    model.result("pg10").feature("surf10").feature("sel1").selection().set(3);
    model.result("pg10").feature("surf1").active(false);
    model.result("pg10").feature("surf5").active(false);
    model.result("pg10").create("surf11", "Surface");
    model.result("pg10").feature("surf11").create("sel1", "Selection");
    model.result("pg10").feature("surf11").feature("sel1").selection().named("geom1_adjsel2");
    model.result("pg10").feature("surf11").set("descr", "Velocity magnitude Tire velocity, x-component");
    model.result("pg10").feature("surf11").set("expr", "sqrt(Uwx^2+Uwz_front^2)");
    model.result("pg10").feature("surf11").set("inheritplot", "surf10");
    model.result("pg10").run();
    model.result("pg10").feature().duplicate("surf12", "surf11");
    model.result("pg10").feature("surf12").set("expr", "sqrt(Uwx^2+Uwz_rear^2)");
    model.result("pg10").feature("surf12").set("inheritplot", "surf11");
    model.result("pg10").feature("surf12").feature("sel1").selection().named("geom1_adjsel3");
    model.result("pg10").set("titletype", "manual");
    model.result("pg10").set("title", "Velocity magnitude (m/s)");
    model.result("pg10").run();

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop1").selection().named("geom1_boxsel16");

    model.component("comp1").variable("var1").set("A", "intop1(max(0,spf.nxmesh))");
    model.component("comp1").variable("var1").descr("A", "");
    model.component("comp1").variable("var1").set("Cdp", "2/(A*U0^2*1.2[kg/m^3])*intop1(p*spf.nxmesh)");
    model.component("comp1").variable("var1").descr("Cdp", "");
    model.component("comp1").variable("var1").set("Cdstress", "2/(A*U0^2*1.2[kg/m^3])*intop1(-spf.T_tracx)");
    model.component("comp1").variable("var1").descr("Cdstress", "");

    model.sol("sol3").updateSolution();

    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").set("data", "dset3");
    model.result().numerical("gev1").setIndex("looplevelinput", "manual", 0);
    model.result().numerical("gev1")
         .setIndex("looplevel", new int[]{8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58}, 0);
    model.result().numerical("gev1").setIndex("expr", "Cdp", 0);
    model.result().numerical("gev1").setIndex("unit", 1, 0);
    model.result().numerical("gev1").setIndex("descr", "", 0);
    model.result().numerical("gev1").setIndex("expr", "Cdstress", 1);
    model.result().numerical("gev1").setIndex("unit", 1, 1);
    model.result().numerical("gev1").setIndex("descr", "", 1);
    model.result().numerical("gev1").set("dataseries", "average");
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("Global Evaluation 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result().numerical().duplicate("gev2", "gev1");
    model.result().numerical("gev2").set("dataseries", "none");
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("Global Evaluation 2");
    model.result().numerical("gev2").set("table", "tbl2");
    model.result().numerical("gev2").setResult();
    model.result().create("pg11", "PlotGroup1D");
    model.result("pg11").set("data", "none");
    model.result("pg11").create("tblp1", "Table");
    model.result("pg11").feature("tblp1").set("source", "table");
    model.result("pg11").feature("tblp1").set("table", "tbl2");
    model.result("pg11").feature("tblp1").set("linewidth", "preference");
    model.result("pg11").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg11").run();
    model.result("pg11").feature("tblp1").label("Drag Coefficient");
    model.result("pg11").feature("tblp1").set("legend", true);
    model.result("pg11").set("legendpos", "lowerright");
    model.result("pg11").run();

    model.title("\u8dd1\u8f66\u7684\u5927\u6da1\u6a21\u62df");

    model
         .description("\u672c\u4f8b\u4f7f\u7528\u201c\u5927\u6da1\u6a21\u62df\u201d(LES) \u6765\u6a21\u62df\u4e00\u8f86\u4ee5\u65f6\u901f 180\u00a0km/h \u884c\u9a76\u7684\u8dd1\u8f66\u5468\u56f4\u7684\u6e4d\u6d41\u3002");

    model.label("sports_car.mph");

    model.result("pg1").run();

    model.component("comp1").cpl().create("genext1", "GeneralExtrusion");
    model.component("comp1").cpl("genext1").selection().geom("geom1", 2);
    model.component("comp1").cpl("genext1").selection().named("geom1_boxsel18");
    model.component("comp1").cpl("genext1").set("method", "closest");

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 3);
    model.component("comp2").geom("geom2").geomRep("comsol");

    model.component("comp2").mesh().create("mesh3");
    model.component("comp2").mesh("mesh3").contribute("geom/detail", true);

    model.component("comp2").geom("geom2").geomRep("cadps");
    model.component("comp2").geom("geom2").designBooleans(true);
    model.component("comp2").geom("geom2").insertFile("sports_car_fsi_geom_sequence.mph", "geom2");
    model.component("comp1").geom("geom1").run("fil11");
    model.component("comp2").geom("geom2").run("rmd1");

    model.component("comp2").material().create("mat2", "Common");
    model.component("comp2").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp2").material("mat2").label("Aluminum 3003-H18");
    model.component("comp2").material("mat2").set("family", "aluminum");
    model.component("comp2").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"2.326e7[S/m]", "0", "0", "0", "2.326e7[S/m]", "0", "0", "0", "2.326e7[S/m]"});
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"23.2e-6[1/K]", "0", "0", "0", "23.2e-6[1/K]", "0", "0", "0", "23.2e-6[1/K]"});
    model.component("comp2").material("mat2").propertyGroup("def").set("heatcapacity", "893[J/(kg*K)]");
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat2").propertyGroup("def").set("density", "2730[kg/m^3]");
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"155[W/(m*K)]", "0", "0", "0", "155[W/(m*K)]", "0", "0", "0", "155[W/(m*K)]"});
    model.component("comp2").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp2").material("mat2").propertyGroup("Enu").set("E", "69[GPa]");
    model.component("comp2").material("mat2").propertyGroup("Enu").set("nu", "0.33");
    model.component("comp2").material().create("mat3", "Common");
    model.component("comp2").material("mat3").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp2").material("mat3").label("Glass (quartz)");
    model.component("comp2").material("mat3").set("family", "custom");
    model.component("comp2").material("mat3").set("diffuse", "custom");
    model.component("comp2").material("mat3").set("ambient", "custom");
    model.component("comp2").material("mat3").set("noise", true);
    model.component("comp2").material("mat3").set("fresnel", 0.99);
    model.component("comp2").material("mat3").set("roughness", 0.02);
    model.component("comp2").material("mat3").set("diffusewrap", 0);
    model.component("comp2").material("mat3").set("reflectance", 0);
    model.component("comp2").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp2").material("mat3").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"1e-14[S/m]", "0", "0", "0", "1e-14[S/m]", "0", "0", "0", "1e-14[S/m]"});
    model.component("comp2").material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"4.2", "0", "0", "0", "4.2", "0", "0", "0", "4.2"});
    model.component("comp2").material("mat3").propertyGroup("def").set("density", "2210[kg/m^3]");
    model.component("comp2").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"1.4[W/(m*K)]", "0", "0", "0", "1.4[W/(m*K)]", "0", "0", "0", "1.4[W/(m*K)]"});
    model.component("comp2").material("mat3").propertyGroup("def").set("heatcapacity", "730[J/(kg*K)]");
    model.component("comp2").material("mat3").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp2").material("mat3").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1.5", "0", "0", "0", "1.5", "0", "0", "0", "1.5"});
    model.component("comp2").material("mat3").selection().named("geom2_extract4_bnd");

    model.component("comp1").geom("geom1").run("igv1");

    model.component("comp2").physics().create("wb", "WeakFormBoundaryPDE", "geom2");

    model.study("std1").feature("stat").setSolveFor("/physics/wb", false);
    model.study("std2").feature("stat").setSolveFor("/physics/wb", false);
    model.study("std3").feature("time").setSolveFor("/physics/wb", false);

    model.component("comp2").physics("wb").prop("EquationForm").set("form", "Automatic");
    model.component("comp2").physics("wb").feature("wfeq1").set("weak", new String[][]{{"0"}});
    model.component("comp2").physics("wb").selection().set();

    model.component("comp2").view("view59").set("renderwireframe", true);

    model.component("comp2").physics("wb").selection().named("geom2_difsel4");
    model.component("comp2").physics("wb").prop("Units").set("CustomDependentVariableUnit", "1");
    model.component("comp2").physics("wb").prop("Units").set("DependentVariableQuantity", "none");
    model.component("comp2").physics("wb").prop("Units").setIndex("CustomDependentVariableUnit", "Pa", 0, 0);
    model.component("comp2").physics("wb").prop("Units").setIndex("CustomSourceTermUnit", "Pa/m^-2", 0, 0);
    model.component("comp2").physics("wb").prop("ShapeProperty").set("order", 1);
    model.component("comp2").physics("wb").field("dimensionless").field("Tstress");
    model.component("comp2").physics("wb").field("dimensionless")
         .component(new String[]{"u3", "Tstress2", "Tstress3"});
    model.component("comp2").physics("wb").field("dimensionless").component(1, "Tstress1");
    model.component("comp2").physics("wb").feature("wfeq1")
         .setIndex("weak", "test(Tstress1)*(Tstress1-comp1.genext1(comp1.spf.T_tracx))", 0);
    model.component("comp2").physics("wb").feature("wfeq1")
         .setIndex("weak", "test(Tstress2)*(Tstress2-comp1.genext1(comp1.spf.T_tracy))", 1);
    model.component("comp2").physics("wb").feature("wfeq1")
         .setIndex("weak", "test(Tstress3)*(Tstress3-comp1.genext1(comp1.spf.T_tracz))", 2);

    model.study().create("std4");
    model.study("std4").create("time", "Transient");
    model.study("std4").feature("time").setSolveFor("/physics/spf", false);
    model.study("std4").feature("time").setSolveFor("/physics/ipf", false);
    model.study("std4").feature("time").setSolveFor("/physics/spf2", false);
    model.study("std4").feature("time").setSolveFor("/physics/wb", true);
    model.study("std4").feature("time").set("tlist", "range(0.6,0.002,0.7)");
    model.study("std4").feature("time").set("usesol", true);
    model.study("std4").feature("time").set("notsolmethod", "sol");
    model.study("std4").feature("time").set("notstudy", "std3");
    model.study("std4").feature("time").set("notsolnum", "from_list");
    model.study("std4").feature("time")
         .set("notlistsolnum", new int[]{8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58});
    model.study("std4").feature("time").setEntry("outputmap", "spf", "none");
    model.study("std4").feature("time").setEntry("outputmap", "ipf", "none");
    model.study("std4").feature("time").setEntry("outputmap", "spf2", "none");
    model.study("std4").create("tffft", "TimeToFreqFFT");
    model.study("std4").feature("tffft").set("fftstarttime", 0.6);
    model.study("std4").feature("tffft").set("fftendtime", 0.7);
    model.study("std4").feature("tffft").set("fftmaxfreq", 1400);
    model.study("std4").feature("tffft").setEntry("mesh", "geom1", "nomesh");
    model.study("std4").feature("tffft").setEntry("outputmap", "spf", "none");
    model.study("std4").feature("tffft").setEntry("outputmap", "ipf", "none");
    model.study("std4").feature("tffft").setEntry("outputmap", "spf2", "none");
    model.study("std4").showAutoSequences("all");

    model.sol("sol4").feature("t1").set("tstepsbdf", "manual");
    model.sol("sol4").feature("t1").set("timestepbdf", ".002");
    model.sol("sol4").feature("t1").set("tout", "tsteps");
    model.sol("sol4").feature("t1").feature("fc1").set("jtech", "once");
    model.sol("sol4").feature("t1").feature("fc1").set("ntermconst", "iter");

    model.component("comp2").cpl().create("intop2", "Integration");
    model.component("comp2").cpl("intop2").set("axisym", true);
    model.component("comp2").cpl("intop2").selection().geom("geom2", 2);
    model.component("comp2").cpl("intop2").selection().named("geom2_extract4_bnd");
    model.component("comp2").cpl().create("intop3", "Integration");
    model.component("comp2").cpl("intop3").set("axisym", true);
    model.component("comp2").cpl("intop3").selection().geom("geom2", 2);
    model.component("comp2").cpl("intop3").selection().named("geom2_extract1_bnd");

    model.component("comp2").variable().create("var2");
    model.component("comp2").variable("var2").set("T0s", "0.1");
    model.component("comp2").variable("var2").descr("T0s", "\u65e0\u91cf\u7eb2\u5468\u671f");
    model.component("comp2").variable("var2")
         .set("F_window", "sqrt(intop2(Tstress1)*intop2(conj(Tstress1))+intop2(Tstress2)*intop2(conj(Tstress2))+intop2(Tstress3)*intop2(conj(Tstress3)))/T0s");
    model.component("comp2").variable("var2").descr("F_window", "\u8f66\u7a97\u529b\u7684\u6a21");
    model.component("comp2").variable("var2")
         .set("F_mirror", "sqrt(intop3(Tstress1)*intop3(conj(Tstress1))+intop3(Tstress2)*intop3(conj(Tstress2))+intop3(Tstress3)*intop3(conj(Tstress3)))/T0s");
    model.component("comp2").variable("var2").descr("F_mirror", "\u540e\u89c6\u955c\u529b\u7684\u6a21");

    model.component("comp2").mesh("mesh3").automatic(false);
    model.component("comp2").mesh("mesh3").feature("size").set("table", "cfd");
    model.component("comp2").mesh("mesh3").feature("size").set("hauto", 4);
    model.component("comp2").mesh("mesh3").feature("ftri1").selection().geom("geom2");
    model.component("comp2").mesh("mesh3").run();

    model.study("std4").createAutoSequences("all");

    model.sol("sol4").runAll();

    model.result().create("pg12", "PlotGroup3D");
    model.result("pg12").set("data", "dset5");
    model.result("pg12").setIndex("looplevel", 141, 0);
    model.result("pg12").create("surf1", "Surface");
    model.result("pg12").label("\u5f31\u5f62\u5f0f\u8fb9\u754c\u504f\u5fae\u5206\u65b9\u7a0b");
    model.result("pg12").feature("surf1").set("expr", "Tstress1");
    model.result().create("pg13", "PlotGroup1D");
    model.result("pg13").set("data", "dset5");
    model.result("pg13").create("glob1", "Global");
    model.result("pg13").feature("glob1").set("markerpos", "datapoints");
    model.result("pg13").feature("glob1").set("linewidth", "preference");
    model.result("pg13").feature("glob1").set("expr", new String[]{"F_mirror"});
    model.result("pg13").feature("glob1").set("descr", new String[]{"\u540e\u89c6\u955c\u529b\u7684\u6a21"});
    model.result("pg13").feature("glob1").set("expr", new String[]{"F_mirror", "F_window"});
    model.result("pg13").feature("glob1")
         .set("descr", new String[]{"\u540e\u89c6\u955c\u529b\u7684\u6a21", "\u8f66\u7a97\u529b\u7684\u6a21"});
    model.result("pg13").run();
    model.result("pg13").setIndex("looplevelinput", "manual", 0);
    model.result("pg13")
         .setIndex("looplevel", new int[]{2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140}, 0);
    model.result("pg13").set("ylabelactive", true);
    model.result("pg13").set("ylabel", "\u603b\u529b (N)");
    model.result("pg13").run();
    model.result("pg13").set("xlog", true);
    model.result("pg13").label("\u8f66\u7a97\u548c\u540e\u89c6\u955c\u4e0a\u7684\u529b");

    model.sol("sol3").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol1").clearSolutionData();

    model.result("pg12").setIndex("looplevel", 7, 0);
    model.result("pg12").run();

    model.component("comp2").physics().create("shell", "Shell", "geom2");

    model.study("std1").feature("stat").setSolveFor("/physics/shell", false);
    model.study("std2").feature("stat").setSolveFor("/physics/shell", false);
    model.study("std3").feature("time").setSolveFor("/physics/shell", false);
    model.study("std4").feature("time").setSolveFor("/physics/shell", false);
    model.study("std4").feature("tffft").setSolveFor("/physics/shell", false);

    model.component("comp2").material("mat3").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp2").material("mat3").propertyGroup("Enu").set("E", new String[]{"7e10"});
    model.component("comp2").material("mat3").propertyGroup("Enu").set("nu", new String[]{"0.23"});

    model.component("comp2").physics("shell").feature("emm1").create("dmp1", "Damping", 2);
    model.component("comp2").physics("shell").feature("emm1").feature("dmp1")
         .set("DampingType", "IsotropicLossFactor");
    model.component("comp2").physics("shell").feature("emm1").feature("dmp1").set("eta_s_mat", "userdef");
    model.component("comp2").physics("shell").feature("emm1").feature("dmp1").set("eta_s", 0.03);
    model.component("comp2").physics("shell").feature("to1").set("d", "1.2[mm]");
    model.component("comp2").physics("shell").create("to2", "ThicknessOffset", 2);
    model.component("comp2").physics("shell").feature("to2").selection().named("geom2_extract4_bnd");
    model.component("comp2").physics("shell").feature("to2").set("d", "3[mm]");
    model.component("comp2").physics("shell").create("to3", "ThicknessOffset", 2);
    model.component("comp2").physics("shell").feature("to3").selection().named("geom2_unisel1");
    model.component("comp2").physics("shell").feature("to3").set("d", "2.4[mm]");
    model.component("comp2").physics("shell").create("to4", "ThicknessOffset", 2);
    model.component("comp2").physics("shell").feature("to4").selection().named("geom2_extract11_bnd");
    model.component("comp2").physics("shell").feature("to4").set("d", "1.8[mm]");
    model.component("comp2").physics("shell").create("fix1", "Fixed", 1);
    model.component("comp2").physics("shell").feature("fix1").selection().named("geom2_csel1_edg");
    model.component("comp2").physics("shell").create("spf1", "SpringFoundation2", 2);
    model.component("comp2").physics("shell").feature("spf1").selection().named("geom2_intsel2");
    model.component("comp2").physics("shell").feature("spf1")
         .set("kPerArea", new String[]{"2e9", "0", "0", "0", "2e9", "0", "0", "0", "2e9"});
    model.component("comp2").physics("shell").create("spf2", "SpringFoundation1", 1);
    model.component("comp2").physics("shell").feature("spf2").selection().set(1);
    model.component("comp2").physics("shell").feature("spf2")
         .set("kPerLine", new String[]{"2e7", "0", "0", "0", "2e7", "0", "0", "0", "2e7"});
    model.component("comp2").physics("shell").create("fl1", "FaceLoad", 2);
    model.component("comp2").physics("shell").feature("fl1").selection().named("geom2_difsel4");
    model.component("comp2").physics("shell").feature("fl1")
         .set("forceReferenceArea", new String[]{"-withsol('sol4',comp2.Tstress1,setval(freq,freq))/T0s", "-withsol('sol4',comp2.Tstress2,setval(freq,freq))/T0s", "-withsol('sol4',comp2.Tstress3,setval(freq,freq))/T0s"});
    model.component("comp2").physics("shell").feature("fl1").set("harmonicPerturbation", true);

    model.study().create("std5");
    model.study("std5").create("eig", "Eigenfrequency");
    model.study("std5").feature("eig").set("plotgroup", "Default");
    model.study("std5").feature("eig").set("storefact", false);
    model.study("std5").feature("eig").set("outputmap", new String[]{});
    model.study("std5").feature("eig").set("filtereigdescription", new String[]{"Damped natural frequency"});
    model.study("std5").feature("eig").set("ngenAUX", "1");
    model.study("std5").feature("eig").set("goalngenAUX", "1");
    model.study("std5").feature("eig").set("ngenAUX", "1");
    model.study("std5").feature("eig").set("goalngenAUX", "1");
    model.study("std5").feature("eig").setSolveFor("/physics/spf", false);
    model.study("std5").feature("eig").setSolveFor("/physics/ipf", false);
    model.study("std5").feature("eig").setSolveFor("/physics/spf2", false);
    model.study("std5").feature("eig").setSolveFor("/physics/wb", false);
    model.study("std5").feature("eig").setSolveFor("/physics/shell", true);
    model.study("std5").create("frmod", "Frequencymodal");
    model.study("std5").feature("frmod").set("outputmap", new String[]{});
    model.study("std5").feature("frmod").setSolveFor("/physics/spf", false);
    model.study("std5").feature("frmod").setSolveFor("/physics/ipf", false);
    model.study("std5").feature("frmod").setSolveFor("/physics/spf2", false);
    model.study("std5").feature("frmod").setSolveFor("/physics/wb", false);
    model.study("std5").feature("frmod").setSolveFor("/physics/shell", true);
    model.study("std5").feature("eig").set("neigsactive", true);
    model.study("std5").feature("eig").set("neigs", 50);
    model.study("std5").feature("eig").set("useadvanceddisable", true);
    model.study("std5").feature("eig").set("disabledphysics", new String[]{"shell/emm1/dmp1"});
    model.study("std5").feature("eig").setEntry("outputmap", "spf", "none");
    model.study("std5").feature("eig").setEntry("outputmap", "ipf", "none");
    model.study("std5").feature("frmod").set("plist", "range(10,10,1390)");
    model.study("std5").feature("frmod").setEntry("mesh", "geom1", "nomesh");

    return model;
  }

  public static Model run3(Model model) {
    model.study("std5").feature("frmod").setEntry("outputmap", "spf", "none");
    model.study("std5").feature("frmod").setEntry("outputmap", "ipf", "none");
    model.study("std5").showAutoSequences("all");
    model.study("std5").feature("eig").setEntry("mesh", "geom1", "nomesh");
    model.study("std5").createAutoSequences("all");

    model.sol("sol6").runAll();

    model.result().dataset().create("dset9shellshl", "Shell");
    model.result().dataset("dset9shellshl").set("data", "dset9");
    model.result().dataset("dset9shellshl").setIndex("topconst", "1", 3, 1);
    model.result().dataset("dset9shellshl").setIndex("bottomconst", "-1", 3, 1);
    model.result().dataset("dset9shellshl").setIndex("orientationexpr", "shell.nlX", 0);
    model.result().dataset("dset9shellshl").setIndex("displacementexpr", "arx", 0);
    model.result().dataset("dset9shellshl").setIndex("orientationexpr", "shell.nlY", 1);
    model.result().dataset("dset9shellshl").setIndex("displacementexpr", "ary", 1);
    model.result().dataset("dset9shellshl").setIndex("orientationexpr", "shell.nlZ", 2);
    model.result().dataset("dset9shellshl").setIndex("displacementexpr", "arz", 2);
    model.result().dataset("dset9shellshl").set("distanceexpr", "shell.z_pos");
    model.result().dataset("dset9shellshl").set("seplevels", false);
    model.result().dataset("dset9shellshl").set("resolution", 2);
    model.result().dataset("dset9shellshl").set("areascalefactor", "shell.ASF");
    model.result().dataset("dset9shellshl").set("linescalefactor", "shell.LSF");
    model.result().create("pg14", "PlotGroup3D");
    model.result("pg14").set("data", "dset9shellshl");
    model.result("pg14").setIndex("looplevel", 139, 0);
    model.result("pg14").label("\u5e94\u529b (shell)");
    model.result("pg14").set("showlegends", true);
    model.result("pg14").set("frametype", "spatial");
    model.result("pg14").create("surf1", "Surface");
    model.result("pg14").feature("surf1").set("expr", new String[]{"shell.misesGp_peak"});
    model.result("pg14").feature("surf1").set("threshold", "manual");
    model.result("pg14").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg14").feature("surf1").set("colortable", "Rainbow");
    model.result("pg14").feature("surf1").set("colortabletrans", "none");
    model.result("pg14").feature("surf1").set("colorscalemode", "linear");
    model.result("pg14").feature("surf1").set("descr", "Von Mises \u5e94\u529b\uff0c\u5cf0\u503c");
    model.result("pg14").feature("surf1").set("colortable", "Prism");
    model.result("pg14").feature("surf1").create("def", "Deform");
    model.result("pg14").feature("surf1").feature("def").set("expr", new String[]{"shell.u", "shell.v", "shell.w"});
    model.result("pg14").run();
    model.result("pg14").setIndex("looplevel", 5, 0);
    model.result("pg14").run();
    model.result().create("pg15", "PlotGroup3D");
    model.result("pg15").label("\u4f4d\u79fb (shell)");
    model.result("pg15").set("showlegends", true);
    model.result("pg15").set("frametype", "spatial");
    model.result("pg15").create("surf1", "Surface");
    model.result("pg15").feature("surf1").set("expr", new String[]{"shell.disp"});
    model.result("pg15").feature("surf1").set("threshold", "manual");
    model.result("pg15").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg15").feature("surf1").set("colortable", "SpectrumLight");
    model.result("pg15").feature("surf1").set("colortabletrans", "none");
    model.result("pg15").feature("surf1").set("colorscalemode", "linear");
    model.result("pg15").feature("surf1").set("descr", "\u4f4d\u79fb\u5927\u5c0f");
    model.result("pg15").feature("surf1").create("def", "Deform");
    model.result("pg15").feature("surf1").feature("def").set("expr", new String[]{"shell.u", "shell.v", "shell.w"});
    model.result("pg15").set("data", "dset9shellshl");
    model.result("pg15").label("\u4f4d\u79fb (shell)");
    model.result("pg15").setIndex("looplevel", 5, 0);
    model.result("pg15").run();
    model.result("pg15").setIndex("looplevel", 7, 0);
    model.result("pg15").run();
    model.result().create("pg16", "PlotGroup3D");
    model.result("pg16").set("data", "dset9");
    model.result("pg16").setIndex("looplevel", 139, 0);
    model.result("pg16").label("\u9762\u8f7d\u8377 (shell)");
    model.result("pg16").set("showlegends", true);
    model.result("pg16").set("titletype", "label");
    model.result("pg16").set("frametype", "spatial");
    model.result("pg16").set("showlegendsunit", true);
    model.result("pg16").create("surf1", "Surface");
    model.result("pg16").feature("surf1").set("expr", new String[]{"1"});
    model.result("pg16").feature("surf1").label("\u7070\u8272\u8868\u9762");
    model.result("pg16").feature("surf1").set("coloring", "uniform");
    model.result("pg16").feature("surf1").set("color", "gray");
    model.result("pg16").feature("surf1").set("inheritcolor", false);
    model.result("pg16").feature("surf1").set("inheritrange", false);
    model.result("pg16").feature("surf1").set("inherittransparency", false);
    model.result("pg16").feature("surf1").create("def", "Deform");
    model.result("pg16").feature("surf1").feature("def").set("expr", new String[]{"u3", "v3", "w3"});
    model.result("pg16").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg16").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg16").feature("surf1").feature("def").set("scale", 0);
    model.result("pg16").feature("surf1").create("sel1", "Selection");
    model.result("pg16").feature("surf1").feature("sel1").selection().geom("geom2", 2);
    model.result("pg16").feature("surf1").feature("sel1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100);
    model.result("pg16").feature("surf1").create("tran1", "Transparency");
    model.result("pg16").feature("surf1").feature("tran1").set("transparency", 0.8);
    model.result("pg16").create("arws1", "ArrowSurface");
    model.result("pg16").feature("arws1")
         .set("expr", new String[]{"shell.fl1.fax", "shell.fl1.fay", "shell.fl1.faz"});
    model.result("pg16").feature("arws1").set("placement", "gausspoints");
    model.result("pg16").feature("arws1").set("arrowbase", "tail");
    model.result("pg16").feature("arws1").label("\u9762\u8f7d\u8377 1");
    model.result("pg16").feature("arws1").set("inheritplot", "none");
    model.result("pg16").feature("arws1").create("col", "Color");
    model.result("pg16").feature("arws1").feature("col").set("colortable", "Rainbow");
    model.result("pg16").feature("arws1").feature("col").set("colortabletrans", "none");
    model.result("pg16").feature("arws1").feature("col").set("colorscalemode", "linear");
    model.result("pg16").feature("arws1").feature("col").set("colordata", "arrowlength");
    model.result("pg16").feature("arws1").feature("col").set("coloring", "gradient");
    model.result("pg16").feature("arws1").feature("col").set("topcolor", "red");
    model.result("pg16").feature("arws1").feature("col").set("bottomcolor", "custom");
    model.result("pg16").feature("arws1").feature("col")
         .set("custombottomcolor", new double[]{0.5882353186607361, 0.5137255191802979, 0.5176470875740051});
    model.result("pg16").feature("arws1").set("color", "red");
    model.result("pg16").feature("arws1").create("def", "Deform");
    model.result("pg16").feature("arws1").feature("def").set("expr", new String[]{"u3", "v3", "w3"});
    model.result("pg16").feature("arws1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg16").feature("arws1").feature("def").set("scaleactive", true);
    model.result("pg16").feature("arws1").feature("def").set("scale", 0);
    model.result("pg16").feature().move("surf1", 1);
    model.result("pg16").label("\u9762\u8f7d\u8377 (shell)");
    model.result("pg16").setIndex("looplevel", 5, 0);
    model.result("pg16").run();

    model.title("\u8dd1\u8f66\u8f66\u95e8\u4e0a\u7684\u6d41-\u56fa\u8026\u5408");

    model
         .description("\u672c\u4f8b\u5bf9\u201c\u8dd1\u8f66\u7684\u5927\u6da1\u6a21\u62df\u201d\u6a21\u578b\u8fdb\u884c\u6269\u5c55\uff0c\u4ee5\u4fbf\u5bf9\u5e26\u6709\u8f66\u7a97\u548c\u540e\u89c6\u955c\u7684\u8f66\u95e8\u8fdb\u884c\u632f\u52a8\u5206\u6790\u3002\u6d41\u4f53\u4e0e\u8f66\u8eab\u76f8\u4e92\u4f5c\u7528\u4ea7\u751f\u7684\u529b\u88ab\u62c9\u4f38\u8026\u5408\u5230\u8f66\u95e8\u51e0\u4f55\u4e0a\uff0c\u5e76\u901a\u8fc7 FFT \u8f6c\u6362\u5230\u9891\u57df\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();

    model.label("sports_car_fsi.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
