/*
 * wave_based_room.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:26 by COMSOL 6.3.0.290. */
public class wave_based_room {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Building_and_Room_Acoustics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("pate", "PressureAcousticsTimeExplicit", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/pate", true);

    model.component("comp1").geom("geom1").geomRep("cadps");
    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "wave_based_room.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "1.2[m] 0.2[m] -0.8[m] -1.8[m]", 0);
    model.component("comp1").geom("geom1").feature("pt1")
         .setIndex("p", "0.75*1.75[m] 0.5*1.75[m] 0.25*1.75[m] 0[m]", 1);
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "1[m] 1[m] 1[m] 1[m]", 2);
    model.component("comp1").geom("geom1").run("pt1");

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1")
         .set(10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 25, 26, 27, 28, 29, 30, 31, 51, 52, 61, 68, 69, 70, 71);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").set(21, 22, 23, 24, 32, 33, 34, 35, 53, 54, 55, 56);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").geom(2);
    model.component("comp1").selection("sel3")
         .set(116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 265, 266, 267, 268, 269, 270, 272, 273, 274, 277, 278, 279, 281, 282, 283, 284, 285, 286, 287, 288, 289);
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").geom(2);
    model.component("comp1").selection("sel4")
         .set(241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255, 256, 257, 258, 259, 260, 261);
    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").geom(2);
    model.component("comp1").selection("sel5")
         .set(79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 233, 234, 235, 236, 237, 238, 239, 240, 263, 264, 271, 275, 276, 280);
    model.component("comp1").selection().create("sel6", "Explicit");
    model.component("comp1").selection("sel6").geom(2);
    model.component("comp1").selection("sel6")
         .set(36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 57, 58, 59, 60, 62, 63, 64, 65, 66, 67, 72);
    model.component("comp1").selection().create("sel7", "Explicit");
    model.component("comp1").selection("sel7").geom(2);
    model.component("comp1").selection("sel7").set(207, 208, 209, 210, 211, 212, 225, 226, 227, 228);
    model.component("comp1").selection().create("sel8", "Explicit");
    model.component("comp1").selection("sel8").geom(2);
    model.component("comp1").selection("sel8").set(7, 77);
    model.component("comp1").selection().create("sel9", "Explicit");
    model.component("comp1").selection("sel9").geom(2);
    model.component("comp1").selection("sel9").set(3, 75);
    model.component("comp1").selection().create("sel10", "Explicit");
    model.component("comp1").selection("sel10").geom(2);
    model.component("comp1").selection("sel10").set(1, 2, 4, 5, 8, 9, 74, 78, 262);
    model.component("comp1").selection().create("sel11", "Explicit");
    model.component("comp1").selection("sel11").geom(2);
    model.component("comp1").selection("sel11").all();
    model.component("comp1").selection().create("sel12", "Explicit");
    model.component("comp1").selection("sel12").geom(0);
    model.component("comp1").selection("sel12").set(35, 53, 121, 122);

    model.component("comp1").view("view1").set("renderwireframe", false);
    model.component("comp1").view("view1").hideObjects().create("hide1");
    model.component("comp1").view("view1").hideObjects("hide1").init(2);
    model.component("comp1").view("view1").hideObjects("hide1").add("imp1", 77);
    model.component("comp1").view("view1").hideObjects("hide1").add("imp1", 7);
    model.component("comp1").view("view1").hideObjects("hide1").add("imp1", 5);
    model.component("comp1").view("view1").hideObjects("hide1").add("imp1", 74);
    model.component("comp1").view("view1").hideObjects("hide1").add("imp1", 76);
    model.component("comp1").view("view1").hideObjects("hide1").add("imp1", 4);
    model.component("comp1").view("view1").hideObjects("hide1").add("imp1", 1);
    model.component("comp1").view("view1").hideObjects("hide1").add("imp1", 2);
    model.component("comp1").view("view1").hideObjects("hide1").add("imp1", 6);
    model.component("comp1").view("view1").hideObjects("hide1").add("imp1", 73);
    model.component("comp1").view("view1").hideObjects().clear();
    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").probe().create("point1", "Point");
    model.component("comp1").probe("point1").selection().set(122);
    model.component("comp1").probe("point1").set("expr", "pate.p_t/(1[m/s]*pate.Z)");
    model.component("comp1").probe("point1").set("descractive", true);
    model.component("comp1").probe().duplicate("point2", "point1");
    model.component("comp1").probe("point2").selection().set(121);
    model.component("comp1").probe().duplicate("point3", "point2");
    model.component("comp1").probe("point3").selection().set(53);
    model.component("comp1").probe().duplicate("point4", "point3");
    model.component("comp1").probe("point4").selection().set(35);

    model.param().set("f0", "700[Hz]");
    model.param().descr("f0", "Signal center frequency");
    model.param().set("T0", "1/f0");
    model.param().descr("T0", "Signal period at center frequency");
    model.param().set("c0", "343[m/s]");
    model.param().descr("c0", "Speed of sound");
    model.param().set("lam0", "c0/f0");
    model.param().descr("lam0", "Signal wavelength at center frequency");

    model.func().create("an1", "Analytic");
    model.func("an1").set("funcname", "vn");
    model.func("an1").set("args", "t");
    model.func("an1").set("expr", "exp(-(t - 2*T0)^2/(T0^2/2))*sin(2*pi*f0*t)");
    model.func("an1").set("fununit", "m/s");
    model.func("an1").setIndex("argunit", "s", 0);
    model.func("an1").setIndex("plotargs", "5*T0", 0, 2);
    model.func("an1").createPlot("pg1");

    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("plot1").set("display", "fourier");
    model.result("pg1").feature("plot1").set("fouriershow", "spectrum");
    model.result("pg1").feature("plot1").set("scale", "multiplyperiod");
    model.result("pg1").feature("plot1").set("freqrangeactive", true);
    model.result("pg1").feature("plot1").set("freqmax", "3*f0");
    model.result("pg1").run();

    model.func().create("pff1", "PartialFraction");
    model.func("pff1").set("filename", "wave_based_room_admittance_carpet.txt");
    model.func("pff1").set("tol", "1e-5");
    model.func("pff1").run();
    model.func("pff1").createPlot("pg2");

    model.result("pg2").run();

    model.func().duplicate("pff2", "pff1");
    model.func("pff2").set("tol", "1e-3");
    model.func("pff2").run();
    model.func("pff2").createPlot("pg3");

    model.result("pg3").run();
    model.result("pg3").set("axislimits", true);
    model.result("pg3").set("xmax", 230);
    model.result("pg3").set("ymin", "-5e-7");
    model.result("pg3").set("ymax", "5e-7");
    model.result("pg3").run();
    model.result("pg3").feature().remove("tblp2");
    model.result("pg3").run();
    model.result("pg3").feature("plot1").setIndex("legends", "tol = 1e-3", 0);
    model.result("pg3").run();
    model.result("pg3").feature("plot2").set("data", "pff1_ds1");
    model.result("pg3").feature("plot2").set("expr", "real(pff1(x[1/m][Hz]))");
    model.result("pg3").feature("plot2").set("linestyle", "dotted");
    model.result("pg3").feature("plot2").set("linecolor", "cyclereset");
    model.result("pg3").feature("plot2").set("linewidth", 3);
    model.result("pg3").feature("plot2").setIndex("legends", "tol = 1e-5", 0);
    model.result("pg3").run();
    model.result().dataset().remove("pff1_ds1");
    model.result().dataset().remove("pff2_ds1");

    model.nodeGroup().remove("grp2");
    model.nodeGroup().remove("grp1");

    model.func().remove("pff2");
    model.func().create("pff2", "PartialFraction");
    model.func("pff2").set("filename", "wave_based_room_admittance_ceiling.txt");
    model.func("pff2").run();
    model.func("pff2")
         .set("realpoles", new String[]{"-807.1324471705102", "-199.24356931073874", "-39.22367790766698"});
    model.func("pff2").set("complexpoles", new String[]{"-1239.0011202983076+3462.56975554916*i"});
    model.func("pff2")
         .set("realresidues", new String[]{"-0.0623119429652287", "-0.004573632483144791", "2.6956861359732913E-6"});
    model.func("pff2").set("complexresidues", new String[]{"11.180750600761762+7.470150376516617*i"});
    model.func("pff2").set("asymterm", "9.890037942686073E-4-7.46187179100124E-8*i");
    model.func().create("pff3", "PartialFraction");
    model.func("pff3").set("filename", "wave_based_room_admittance_sofa.txt");
    model.func("pff3").run();
    model.func("pff3").set("realresidues", new String[]{});
    model.func("pff3").set("realpoles", new String[]{});
    model.func("pff3").set("realresidues", new String[]{});
    model.func("pff3").set("complexresidues", new String[]{"6.466184458276553+4.467272092659058*i"});
    model.func("pff3").set("asymterm", "1.8342057431599337E-4+4.871186193932436E-5*i");
    model.func("pff3").set("tol", "1e-5");
    model.func("pff3").run();
    model.func("pff3").set("realresidues", new String[]{});
    model.func("pff3").set("realpoles", new String[]{});
    model.func("pff3").set("realresidues", new String[]{});
    model.func("pff3")
         .set("complexresidues", new String[]{"6.435639310794105+10.694705749260615*i", "7.3114317724965865+3.445797068600098*i"});
    model.func("pff3").set("asymterm", "9.119280184189337E-4+3.0023368219263197E-7*i");
    model.func().create("pff4", "PartialFraction");
    model.func("pff4").set("filename", "wave_based_room_admittance_wall.txt");
    model.func("pff4").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("rho", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("cs", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat1").propertyGroup().create("RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat1").propertyGroup().create("NonlinearModel", "Nonlinear model");
    model.component("comp1").material("mat1").propertyGroup().create("idealGas", "Ideal gas");
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
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("molarmass", "0.02897");
    model.component("comp1").material("mat1").propertyGroup("idealGas").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("idealGas").addInput("pressure");
    model.component("comp1").material("mat1").materialType("nonSolid");

    model.component("comp1").physics("pate").create("nvel1", "NormalVelocity", 2);
    model.component("comp1").physics("pate").feature("nvel1").selection().set(222);
    model.component("comp1").physics("pate").feature("nvel1").set("nvel", "vn(t)");
    model.component("comp1").physics("pate").create("imp1", "Impedance", 2);
    model.component("comp1").physics("pate").feature("imp1").selection().named("sel9");
    model.component("comp1").physics("pate").feature("imp1").set("ImpedanceModel", "RationalApproximation");
    model.component("comp1").physics("pate").feature("imp1").set("ApproximantSource", "Function");
    model.component("comp1").physics("pate").feature("imp1").set("ApproximantFunctionReference", "pff1");
    model.component("comp1").physics("pate").feature("imp1").set("Y_inf", "0.0015195024746589788");
    model.component("comp1").physics("pate").feature("imp1")
         .set("R", new String[]{"0.026677952421701165", "5.050828661456189E-4", "7.784297567009928E-6"});
    model.component("comp1").physics("pate").feature("imp1")
         .set("xi", new String[]{"-1805.0048606782857", "-598.5400825726435", "-168.52889907841535"});
    model.component("comp1").physics("pate").feature("imp1")
         .set("Q", new String[]{"13.53190598769591+18.444782643470383*i"});
    model.component("comp1").physics("pate").feature("imp1")
         .set("zeta", new String[]{"-2956.2143629068196+7143.975985151538*i"});
    model.component("comp1").physics("pate").feature().duplicate("imp2", "imp1");
    model.component("comp1").physics("pate").feature("imp2").selection().named("sel8");
    model.component("comp1").physics("pate").feature("imp2").set("ApproximantFunctionReference", "pff2");
    model.component("comp1").physics("pate").feature("imp2").set("Y_inf", "9.890037942686073E-4");
    model.component("comp1").physics("pate").feature("imp2")
         .set("R", new String[]{"-0.0623119429652287", "-0.004573632483144791", "2.6956861359732913E-6"});
    model.component("comp1").physics("pate").feature("imp2")
         .set("xi", new String[]{"-807.1324471705102", "-199.24356931073874", "-39.22367790766698"});
    model.component("comp1").physics("pate").feature("imp2")
         .set("Q", new String[]{"11.180750600761762+7.470150376516617*i"});
    model.component("comp1").physics("pate").feature("imp2")
         .set("zeta", new String[]{"-1239.0011202983076+3462.56975554916*i"});
    model.component("comp1").physics("pate").feature().duplicate("imp3", "imp2");
    model.component("comp1").physics("pate").feature("imp3").selection().named("sel1");
    model.component("comp1").physics("pate").feature("imp3").set("ApproximantFunctionReference", "pff3");
    model.component("comp1").physics("pate").feature("imp3").set("Y_inf", "9.119280184189337E-4");
    model.component("comp1").physics("pate").feature("imp3").set("R", new String[]{});
    model.component("comp1").physics("pate").feature("imp3").set("xi", new String[]{});
    model.component("comp1").physics("pate").feature("imp3")
         .set("Q", new String[]{"6.435639310794105+10.694705749260615*i", "7.3114317724965865+3.445797068600098*i"});
    model.component("comp1").physics("pate").feature("imp3")
         .set("zeta", new String[]{"-1644.5862490290372+6128.39249680556*i", "-1141.474635860429+1816.6938934791929*i"});
    model.component("comp1").physics("pate").feature().duplicate("imp4", "imp3");
    model.component("comp1").physics("pate").feature("imp4").selection().named("sel10");
    model.component("comp1").physics("pate").feature("imp4").set("ApproximantFunctionReference", "pff4");
    model.component("comp1").physics("pate").feature("imp4").set("Y_inf", "6.674405213082241E-4");
    model.component("comp1").physics("pate").feature("imp4").set("R", new String[]{"-0.09937417551168455"});
    model.component("comp1").physics("pate").feature("imp4").set("xi", new String[]{"-281.48818345618673"});
    model.component("comp1").physics("pate").feature("imp4")
         .set("Q", new String[]{"-1.720537564079464+2.846863638240308*i"});
    model.component("comp1").physics("pate").feature("imp4")
         .set("zeta", new String[]{"-3222.506530349331+9794.831933382658*i"});

    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().set(2);
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().named("sel1");
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet1").set("optlevel", "high");
    model.component("comp1").mesh("mesh1").feature("ftet1").set("optsmall", true);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "lam0/3");
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", 0.04);
    model.component("comp1").mesh("mesh1").feature("size").set("hcurve", 0.3);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "range(0,T0,30*T0)");
    model.study("std1").feature("time").setEntry("outputmap", "pate", "selection");
    model.study("std1").feature("time").setEntry("outputselectionmap", "pate", "sel11");
    model.study("std1").setGenPlots(false);

    model.sol().create("sol1");
    model.sol("sol1").study("std1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "time");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").set("control", "time");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("t1").set("odesolvertype", "explicit");
    model.sol("sol1").feature("t1").set("timemethodexp", "erk");
    model.sol("sol1").feature("t1").set("tlist", "range(0,T0,30*T0)");
    model.sol("sol1").feature("t1").set("plot", "off");
    model.sol("sol1").feature("t1").set("plotgroup", "pg1");
    model.sol("sol1").feature("t1").set("plotfreq", "tout");
    model.sol("sol1").feature("t1").set("probesel", "all");
    model.sol("sol1").feature("t1").set("probes", new String[]{"point1", "point2", "point3", "point4"});
    model.sol("sol1").feature("t1").set("probefreq", "tsteps");
    model.sol("sol1").feature("t1").set("exprs", new String[]{"root.comp1.pate.wtc"});
    model.sol("sol1").feature("t1").set("tstepping", "elemexprs");
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("t1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");

    model.component("comp1").probe("point1").genResult("none");
    model.component("comp1").probe("point2").genResult("none");
    model.component("comp1").probe("point3").genResult("none");
    model.component("comp1").probe("point4").genResult("none");

    model.sol("sol1").runFromTo("st1", "v1");

    model.result().table("tbl1").set("tablebuffersize", 20000);

    model.sol("sol1").feature("t1").feature("dDef").set("linsolver", "pardiso");

    model.component("comp1").probe("point1").genResult("none");
    model.component("comp1").probe("point2").genResult("none");
    model.component("comp1").probe("point3").genResult("none");
    model.component("comp1").probe("point4").genResult("none");

    model.sol("sol1").runAll();

    model.result("pg2").set("window", "window2");
    model.result("pg2").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg2").run();
    model.result("pg2").set("titletype", "label");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("window", "window2");
    model.result("pg2").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg2").run();
    model.result("pg2").feature("tblp1").set("legendmethod", "manual");
    model.result("pg2").feature("tblp1").setIndex("legends", "LP1", 0);
    model.result("pg2").feature("tblp1").setIndex("legends", "LP2", 1);
    model.result("pg2").feature("tblp1").setIndex("legends", "LP3", 2);
    model.result("pg2").feature("tblp1").setIndex("legends", "LP4", 3);
    model.result("pg2").set("window", "window2");
    model.result("pg2").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 12, 0);
    model.result("pg3").set("titletype", "label");
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "pate.p_t/(1[m/s]*pate.Z)");
    model.result("pg3").feature("surf1").set("colortable", "Wave");
    model.result("pg3").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg3").feature("surf1").create("sel1", "Selection");
    model.result("pg3").feature("surf1").feature("sel1").selection()
         .set(3, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 75, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255, 256, 257, 258, 259, 260, 261, 262, 263, 264, 265, 266, 267, 268, 269, 270, 271, 272, 273, 274, 275, 276, 277, 278, 279, 280, 281, 282, 283, 284, 285, 286, 287, 288, 289);

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 5, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 19, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 26, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 12, 0);
    model.result("pg3").run();

    model.func("pff1").importData();
    model.func("pff2").importData();
    model.func("pff3").importData();
    model.func("pff4").importData();

    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").set("edges", false);
    model.result("pg4").set("showlegends", false);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature("surf1").feature("sel1").selection()
         .set(8, 9, 78, 217, 218, 219, 220, 222, 224, 230);
    model.result("pg4").run();
    model.result("pg4").feature().duplicate("surf2", "surf1");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature("surf2").feature("sel1").selection().set(6);
    model.result("pg4").run();
    model.result("pg4").feature("surf2").create("tran1", "Transparency");
    model.result("pg4").run();
    model.result("pg4").feature("surf2").feature("tran1").set("uniformblending", 0.5);
    model.result("pg4").feature("surf2").feature("tran1").set("transparency", 0.15);
    model.result("pg4").run();
    model.result("pg4").feature("surf2").create("def1", "Deform");
    model.result("pg4").run();
    model.result("pg4").feature("surf2").feature("def1")
         .set("expr", new String[]{"0", "0", "pate.p_t/(1[m/s]*pate.Z)"});
    model.result("pg4").run();
    model.result("pg4").create("surf3", "Surface");
    model.result("pg4").feature("surf3").set("expr", "1");
    model.result("pg4").feature("surf3").set("coloring", "uniform");
    model.result("pg4").feature("surf3").set("color", "custom");
    model.result("pg4").feature("surf3").set("customcolor", new double[]{0, 0.250980406999588, 0.250980406999588});
    model.result("pg4").feature("surf3").create("mtrl1", "MaterialAppearance");
    model.result("pg4").run();
    model.result("pg4").feature("surf3").feature("mtrl1").set("appearance", "custom");
    model.result("pg4").feature("surf3").feature("mtrl1").set("family", "textile");
    model.result("pg4").feature("surf3").feature("mtrl1").set("useplotcolors", true);
    model.result("pg4").run();
    model.result("pg4").feature("surf3").create("sel1", "Selection");
    model.result("pg4").feature("surf3").feature("sel1").selection().named("sel1");
    model.result("pg4").run();
    model.result("pg4").feature().duplicate("surf4", "surf3");
    model.result("pg4").run();
    model.result("pg4").feature("surf4")
         .set("customcolor", new double[]{0.9921568632125854, 0.7254902124404907, 0.07450980693101883});
    model.result("pg4").run();
    model.result("pg4").feature("surf4").feature("mtrl1").set("family", "wood");
    model.result("pg4").run();
    model.result("pg4").feature("surf4").feature("sel1").selection().named("sel2");
    model.result("pg4").run();
    model.result("pg4").feature().duplicate("surf5", "surf4");
    model.result("pg4").run();
    model.result("pg4").feature("surf5")
         .set("customcolor", new double[]{0.501960813999176, 0.250980406999588, 0.250980406999588});
    model.result("pg4").run();
    model.result("pg4").feature("surf5").feature("sel1").selection().named("sel3");
    model.result("pg4").run();
    model.result("pg4").feature().duplicate("surf6", "surf5");
    model.result("pg4").run();
    model.result("pg4").feature("surf6")
         .set("customcolor", new double[]{0.4117647111415863, 0.4117647111415863, 0.4117647111415863});
    model.result("pg4").run();
    model.result("pg4").feature("surf6").feature("mtrl1").set("family", "plastic");
    model.result("pg4").run();
    model.result("pg4").feature("surf6").feature("sel1").selection().named("sel4");
    model.result("pg4").run();
    model.result("pg4").feature().duplicate("surf7", "surf6");
    model.result("pg4").run();
    model.result("pg4").feature("surf7")
         .set("customcolor", new double[]{0.7411764860153198, 0.7882353067398071, 0.8470588326454163});
    model.result("pg4").run();
    model.result("pg4").feature("surf7").feature("mtrl1").set("family", "chrome");
    model.result("pg4").run();
    model.result("pg4").feature("surf7").feature("sel1").selection().named("sel6");
    model.result("pg4").run();
    model.result("pg4").feature().duplicate("surf8", "surf7");
    model.result("pg4").run();
    model.result("pg4").feature("surf8")
         .set("customcolor", new double[]{0.6509804129600525, 0.8392156958580017, 0.8156862854957581});
    model.result("pg4").run();
    model.result("pg4").feature("surf8").feature("mtrl1").set("family", "textile");
    model.result("pg4").run();
    model.result("pg4").feature("surf8").feature("sel1").selection().set(3, 75);
    model.result("pg4").run();
    model.result("pg4").feature().duplicate("surf9", "surf7");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature("surf9").feature("sel1").selection().named("sel7");
    model.result("pg4").run();
    model.result("pg4").feature().duplicate("surf10", "surf6");
    model.result("pg4").run();
    model.result("pg4").feature("surf10")
         .set("customcolor", new double[]{0.6549019813537598, 0.6901960968971252, 0.7568627595901489});
    model.result("pg4").run();
    model.result("pg4").feature("surf10").feature("sel1").selection().set(213, 214, 215, 216, 229, 231);
    model.result("pg4").run();
    model.result("pg4").feature().duplicate("surf11", "surf10");
    model.result("pg4").run();
    model.result("pg4").feature("surf11")
         .set("customcolor", new double[]{0.4117647111415863, 0.4117647111415863, 0.4117647111415863});
    model.result("pg4").run();
    model.result("pg4").feature("surf11").feature("mtrl1").set("family", "aluminum");
    model.result("pg4").run();
    model.result("pg4").feature("surf11").feature("sel1").selection().set(221, 223);
    model.result("pg4").create("surf12", "Surface");
    model.result("pg4").feature("surf12").set("expr", "1");
    model.result("pg4").feature("surf12").set("coloring", "uniform");
    model.result("pg4").feature("surf12").set("color", "white");
    model.result("pg4").feature("surf12").create("sel1", "Selection");
    model.result("pg4").feature("surf12").feature("sel1").selection().set(262);
    model.result("pg4").run();
    model.result("pg4").create("line1", "Line");
    model.result("pg4").feature("line1").set("expr", "1");
    model.result("pg4").feature("line1").set("coloring", "uniform");
    model.result("pg4").feature("line1").set("color", "black");
    model.result("pg4").feature("line1").create("sel1", "Selection");
    model.result("pg4").feature("line1").feature("sel1").selection()
         .set(1, 2, 3, 4, 7, 8, 9, 10, 11, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 187, 191, 193, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255, 256, 257, 258, 259, 260, 261, 262, 263, 264, 265, 266, 267, 268, 269, 270, 271, 272, 273, 274, 275, 276, 277, 278, 279, 280, 281, 282, 283, 284, 285, 286, 287, 288, 289, 290, 291, 292, 293, 294, 295, 296, 297, 298, 299, 300, 301, 302, 303, 304, 305, 306, 307, 308, 309, 310, 311, 312, 313, 314, 315, 316, 317, 318, 319, 320, 321, 322, 323, 324, 325, 326, 327, 328, 329, 330, 331, 332, 333, 334, 335, 336, 337, 338, 339, 340, 341, 342, 343, 344, 345, 346, 347, 348, 349, 350, 351, 352, 353, 354, 355, 356, 357, 358, 359, 360, 361, 362, 363, 364, 365, 366, 367, 368, 369, 370, 371, 372, 373, 374, 375, 376, 377, 378, 379, 380, 381, 382, 383, 384, 385, 386, 387, 388, 389, 390, 391, 392, 393, 394, 395, 396, 397, 398, 399, 400, 401, 402, 403, 404, 405, 406, 407, 408, 409, 410, 411, 412, 413, 414, 415, 416, 417, 418, 419, 420, 421, 422, 423, 424, 425, 426, 427, 428, 429, 430, 431, 432, 433, 434, 435, 436, 437, 438, 439, 440, 441, 442, 443, 444, 445, 446, 447, 448, 449, 450, 451, 452, 453, 454, 455, 456, 457, 458, 459, 460, 461, 462, 463, 464, 465, 466, 467, 468, 469, 470, 471, 472, 473, 474, 475, 476, 477, 478, 479, 480, 481, 482, 483, 484, 485, 486, 487, 488, 489, 490, 491, 492, 493, 494, 495, 496, 497, 498, 499, 500, 501, 502, 503, 504, 505, 506, 507, 508, 509, 510, 511, 512, 513, 514, 515, 516, 517, 518, 519, 520, 521, 522, 523, 524, 525, 526, 527, 528, 529, 530, 531, 532, 533, 534, 535, 536, 537, 538, 539, 540, 541, 542, 543, 544, 545, 546, 547, 548, 549, 550, 551, 552, 553, 554, 555, 556, 557, 558, 559, 560, 561, 562, 563, 564, 565, 566, 567, 568, 569, 570, 571, 572, 573, 574, 575, 576, 577, 578, 579, 580, 581, 582, 583, 584, 585, 586, 587, 588, 589, 590, 591, 592, 593, 594, 595, 596, 597, 598, 599, 600, 601, 602, 603, 604, 605, 606, 607, 608, 609, 610, 611, 612, 613, 614, 615, 616, 617, 618, 619, 620, 621, 622, 623, 624, 625, 626, 627, 628, 629, 630, 631, 632, 633, 634, 635, 636, 637, 638, 639, 640, 641, 642, 643, 644, 645, 646, 647, 648, 649, 650, 651, 652, 653, 654, 655, 656, 657, 658, 659, 660, 661, 662, 663, 664, 665, 666, 667, 668, 669, 670, 671, 672, 673, 674, 675, 676, 677, 678, 679, 680, 681, 682, 683, 684, 685, 686, 687, 688, 689, 690, 691, 692, 693, 694, 695, 696, 697, 698, 699, 700, 701, 702, 703, 704, 705);
    model.result("pg4").run();
    model.result("pg4").feature().duplicate("surf13", "surf5");
    model.result("pg4").run();
    model.result("pg4").feature("surf13").set("customcolor", new double[]{0.250980406999588, 0, 0});
    model.result("pg4").run();
    model.result("pg4").feature("surf13").feature("sel1").selection().named("sel5");
    model.result("pg4").run();
    model.result("pg4").run();

    model
         .title("\u57fa\u4e8e\u6ce2\u7684\u65f6\u57df\u5ba4\u5185\u58f0\u5b66\u4eff\u771f\uff08\u542b\u9891\u7387\u76f8\u5173\u963b\u6297\uff09");

    model
         .description("\u8fd1\u5e74\u6765\uff0c\u968f\u7740\u8ba1\u7b97\u6027\u80fd\u7684\u63d0\u5347\u4ee5\u53ca\u65b0\u6570\u503c\u65b9\u6cd5\u7684\u53d1\u5c55\uff0c\u57fa\u4e8e\u6ce2\u7684\u5ba4\u5185\u58f0\u5b66\u4eff\u771f\u6280\u672f\u5f97\u5230\u4e86\u5e7f\u6cdb\u7684\u5e94\u7528\u3002\u4f20\u7edf\u4e0a\uff0c\u5305\u542b\u58c1\u4e0a\u7684\u5b9e\u9645\u963b\u6297\u6761\u4ef6\u8fd9\u4e00\u96be\u9898\u662f\u5728\u9891\u57df\u4e2d\u89e3\u51b3\u7684\uff0c\u800c\u6700\u65b0\u7684\u7814\u7a76\u81f4\u529b\u4e8e\u63a2\u7d22\u5982\u4f55\u5728\u65f6\u57df\u6a21\u578b\u4e2d\u901a\u8fc7\u6570\u636e\u7684\u90e8\u5206\u5206\u5f0f\u8868\u793a\u6765\u5b9e\u73b0\u9891\u7387\u76f8\u5173\u7684\u963b\u6297\u3002\n\n\u672c\u6559\u7a0b\u6f14\u793a\u5982\u4f55\u901a\u8fc7\u201c\u90e8\u5206\u5206\u5f0f\u62df\u5408\u201d\u51fd\u6570\u83b7\u53d6\u9891\u7387\u76f8\u5173\u58c1\u963b\u6297\u6570\u636e\u7684\u90e8\u5206\u5206\u5f0f\u8868\u793a\uff0c\u4ee5\u53ca\u5982\u4f55\u4f7f\u7528\u8fd9\u4e9b\u7ed3\u679c\u6765\u8bbe\u7f6e\u5185\u7f6e\u7684\u963b\u6297\u8fb9\u754c\u6761\u4ef6\uff0c\u4ee5\u5728\u65f6\u57df\u4e2d\u8fdb\u884c\u5ba4\u5185\u58f0\u5b66\u54cd\u5e94\u4eff\u771f\u3002\u672c\u6a21\u578b\u4f7f\u7528\u201c\u538b\u529b\u58f0\u5b66\uff0c\u65f6\u57df\u663e\u5f0f\u201d\u7269\u7406\u573a\u63a5\u53e3\u6765\u6a21\u62df\u58f0\u97f3\u7684\u4f20\u64ad\uff0c\u8be5\u63a5\u53e3\u57fa\u4e8e\u91c7\u7528\u65e0\u77e9\u9635\u65b9\u6cd5\u548c\u65f6\u57df\u663e\u5f0f\u6c42\u89e3\u5668\u7684\u95f4\u65ad\u4f3d\u8fbd\u91d1 (dG) \u65b9\u6cd5\uff0c\u8fd9\u79cd\u65b9\u6cd5\u5728\u5185\u5b58\u4f7f\u7528\u4e0a\u975e\u5e38\u9ad8\u6548\uff0c\u4e14\u975e\u5e38\u9002\u5408\u5728\u96c6\u7fa4\u67b6\u6784\u4e0a\u8fdb\u884c\u5206\u5e03\u5f0f\u8ba1\u7b97\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("wave_based_room.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
