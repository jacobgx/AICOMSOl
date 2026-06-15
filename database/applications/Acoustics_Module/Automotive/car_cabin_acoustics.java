/*
 * car_cabin_acoustics.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:25 by COMSOL 6.3.0.290. */
public class car_cabin_acoustics {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Automotive");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("acpr", "PressureAcoustics", "geom1");
    model.component("comp1").physics().create("cir", "Circuit", "geom1");
    model.component("comp1").physics().create("cir2", "Circuit", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/acpr", true);
    model.study("std1").feature("freq").setSolveFor("/physics/cir", true);
    model.study("std1").feature("freq").setSolveFor("/physics/cir2", true);

    model.component("comp1").geom("geom1").geomRep("cadps");
    model.component("comp1").geom("geom1").repairTolType("relative");
    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "car_cabin_acoustics_geometry.mphbin");
    model.component("comp1").geom("geom1").run("imp1");
    model.component("comp1").geom("geom1").create("cap1", "CapFaces");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").feature("cap1").selection("input")
         .set("imp1", 53, 54, 56, 57, 389, 390, 392, 393);
    model.component("comp1").geom("geom1").run("cap1");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", 2, 0);
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", -0.55, 1);
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", 1.2, 2);
    model.component("comp1").geom("geom1").run("pt1");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("pt1");
    model.component("comp1").geom("geom1").feature("arr1").set("fullsize", new int[]{6, 12, 1});
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new double[]{0.1, 0.1, 0});
    model.component("comp1").geom("geom1").feature("arr1").set("selresult", true);
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u7d2f\u79ef\u9009\u62e9 1");
    model.component("comp1").geom("geom1").feature("arr1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").selection("csel1.dom").show(false);
    model.component("comp1").geom("geom1").selection("csel1.bnd").show(false);
    model.component("comp1").geom("geom1").selection("csel1.edg").show(false);
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("ige1", "IgnoreEdges");
    model.component("comp1").geom("geom1").feature("ige1").selection("input").set("fin", 1981);
    model.component("comp1").geom("geom1").run("ige1");
    model.component("comp1").geom("geom1").create("rmd1", "RemoveDetails");
    model.component("comp1").geom("geom1").run("rmd1");
    model.component("comp1").geom("geom1").create("ige2", "IgnoreEdges");
    model.component("comp1").geom("geom1").feature("ige2").selection("input")
         .set("aigv8", 1178, 1183, 1191, 1678, 1686);
    model.component("comp1").geom("geom1").run("ige2");

    model.component("comp1").view("view1").set("ssao", true);

    model.param().label("\u53c2\u6570 1 - \u6a21\u578b");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("fmax", "1000[Hz]", "Maximal frequency for the sweep");
    model.param().set("f0", "2000[Hz]", "Study frequency");
    model.param().set("c0", "343[m/s]", "Speed of sound");
    model.param().set("rho0", "1.2[kg/m^3]", "Air density");
    model.param().set("relH", "0.5", "Relative humidity");
    model.param().set("V0", "2.83[V]", "Driver Source");
    model.param().create("par2");
    model.param("par2").label("\u53c2\u6570 2 - \u8fb9\u754c");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("alpha_win", "0.005", "Windows absorption coefficient");
    model.param("par2").set("alpha_dash", "0.01", "Dashboard absorption coefficient");
    model.param("par2").set("alpha_door", "0.01", "Doors absorption coefficient");
    model.param("par2").set("d_carp", "0.5[cm]", "Thickness of the carpet floor");
    model.param("par2").set("Rf_carp", "10000[Pa*s/m^2]", "Flow resistivity of the carpet floor");
    model.param("par2").set("d_roof", "0.7[cm]", "Thickness of the roof trim");
    model.param("par2").set("Rf_roof", "20000[Pa*s/m^2]", "Flow resistivity of the roof trim");
    model.param().create("par3");
    model.param("par3").label("\u53c2\u6570 3 - \u4e2d\u4f4e\u97f3\u626c\u58f0\u5668");

//    To import content from file, use:
//    model.param("par3").loadFile("FILENAME");
    model.param("par3").set("Sd_m", "0.0143[m^2]", "Diaphragm piston area");
    model.param("par3").set("R_m", "sqrt(Sd_m/pi)", "Piston radius");
    model.param("par3").set("Vback_m", "4[l]", "Back volume");
    model.param("par3").set("R1_m", "6[ohm]", "Resistance");
    model.param("par3").set("L1_m", "0.35[mH]", "Inductance");
    model.param("par3").set("L2_m", "18[g]*1[H/kg]", "Inductance");
    model.param("par3").set("R2_m", "1.4[kg/s]*1[ohm/kg*s]", "Resistance");
    model.param("par3").set("C1_m", "2.5[mm/N]*1[F*N/m]", "Capacitance");
    model.param("par3").set("A_m", "6[T*m]/1[T*m/ohm]", "Gain");
    model.param().create("par4");
    model.param("par4").label("\u53c2\u6570 4 - \u9ad8\u97f3\u626c\u58f0\u5668");

//    To import content from file, use:
//    model.param("par4").loadFile("FILENAME");
    model.param("par4").set("Sd_t", "5.9[cm^2]", "Diaphragm piston area");
    model.param("par4").set("R_t", "sqrt(Sd_t/pi)", "Piston radius");
    model.param("par4").set("Vback_t", "0.3[l]", "Back volume");
    model.param("par4").set("R1_t", "3.5[ohm]", "Resistance");
    model.param("par4").set("L1_t", "0.012[mH]", "Inductance");
    model.param("par4").set("L2_t", "0.7[g]*1[H/kg]", "Inductance");
    model.param("par4").set("R2_t", "0.6[kg/s]*1[ohm/kg*s]", "Resistance");
    model.param("par4").set("C1_t", "0.2[mm/N]*1[F*N/m]", "Capacitance");
    model.param("par4").set("A_t", "3[T*m]/1[T*m/ohm]", "Gain");

    model.func().create("int1", "Interpolation");
    model.func("int1").label("\u63d2\u503c 1 - \u76ae\u9769\u5ea7\u6905");
    model.func("int1").set("source", "file");
    model.func("int1").set("filename", "car_cabin_acoustics_impedance_seats.txt");
    model.func("int1").set("nargs", 1);
    model.func("int1").setIndex("funcs", "realZ_seat", 0, 0);
    model.func("int1").setIndex("funcs", "imagZ_seat", 1, 0);
    model.func("int1").setIndex("funcs", 2, 1, 1);
    model.func("int1").set("interp", "piecewisecubic");
    model.func("int1").setIndex("fununit", 1, 0);
    model.func("int1").setIndex("fununit", 1, 1);
    model.func("int1").setIndex("argunit", "Hz", 0);
    model.func("int1").importData();
    model.func().create("an1", "Analytic");
    model.func("an1").label("\u89e3\u6790 1 - Struve \u51fd\u6570\uff08\u4e00\u9636\uff09");
    model.func("an1").set("funcname", "h1");
    model.func("an1").set("expr", "2/pi-besselj(0,x)+(16/pi-5)*sin(x)/x+(12-36/pi)*(1-cos(x))/x^2");
    model.func("an1").set("fununit", "1");
    model.func("an1").setIndex("argunit", 1, 0);
    model.func("an1").set("complex", true);
    model.func("an1").setIndex("plotargs", 10, 0, 2);
    model.func().create("an2", "Analytic");
    model.func("an2").label("\u89e3\u6790 2 - \u6d3b\u585e\u963b\u6297");
    model.func("an2").set("funcname", "Zp");
    model.func("an2").set("expr", "rho0*c0*(1-besselj(1,2*x)/x+i*h1(2*x)/x)");
    model.func("an2").set("fununit", "kg/(s*m^2)");
    model.func("an2").setIndex("argunit", 1, 0);
    model.func("an2").set("complex", true);
    model.func("an2").setIndex("plotargs", 10, 0, 2);

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

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").label("\u53d8\u91cf 1 - \u58f0\u5b66");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1").set("k0", "2*pi*freq/c0", "Wave number");
    model.component("comp1").variable("var1")
         .set("alpha_all", "if(isnan(acpr.imp1.alpha_n),0,acpr.imp1.alpha_n)+if(isnan(acpr.imp2.alpha_n),0,acpr.imp2.alpha_n)+if(isnan(acpr.imp3.alpha_n),0,acpr.imp3.alpha_n)+if(isnan(acpr.imp4.alpha_n),0,acpr.imp4.alpha_n)+if(isnan(acpr.imp5.alpha_n),0,acpr.imp5.alpha_n)+if(isnan(acpr.imp6.alpha_n),0,acpr.imp6.alpha_n)", "Absorption coefficent of surfaces");
    model.component("comp1").variable("var1")
         .set("Zn_seat", "acpr.rho*acpr.c*(realZ_seat(freq)+i*imagZ_seat(freq))", "Specific impedance of leather seats");
    model.component("comp1").variable().create("var2");
    model.component("comp1").variable("var2").label("\u53d8\u91cf 2 - \u7535\u8def");

//    To import content from file, use:
//    model.component("comp1").variable("var2").loadFile("FILENAME");
    model.component("comp1").variable("var2")
         .set("Zrad_m", "Zp(k0*R_m)/Sd_m", "Radiation impedance of the midwoofer");
    model.component("comp1").variable("var2")
         .set("Zbv_m", "rho0*c0^2/(i*2*pi*freq*Vback_m)", "Back volume impedance of the midwoofer");
    model.component("comp1").variable("var2").set("Zrad_t", "Zp(k0*R_t)/Sd_t", "Radiation impedance of the tweeter");
    model.component("comp1").variable("var2")
         .set("Zbv_t", "rho0*c0^2/(i*2*pi*freq*Vback_t)", "Back volume impedance of the tweeter");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u6240\u6709\u8fb9\u754c");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").all();
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u8f66\u7a97");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").set(3, 4, 62, 63, 489, 495, 771, 775, 798, 801, 804, 805);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u4eea\u8868\u677f");
    model.component("comp1").selection("sel3").geom(2);
    model.component("comp1").selection("sel3")
         .set(1, 2, 5, 6, 7, 8, 9, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 43, 44, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 64, 65, 66, 67, 68, 69, 70, 71, 85, 86, 87, 88, 89, 90, 91, 92, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 141, 142, 143, 144, 146, 147, 156, 157, 158, 159, 160, 311, 312, 313, 318, 319, 320, 321, 322, 324, 756, 799);
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").label("\u5730\u6bef");
    model.component("comp1").selection("sel4").geom(2);
    model.component("comp1").selection("sel4").set(10);
    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").label("\u8f66\u95e8");
    model.component("comp1").selection("sel5").geom(2);
    model.component("comp1").selection("sel5")
         .set(148, 149, 150, 151, 152, 153, 154, 155, 161, 162, 169, 170, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 206, 207, 208, 209, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 250, 251, 252, 253, 254, 260, 271, 272, 279, 280, 281, 282, 283, 284, 285, 286, 287, 288, 289, 290, 291, 292, 305, 306, 307, 308, 309, 310, 350, 351, 352, 353, 356, 357, 358, 359, 360, 361, 362, 363, 364, 365, 366, 369, 370, 371, 372, 373, 374, 375, 376, 377, 378, 379, 380, 393, 394, 415, 416, 425, 426, 499, 500, 501, 502, 503, 504, 505, 506, 507, 508, 509, 510, 511, 512, 513, 514, 515, 517, 518, 519, 520, 521, 522, 523, 524, 525, 526, 527, 528, 529, 530, 531, 532, 533, 534, 535, 536, 537, 538, 539, 540, 541, 542, 543, 544, 545, 546, 547, 548, 549, 550, 551, 552, 553, 554, 555, 556, 557, 558, 559, 560, 566, 567, 580, 581, 582, 583, 584, 585, 586, 587, 588, 589, 590, 591, 592, 593, 608, 609, 610, 611, 612, 613, 616, 617, 618, 619, 620, 621, 622, 623, 624, 625, 626, 627, 628, 629, 630, 633, 634, 635, 636, 637, 638, 639, 640, 648, 649, 650, 651, 652, 653, 654, 655, 656, 657, 658, 659, 665, 666, 667, 668, 669, 670);
    model.component("comp1").selection().create("sel6", "Explicit");
    model.component("comp1").selection("sel6").label("\u771f\u76ae\u5ea7\u6905");
    model.component("comp1").selection("sel6").geom(2);
    model.component("comp1").selection("sel6")
         .set(198, 199, 200, 201, 202, 203, 204, 205, 210, 211, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 267, 268, 269, 270, 275, 276, 277, 278, 293, 294, 295, 296, 297, 298, 299, 300, 326, 327, 328, 329, 330, 331, 332, 333, 334, 335, 336, 337, 338, 339, 340, 341, 342, 343, 344, 345, 346, 347, 348, 349, 354, 355, 367, 368, 381, 382, 383, 384, 385, 386, 387, 388, 389, 390, 391, 392, 401, 402, 403, 404, 405, 406, 407, 408, 409, 410, 411, 412, 413, 414, 418, 420, 421, 422, 423, 424, 427, 428, 429, 430, 431, 432, 433, 434, 449, 450, 451, 452, 453, 454, 455, 456, 465, 466, 467, 468, 469, 470, 471, 472, 473, 474, 475, 476, 561, 562, 564, 565, 568, 569, 570, 571, 572, 573, 574, 575, 576, 577, 578, 579, 594, 595, 601, 602, 603, 604, 605, 606, 607, 641, 642, 643, 644, 645, 646, 647, 671, 672, 687, 698, 699, 700, 701, 702, 703, 704, 705, 706, 707, 708, 709, 710, 711, 720, 721, 722, 723, 724, 725, 726, 727, 728, 729, 730, 731, 732, 733, 734, 736, 738, 747, 748, 749, 750, 751, 752, 753, 754, 757, 758, 759, 760, 761, 762, 763, 764, 765, 766, 777, 778, 779, 780, 781, 782, 783, 784, 785, 786, 787, 788, 789, 790, 791, 792, 793, 794, 795, 796);
    model.component("comp1").selection().create("sel7", "Explicit");
    model.component("comp1").selection("sel7").label("\u8f66\u9876\u5185\u9970");
    model.component("comp1").selection("sel7").geom(2);
    model.component("comp1").selection("sel7").set(257, 258, 477, 478, 712, 713);
    model.component("comp1").selection().create("sel8", "Explicit");
    model.component("comp1").selection("sel8").label("\u626c\u58f0\u5668\u76d6");
    model.component("comp1").selection("sel8").geom(2);
    model.component("comp1").selection("sel8").set(33, 36, 165, 168);
    model.component("comp1").selection().create("sel9", "Explicit");
    model.component("comp1").selection("sel9").label("\u4e2d\u4f4e\u97f3\u626c\u58f0\u5668 L");
    model.component("comp1").selection("sel9").geom(2);
    model.component("comp1").selection("sel9").set(163, 164, 171, 172);
    model.component("comp1").selection().create("sel10", "Explicit");
    model.component("comp1").selection("sel10").label("\u4e2d\u4f4e\u97f3\u626c\u58f0\u5668 R");
    model.component("comp1").selection("sel10").geom(2);
    model.component("comp1").selection("sel10").set(166, 167, 173, 174);
    model.component("comp1").selection().create("sel11", "Explicit");
    model.component("comp1").selection("sel11").label("\u9ad8\u97f3\u626c\u58f0\u5668 R");
    model.component("comp1").selection("sel11").geom(2);
    model.component("comp1").selection("sel11").set(39, 40);
    model.component("comp1").selection().create("sel12", "Explicit");
    model.component("comp1").selection("sel12").label("\u9ad8\u97f3\u626c\u58f0\u5668 L");
    model.component("comp1").selection("sel12").geom(2);
    model.component("comp1").selection("sel12").set(37, 38);
    model.component("comp1").selection().create("dif1", "Difference");
    model.component("comp1").selection("dif1").label("\u786c\u58f0\u573a\u8868\u9762");
    model.component("comp1").selection("dif1").set("entitydim", 2);
    model.component("comp1").selection("dif1").set("add", new String[]{"sel1"});
    model.component("comp1").selection("dif1")
         .set("subtract", new String[]{"sel2", "sel3", "sel4", "sel5", "sel6", "sel7", "sel8", "sel9", "sel10", "sel11", 
         "sel12"});
    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").label("\u6240\u6709\u626c\u58f0\u5668");
    model.component("comp1").selection("uni1").set("entitydim", 2);
    model.component("comp1").selection("uni1").set("input", new String[]{"sel9", "sel10", "sel11", "sel12"});

    model.component("comp1").physics("acpr").feature("fpam1").set("FluidModel", "AtmosphereAttenuation");
    model.component("comp1").physics("acpr").feature("fpam1").set("minput_relativehumidity", "relH");
    model.component("comp1").physics("acpr").create("imp1", "Impedance", 2);
    model.component("comp1").physics("acpr").feature("imp1").label("\u963b\u6297 1 - \u8f66\u7a97");
    model.component("comp1").physics("acpr").feature("imp1").selection().named("sel2");
    model.component("comp1").physics("acpr").feature("imp1").set("ImpedanceModel", "AbsorptionCoefficient");
    model.component("comp1").physics("acpr").feature("imp1").set("alpha_n", "alpha_win");
    model.component("comp1").physics("acpr").create("imp2", "Impedance", 2);
    model.component("comp1").physics("acpr").feature("imp2").label("\u963b\u6297 2 - \u4eea\u8868\u677f");
    model.component("comp1").physics("acpr").feature("imp2").selection().named("sel3");
    model.component("comp1").physics("acpr").feature("imp2").set("ImpedanceModel", "AbsorptionCoefficient");
    model.component("comp1").physics("acpr").feature("imp2").set("alpha_n", "alpha_dash");
    model.component("comp1").physics("acpr").create("imp3", "Impedance", 2);
    model.component("comp1").physics("acpr").feature("imp3").label("\u963b\u6297 3 - \u5730\u6bef");
    model.component("comp1").physics("acpr").feature("imp3").selection().named("sel4");
    model.component("comp1").physics("acpr").feature("imp3").set("ImpedanceModel", "PorousModel");
    model.component("comp1").physics("acpr").feature("imp3").set("thickness", "d_carp");
    model.component("comp1").physics("acpr").feature("imp3").set("WaveDirection", "Automatic");
    model.component("comp1").physics("acpr").feature("imp3").set("FluidMaterial", "mat1");
    model.component("comp1").physics("acpr").feature("imp3").set("SolidMaterial", "mat1");
    model.component("comp1").physics("acpr").feature("imp3").set("Rf_mat", "userdef");
    model.component("comp1").physics("acpr").feature("imp3").set("Rf", "Rf_carp");
    model.component("comp1").physics("acpr").feature("imp3").set("Constants", "Miki");
    model.component("comp1").physics("acpr").create("imp4", "Impedance", 2);
    model.component("comp1").physics("acpr").feature("imp4").label("\u963b\u6297 4 - \u8f66\u95e8");
    model.component("comp1").physics("acpr").feature("imp4").selection().named("sel5");
    model.component("comp1").physics("acpr").feature("imp4").set("ImpedanceModel", "AbsorptionCoefficient");
    model.component("comp1").physics("acpr").feature("imp4").set("alpha_n", "alpha_door");
    model.component("comp1").physics("acpr").create("imp5", "Impedance", 2);
    model.component("comp1").physics("acpr").feature("imp5").label("\u963b\u6297 5 - \u771f\u76ae\u5ea7\u6905");
    model.component("comp1").physics("acpr").feature("imp5").selection().named("sel6");
    model.component("comp1").physics("acpr").feature("imp5").set("Zn", "Zn_seat");
    model.component("comp1").physics("acpr").create("imp6", "Impedance", 2);
    model.component("comp1").physics("acpr").feature("imp6").label("\u963b\u6297 6 - \u8f66\u9876\u5185\u9970");
    model.component("comp1").physics("acpr").feature("imp6").selection().named("sel7");
    model.component("comp1").physics("acpr").feature("imp6").set("ImpedanceModel", "PorousModel");
    model.component("comp1").physics("acpr").feature("imp6").set("thickness", "d_roof");
    model.component("comp1").physics("acpr").feature("imp6").set("WaveDirection", "Automatic");
    model.component("comp1").physics("acpr").feature("imp6").set("FluidMaterial", "mat1");
    model.component("comp1").physics("acpr").feature("imp6").set("SolidMaterial", "mat1");
    model.component("comp1").physics("acpr").feature("imp6").set("Rf_mat", "userdef");
    model.component("comp1").physics("acpr").feature("imp6").set("Rf", "Rf_roof");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").physics("acpr").feature("imp6").set("Constants", "Qunli");
    model.component("comp1").physics("acpr").create("ipp1", "InteriorPerforatedPlate", 2);
    model.component("comp1").physics("acpr").feature("ipp1").selection().named("sel8");
    model.component("comp1").physics("acpr").feature("ipp1").set("porArea", 0.5);
    model.component("comp1").physics("acpr").feature("ipp1").set("FluidMaterial", "mat1");
    model.component("comp1").physics("acpr").create("lsb1", "LumpedSpeakerBoundary", 2);
    model.component("comp1").physics("acpr").feature("lsb1")
         .label("\u96c6\u603b\u626c\u58f0\u5668\u8fb9\u754c 1 - \u4e2d\u4f4e\u97f3\u626c\u58f0\u5668 L");
    model.component("comp1").physics("acpr").feature("lsb1").selection().named("sel9");
    model.component("comp1").physics("acpr").feature("lsb1").set("V_back", "Vback_m");
    model.component("comp1").physics("acpr").create("lsb2", "LumpedSpeakerBoundary", 2);
    model.component("comp1").physics("acpr").feature("lsb2")
         .label("\u96c6\u603b\u626c\u58f0\u5668\u8fb9\u754c 2 - \u9ad8\u97f3\u626c\u58f0\u5668 R");
    model.component("comp1").physics("acpr").feature("lsb2").selection().named("sel11");
    model.component("comp1").physics("acpr").feature("lsb2").set("V_back", "Vback_t");
    model.component("comp1").physics("cir").label("\u7535\u8def - \u4e2d\u4f4e\u97f3\u626c\u58f0\u5668");
    model.component("comp1").physics("cir").create("V1", "VoltageSource", -1);
    model.component("comp1").physics("cir").create("R1", "Resistor", -1);
    model.component("comp1").physics("cir").create("L1", "Inductor", -1);
    model.component("comp1").physics("cir").create("H1", "CurrentVoltageSource", -1);
    model.component("comp1").physics("cir").create("H2", "CurrentVoltageSource", -1);
    model.component("comp1").physics("cir").create("L2", "Inductor", -1);
    model.component("comp1").physics("cir").create("R2", "Resistor", -1);
    model.component("comp1").physics("cir").create("C1", "Capacitor", -1);
    model.component("comp1").physics("cir").create("IvsU1", "ModelDeviceIV", -1);
    model.component("comp1").physics("cir").create("E1", "VoltageVoltageSource", -1);
    model.component("comp1").physics("cir").create("F1", "CurrentCurrentSource", -1);
    model.component("comp1").physics("cir").create("R3", "Resistor", -1);
    model.component("comp1").physics("cir").create("R4", "Resistor", -1);
    model.component("comp1").physics("cir").feature("V1").setIndex("Connections", 0, 1, 0);
    model.component("comp1").physics("cir").feature("V1").set("value", "V0");
    model.component("comp1").physics("cir").feature("R1").setIndex("Connections", 1, 0, 0);
    model.component("comp1").physics("cir").feature("R1").setIndex("Connections", 2, 1, 0);
    model.component("comp1").physics("cir").feature("R1").set("R", "R1_m");
    model.component("comp1").physics("cir").feature("L1").setIndex("Connections", 2, 0, 0);
    model.component("comp1").physics("cir").feature("L1").setIndex("Connections", 3, 1, 0);
    model.component("comp1").physics("cir").feature("L1").set("L", "L1_m");
    model.component("comp1").physics("cir").feature("H1").setIndex("Connections", 3, 0, 0);
    model.component("comp1").physics("cir").feature("H1").setIndex("Connections", 0, 1, 0);
    model.component("comp1").physics("cir").feature("H1").set("device", "L2");
    model.component("comp1").physics("cir").feature("H1").set("gain", "A_m");
    model.component("comp1").physics("cir").feature("H2").setIndex("Connections", 6, 0, 0);
    model.component("comp1").physics("cir").feature("H2").setIndex("Connections", 0, 1, 0);
    model.component("comp1").physics("cir").feature("H2").set("device", "R1");
    model.component("comp1").physics("cir").feature("H2").set("gain", "A_m");
    model.component("comp1").physics("cir").feature("L2").setIndex("Connections", 6, 0, 0);
    model.component("comp1").physics("cir").feature("L2").setIndex("Connections", 7, 1, 0);
    model.component("comp1").physics("cir").feature("L2").set("L", "L2_m");
    model.component("comp1").physics("cir").feature("R2").setIndex("Connections", 7, 0, 0);
    model.component("comp1").physics("cir").feature("R2").setIndex("Connections", 8, 1, 0);
    model.component("comp1").physics("cir").feature("R2").set("R", "R2_m");
    model.component("comp1").physics("cir").feature("C1").setIndex("Connections", 8, 0, 0);
    model.component("comp1").physics("cir").feature("C1").setIndex("Connections", 9, 1, 0);
    model.component("comp1").physics("cir").feature("C1").set("C", "C1_m");
    model.component("comp1").physics("cir").feature("IvsU1").set("V_src", "root.comp1.acpr.lsb1.V_cir");
    model.component("comp1").physics("cir").feature("IvsU1").setIndex("Connections", 9, 0, 0);
    model.component("comp1").physics("cir").feature("IvsU1").setIndex("Connections", 0, 1, 0);
    model.component("comp1").physics("cir").feature("E1").setIndex("Connections", 9, 0, 0);
    model.component("comp1").physics("cir").feature("E1").setIndex("Connections", 0, 1, 0);
    model.component("comp1").physics("cir").feature("E1").setIndex("Connections", 11, 2, 0);
    model.component("comp1").physics("cir").feature("E1").setIndex("Connections", 10, 3, 0);
    model.component("comp1").physics("cir").feature("E1").set("gain", "Sd_m[1/m^2]");
    model.component("comp1").physics("cir").feature("F1").setIndex("Connections", 10, 0, 0);
    model.component("comp1").physics("cir").feature("F1").setIndex("Connections", 11, 1, 0);
    model.component("comp1").physics("cir").feature("F1").set("device", "E1");
    model.component("comp1").physics("cir").feature("F1").set("gain", "Sd_m[1/m^2]");
    model.component("comp1").physics("cir").feature("R3")
         .label("\u7535\u963b\u5668 3 - \u96c6\u603b\u8f90\u5c04\u963b\u6297");
    model.component("comp1").physics("cir").feature("R3").setIndex("Connections", 10, 0, 0);
    model.component("comp1").physics("cir").feature("R3").setIndex("Connections", 0, 1, 0);
    model.component("comp1").physics("cir").feature("R3").set("R", "Zrad_m*1[ohm*m^4*s/kg]");
    model.component("comp1").physics("cir").feature("R4")
         .label("\u7535\u963b\u5668 4 - \u96c6\u603b\u540e\u8154\u67d4\u5ea6");
    model.component("comp1").physics("cir").feature("R4").setIndex("Connections", 11, 0, 0);
    model.component("comp1").physics("cir").feature("R4").setIndex("Connections", 0, 1, 0);
    model.component("comp1").physics("cir").feature("R4").set("R", "Zbv_m*1[ohm*m^4*s/kg]");
    model.component("comp1").physics("cir2").label("\u7535\u8def 2 - \u9ad8\u97f3\u626c\u58f0\u5668");
    model.component("comp1").physics("cir2").feature().copy("V1", "cir/V1");
    model.component("comp1").physics("cir2").feature().copy("R1", "cir/R1");
    model.component("comp1").physics("cir2").feature().copy("L1", "cir/L1");
    model.component("comp1").physics("cir2").feature().copy("H1", "cir/H1");
    model.component("comp1").physics("cir2").feature().copy("H2", "cir/H2");
    model.component("comp1").physics("cir2").feature().copy("L2", "cir/L2");
    model.component("comp1").physics("cir2").feature().copy("R2", "cir/R2");
    model.component("comp1").physics("cir2").feature().copy("C1", "cir/C1");
    model.component("comp1").physics("cir2").feature().copy("IvsU1", "cir/IvsU1");
    model.component("comp1").physics("cir2").feature().copy("E1", "cir/E1");
    model.component("comp1").physics("cir2").feature().copy("F1", "cir/F1");
    model.component("comp1").physics("cir2").feature().copy("R3", "cir/R3");
    model.component("comp1").physics("cir2").feature().copy("R4", "cir/R4");
    model.component("comp1").physics("cir2").feature("V1").setIndex("Connections", 0, 1, 0);
    model.component("comp1").physics("cir2").feature("R1").setIndex("Connections", 1, 0, 0);
    model.component("comp1").physics("cir2").feature("R1").setIndex("Connections", 2, 1, 0);
    model.component("comp1").physics("cir2").feature("R1").set("R", "R1_t");
    model.component("comp1").physics("cir2").feature("L1").setIndex("Connections", 2, 0, 0);
    model.component("comp1").physics("cir2").feature("L1").setIndex("Connections", 3, 1, 0);
    model.component("comp1").physics("cir2").feature("L1").set("L", "L1_t");
    model.component("comp1").physics("cir2").feature("H1").setIndex("Connections", 3, 0, 0);
    model.component("comp1").physics("cir2").feature("H1").setIndex("Connections", 0, 1, 0);
    model.component("comp1").physics("cir2").feature("H1").set("device", "L2");
    model.component("comp1").physics("cir2").feature("H1").set("gain", "A_t");
    model.component("comp1").physics("cir2").feature("H2").setIndex("Connections", 6, 0, 0);
    model.component("comp1").physics("cir2").feature("H2").setIndex("Connections", 0, 1, 0);
    model.component("comp1").physics("cir2").feature("H2").set("gain", "A_t");
    model.component("comp1").physics("cir2").feature("L2").setIndex("Connections", 6, 0, 0);
    model.component("comp1").physics("cir2").feature("L2").setIndex("Connections", 7, 1, 0);
    model.component("comp1").physics("cir2").feature("L2").set("L", "L2_t");
    model.component("comp1").physics("cir2").feature("R2").setIndex("Connections", 7, 0, 0);
    model.component("comp1").physics("cir2").feature("R2").setIndex("Connections", 8, 1, 0);
    model.component("comp1").physics("cir2").feature("R2").set("R", "R2_t");
    model.component("comp1").physics("cir2").feature("C1").setIndex("Connections", 8, 0, 0);
    model.component("comp1").physics("cir2").feature("C1").setIndex("Connections", 9, 1, 0);
    model.component("comp1").physics("cir2").feature("C1").set("C", "C1_t");
    model.component("comp1").physics("cir2").feature("IvsU1").set("V_src", "root.comp1.acpr.lsb2.V_cir");
    model.component("comp1").physics("cir2").feature("IvsU1").setIndex("Connections", 9, 0, 0);
    model.component("comp1").physics("cir2").feature("IvsU1").setIndex("Connections", 0, 1, 0);
    model.component("comp1").physics("cir2").feature("E1").setIndex("Connections", 9, 0, 0);
    model.component("comp1").physics("cir2").feature("E1").setIndex("Connections", 0, 1, 0);
    model.component("comp1").physics("cir2").feature("E1").setIndex("Connections", 11, 2, 0);
    model.component("comp1").physics("cir2").feature("E1").setIndex("Connections", 10, 3, 0);
    model.component("comp1").physics("cir2").feature("E1").set("gain", "Sd_t[1/m^2]");
    model.component("comp1").physics("cir2").feature("F1").setIndex("Connections", 10, 0, 0);
    model.component("comp1").physics("cir2").feature("F1").setIndex("Connections", 11, 1, 0);
    model.component("comp1").physics("cir2").feature("F1").set("gain", "Sd_t[1/m^2]");
    model.component("comp1").physics("cir2").feature("R3").setIndex("Connections", 10, 0, 0);
    model.component("comp1").physics("cir2").feature("R3").setIndex("Connections", 0, 1, 0);
    model.component("comp1").physics("cir2").feature("R3").set("R", "Zrad_t*1[ohm*m^4*s/kg]");
    model.component("comp1").physics("cir2").feature("R4").setIndex("Connections", 11, 0, 0);
    model.component("comp1").physics("cir2").feature("R4").setIndex("Connections", 0, 1, 0);
    model.component("comp1").physics("cir2").feature("R4").set("R", "Zbv_t*1[ohm*m^4*s/kg]");

    model.component("comp1").mesh("mesh1").label("\u7f51\u683c 1 - \u7535\u8def");
    model.component("comp1").mesh("mesh1").contribute("physics/acpr", false);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76 1 - \u626c\u58f0\u5668\u54cd\u5e94");
    model.study("std1").setGenPlots(false);
    model.study("std1").feature("freq")
         .set("plist", "{50, 56, 63, 71, 80, 90, 100, 112, 125, 140, 160, 180, 200, 224, 250, 280, 315, 355, 400, 450, 500, 560, 630, 710, 800, 900, 1e3, 1.12e3, 1.25e3, 1.4e3, 1.6e3, 1.8e3, 2e3, 2.24e3, 2.5e3, 2.8e3, 3.15e3, 3.55e3, 4e3, 4.5e3, 5e3, 5.6e3, 6.3e3, 7.1e3, 8e3, 9e3, 1e4}");
    model.study("std1").feature("freq").set("useadvanceddisable", true);
    model.study("std1").feature("freq").setSolveFor("/physics/acpr", false);
    model.study("std1").feature("freq").set("disabledphysics", new String[]{"cir/IvsU1", "cir2/IvsU1"});
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").label("\u626c\u58f0\u5668\u54cd\u5e94");
    model.result("pg1").set("titletype", "label");
    model.result("pg1").set("legendpos", "upperleft");
    model.result("pg1").create("oct1", "OctaveBand");
    model.result("pg1").feature("oct1").set("quantity", "bandpower");
    model.result("pg1").feature("oct1").set("markerpos", "datapoints");
    model.result("pg1").feature("oct1").set("linewidth", "preference");
    model.result("pg1").feature("oct1").selection().geom("geom1");
    model.result("pg1").feature("oct1").set("expr", "acpr.iomega*rho0*cir.R3_i*exp(-i*k0*1[m])/(2*pi*1[m])");
    model.result("pg1").feature("oct1").set("quantity", "continuous");
    model.result("pg1").feature("oct1").set("legend", true);
    model.result("pg1").feature("oct1").set("legendmethod", "manual");
    model.result("pg1").feature("oct1").setIndex("legends", "\u4e2d\u4f4e\u97f3\u626c\u58f0\u5668", 0);
    model.result("pg1").feature().duplicate("oct2", "oct1");
    model.result("pg1").run();
    model.result("pg1").feature("oct2").set("expr", "acpr.iomega*rho0*cir2.R3_i*exp(-i*k0*1[m])/(2*pi*1[m])");
    model.result("pg1").feature("oct2").setIndex("legends", "\u9ad8\u97f3\u626c\u58f0\u5668", 0);
    model.result("pg1").run();

    model.study().create("std2");
    model.study("std2").create("eig", "Eigenfrequency");
    model.study("std2").feature("eig").setSolveFor("/physics/acpr", true);
    model.study("std2").feature("eig").setSolveFor("/physics/cir", true);
    model.study("std2").feature("eig").setSolveFor("/physics/cir2", true);
    model.study().create("std3");
    model.study("std3").create("freq", "Frequency");
    model.study("std3").feature("freq").setSolveFor("/physics/acpr", true);
    model.study("std3").feature("freq").setSolveFor("/physics/cir", true);
    model.study("std3").feature("freq").setSolveFor("/physics/cir2", true);
    model.study().create("std4");
    model.study("std4").create("freq", "Frequency");
    model.study("std4").feature("freq").setSolveFor("/physics/acpr", true);
    model.study("std4").feature("freq").setSolveFor("/physics/cir", true);
    model.study("std4").feature("freq").setSolveFor("/physics/cir2", true);
    model.study().create("std5");
    model.study("std5").create("freq", "Frequency");
    model.study("std5").feature("freq").setSolveFor("/physics/acpr", true);
    model.study("std5").feature("freq").setSolveFor("/physics/cir", true);
    model.study("std5").feature("freq").setSolveFor("/physics/cir2", true);

    model.component("comp1").mesh().duplicate("mesh2", "mesh1");
    model.component("comp1").mesh("mesh2")
         .label("\u7f51\u683c 2 - \u7528\u4e8e\u9891\u7387\u626b\u63cf\u7684\u56fa\u5b9a\u7f51\u683c");

    model.component("comp1").physics("acpr").prop("MeshControl").set("SizeControlParameter", "Frequency");

    model.component("comp1").mesh("mesh2").automatic(false);
    model.component("comp1").mesh("mesh2").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh2").feature("size").set("hmax", "c0/fmax/5");
    model.component("comp1").mesh("mesh2").feature().remove("size1");
    model.component("comp1").mesh("mesh2").feature().remove("se1");
    model.component("comp1").mesh("mesh2").feature("ftet1").set("optsmall", true);
    model.component("comp1").mesh("mesh2").run();
    model.component("comp1").mesh().duplicate("mesh3", "mesh2");
    model.component("comp1").mesh("mesh3")
         .label("\u7f51\u683c 3 - \u7528\u4e8e\u9ad8\u9891\u7684\u81ea\u9002\u5e94\u7f51\u683c");
    model.component("comp1").mesh("mesh3").feature("size").set("hmax", "c0/f0/5");

    model.study("std2").label("\u7814\u7a76 2 - \u6a21\u6001\u5206\u6790");
    model.study("std2").feature("eig").set("eigwhich", "lr");
    model.study("std2").feature("eig").setSolveFor("/physics/cir", false);
    model.study("std2").feature("eig").setSolveFor("/physics/cir2", false);
    model.study("std2").feature("eig").setEntry("mesh", "geom1", "mesh2");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").setIndex("looplevel", 1, 0);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg2").feature("surf1").set("colortable", "Wave");
    model.result("pg2").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").label("\u58f0\u538b (acpr)");
    model.result("pg2").feature("surf1").set("colortable", "WaveLight");
    model.result("pg2").create("con1", "Contour");
    model.result("pg2").feature("con1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg2").feature("con1").set("colortable", "Wave");
    model.result("pg2").feature("con1").set("colorscalemode", "linearsymmetric");
    model.result("pg2").feature("con1").set("colorlegend", false);
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").setIndex("looplevel", 1, 0);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"acpr.Lp_t"});
    model.result("pg3").feature("surf1").set("colortable", "Rainbow");
    model.result("pg3").feature("surf1").set("colorscalemode", "linear");
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").label("\u58f0\u538b\u7ea7 (acpr)");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").setIndex("looplevel", 1, 0);
    model.result("pg4").create("iso1", "Isosurface");
    model.result("pg4").feature("iso1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg4").feature("iso1").set("number", "10");
    model.result("pg4").feature("iso1").set("colortable", "Wave");
    model.result("pg4").feature("iso1").set("colorscalemode", "linearsymmetric");
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").label("\u58f0\u538b\uff0c\u7b49\u503c\u9762 (acpr)");
    model.result().evaluationGroup().create("std2EvgFrq", "EvaluationGroup");
    model.result().evaluationGroup("std2EvgFrq").set("defaultPlotID", "eigenfrequenciesTable_acpr");
    model.result().evaluationGroup("std2EvgFrq").set("data", "dset2");
    model.result().evaluationGroup("std2EvgFrq")
         .label("\u7279\u5f81\u9891\u7387 (\u7814\u7a76 2 - \u6a21\u6001\u5206\u6790)");
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
    model.result("pg2").run();
    model.result("pg2").set("looplevel", new int[]{5});
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "\u538b\u529b\u7279\u5f81\u6a21\u6001");
    model.result("pg2").set("paramindicator", "Eigenfrequency=eval(freq) Hz");
    model.result("pg2").run();
    model.result("pg2").set("looplevel", new int[]{6});
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").set("looplevel", new int[]{5});
    model.result("pg3").set("titletype", "manual");
    model.result("pg3").set("title", "\u58f0\u538b\u7ea7\u7279\u5f81\u6a21\u6001");
    model.result("pg3").set("paramindicator", "Eigenfrequency=eval(freq) Hz");
    model.result("pg3").run();
    model.result("pg3").set("looplevel", new int[]{6});
    model.result("pg3").run();

    model.study("std3").label("\u7814\u7a76 3 - \u9891\u7387\u626b\u63cf\u81f3 fmax");
    model.study("std3").setGenPlots(false);
    model.study("std3").feature("freq")
         .set("plist", "{50, 53, 56, 60, 63, 67, 71, 75, 80, 85, 90, 95, 100, 106, 112, 118, 125, 132, 140, 150, 160, 170, 180, 190, 200, 212, 224, 236, 250, 265, 280, 300, 315, 335, 355, 375, 400, 425, 450, 475, 500, 530, 560, 600, 630, 670, 710, 750, 800, 850, 900, 950, 1e3}");
    model.study("std3").feature("freq").set("useadvanceddisable", true);
    model.study("std3").feature("freq")
         .set("disabledphysics", new String[]{"cir/E1", "cir/F1", "cir/R3", "cir/R4", "cir2/E1", "cir2/F1", "cir2/R3", "cir2/R4"});
    model.study("std3").feature("freq").setEntry("outputmap", "acpr", "selection");
    model.study("std3").feature("freq").setEntry("outputselectionmap", "acpr", "geom1_csel1_pnt");
    model.study("std3").feature("freq").setEntry("mesh", "geom1", "mesh2");
    model.study("std3").showAutoSequences("all");

    model.sol("sol3").feature("s1").feature("d1").set("mumpsblr", true);

    model.study("std4")
         .label("\u7814\u7a76 4 - \u9891\u7387\u626b\u63cf\u81f3 fmax\uff08\u4ec5\u4e2d\u4f4e\u97f3\u626c\u58f0\u5668\uff09");
    model.study("std4").setGenPlots(false);
    model.study("std4").feature("freq")
         .set("plist", "{50, 53, 56, 60, 63, 67, 71, 75, 80, 85, 90, 95, 100, 106, 112, 118, 125, 132, 140, 150, 160, 170, 180, 190, 200, 212, 224, 236, 250, 265, 280, 300, 315, 335, 355, 375, 400, 425, 450, 475, 500, 530, 560, 600, 630, 670, 710, 750, 800, 850, 900, 950, 1e3}");
    model.study("std4").feature("freq").set("useadvanceddisable", true);
    model.study("std4").feature("freq")
         .set("disabledphysics", new String[]{"acpr/lsb2", "cir/E1", "cir/F1", "cir/R3", "cir/R4"});
    model.study("std4").feature("freq").setSolveFor("/physics/cir2", false);
    model.study("std4").feature("freq")
         .set("disabledphysics", new String[]{"acpr/lsb2", "cir/E1", "cir/F1", "cir/R3", "cir/R4", "cir2"});
    model.study("std4").feature("freq").setEntry("outputmap", "acpr", "selection");
    model.study("std4").feature("freq").setEntry("outputselectionmap", "acpr", "geom1_csel1_pnt");
    model.study("std4").feature("freq").setEntry("mesh", "geom1", "mesh2");
    model.study("std4").showAutoSequences("all");

    model.sol("sol4").feature("s1").feature("d1").set("mumpsblr", true);

    model.study("std5").label("\u7814\u7a76 5 - \u9ad8\u4e8e fmax \u7684\u5355\u4e00\u9891\u7387");
    model.study("std5").setGenPlots(false);
    model.study("std5").create("param", "Parametric");
    model.study("std5").feature("param").setIndex("pname", "A_m", 0);
    model.study("std5").feature("param").setIndex("plistarr", "", 0);
    model.study("std5").feature("param").setIndex("punit", "\u03a9", 0);
    model.study("std5").feature("param").setIndex("pname", "A_m", 0);
    model.study("std5").feature("param").setIndex("plistarr", "", 0);
    model.study("std5").feature("param").setIndex("punit", "\u03a9", 0);
    model.study("std5").feature("param").setIndex("pname", "f0", 0);
    model.study("std5").feature("param").setIndex("plistarr", "2000, 3000, 4000", 0);
    model.study("std5").feature("freq").set("plist", "f0");
    model.study("std5").feature("freq").set("useadvanceddisable", true);
    model.study("std5").feature("freq")
         .set("disabledphysics", new String[]{"cir/E1", "cir/F1", "cir/R3", "cir/R4", "cir2/E1", "cir2/F1", "cir2/R3", "cir2/R4"});
    model.study("std5").feature("freq").setEntry("outputmap", "acpr", "selection");
    model.study("std5").feature("freq").setEntry("outputselectionmap", "acpr", "sel1;geom1_csel1_pnt");
    model.study("std5").feature("freq").setEntry("mesh", "geom1", "mesh3");
    model.study("std5").showAutoSequences("all");

    model.sol("sol5").feature("s1").feature("i3").active(true);
    model.sol("sol5").feature("s1").feature("i3").feature("mg1").feature("cs").feature("dDef").set("mumpsblr", true);

    model.study().create("std6");
    model.study("std6").label("\u7814\u7a76 6 - \u6240\u6709\u9891\u7387\u5206\u6790");
    model.study("std6").setGenPlots(false);
    model.study("std6").create("ref", "StudyReference");
    model.study("std6").feature("ref").label("\u7814\u7a76 3");
    model.study("std6").feature("ref").set("studyref", "std3");
    model.study("std6").create("ref2", "StudyReference");
    model.study("std6").feature("ref2").label("\u7814\u7a76 4");
    model.study("std6").feature("ref2").set("studyref", "std4");
    model.study("std6").create("ref3", "StudyReference");
    model.study("std6").feature("ref3").label("\u7814\u7a76 5");
    model.study("std6").feature("ref3").set("studyref", "std5");
    model.study("std6").createAutoSequences("all");

    model.sol().create("sol7");
    model.sol("sol7").study("std5");
    model.sol("sol7").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol7");
    model.batch("s1").run("compute");

    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u8f66\u53a2\u54cd\u5e94");
    model.result("pg5").set("data", "none");
    model.result("pg5").set("titletype", "label");
    model.result("pg5").set("legendpos", "lowerright");
    model.result("pg5").create("oct1", "OctaveBand");
    model.result("pg5").feature("oct1").set("quantity", "bandpower");
    model.result("pg5").feature("oct1").set("markerpos", "datapoints");
    model.result("pg5").feature("oct1").set("linewidth", "preference");
    model.result("pg5").feature("oct1").set("data", "dset3");
    model.result("pg5").feature("oct1").selection().geom("geom1", 0);
    model.result("pg5").feature("oct1").selection().set(543, 544, 545);
    model.result("pg5").feature("oct1").set("quantity", "continuous");
    model.result("pg5").feature("oct1").set("linemarker", "point");
    model.result("pg5").feature("oct1").set("legend", true);
    model.result("pg5").feature("oct1").set("legendmethod", "manual");
    model.result("pg5").feature("oct1").setIndex("legends", "\u7814\u7a76 3", 0);
    model.result("pg5").feature().duplicate("oct2", "oct1");
    model.result("pg5").run();
    model.result("pg5").feature("oct2").set("data", "dset7");
    model.result("pg5").feature("oct2").set("linestyle", "dashed");
    model.result("pg5").feature("oct2").set("linecolor", "cyclereset");
    model.result("pg5").feature("oct2").set("linemarker", "asterisk");
    model.result("pg5").feature("oct2").setIndex("legends", "\u7814\u7a76 5", 0);
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result("pg6").label("\u8f66\u53a2\u54cd\u5e94 - \u626c\u58f0\u5668\u6bd4\u8f83");
    model.result("pg6").run();
    model.result("pg6").feature("oct1")
         .setIndex("legends", "\u4e2d\u4f4e\u97f3\u626c\u58f0\u5668\u548c\u9ad8\u97f3\u626c\u58f0\u5668", 0);
    model.result("pg6").run();
    model.result("pg6").feature("oct2").set("data", "dset4");
    model.result("pg6").feature("oct2").set("linecolor", "cycle");
    model.result("pg6").feature("oct2").setIndex("legends", "\u4ec5\u4e2d\u4f4e\u97f3\u626c\u58f0\u5668", 0);
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").set("data", "dset7");
    model.result("pg7").setIndex("looplevel", 1, 0);
    model.result("pg7").setIndex("looplevel", 3, 1);
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", new String[]{"acpr.Lp_t"});
    model.result("pg7").feature("surf1").set("colortable", "Rainbow");
    model.result("pg7").feature("surf1").set("colorscalemode", "linear");
    model.result("pg7").set("showlegendsunit", true);
    model.result("pg7").label("\u58f0\u538b\u7ea7 (acpr) 1");
    model.result("pg7").label("\u58f0\u538b\u7ea7 (acpr) 1");
    model.result("pg7").run();
    model.result("pg7").label("\u8868\u9762\u58f0\u538b\u7ea7");
    model.result("pg7").selection().geom("geom1", 2);
    model.result("pg7").selection().named("sel1");
    model.result("pg7").selection().geom("geom1", 2);
    model.result("pg7").selection()
         .set(1, 2, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255, 256, 258, 259, 260, 261, 262, 263, 264, 265, 266, 267, 268, 269, 270, 271, 272, 273, 274, 275, 276, 277, 278, 279, 280, 281, 282, 283, 284, 285, 286, 287, 288, 289, 290, 291, 292, 293, 294, 295, 296, 297, 298, 299, 300, 301, 302, 303, 304, 305, 306, 307, 308, 309, 310, 311, 312, 313, 314, 315, 316, 317, 318, 319, 320, 321, 322, 323, 324, 325, 326, 327, 328, 329, 330, 331, 332, 333, 334, 335, 336, 337, 338, 339, 340, 341, 342, 343, 344, 345, 346, 347, 348, 349, 350, 351, 352, 353, 354, 355, 356, 357, 358, 359, 360, 361, 362, 363, 364, 365, 366, 367, 368, 369, 370, 371, 372, 373, 374, 375, 376, 377, 378, 379, 380, 381, 382, 383, 384, 385, 386, 387, 388, 389, 390, 391, 392, 393, 394, 395, 396, 397, 398, 399, 400, 401, 402, 403, 404, 405, 406, 407, 408, 409, 410, 411, 412, 413, 414, 415, 416, 417, 418, 419, 420, 421, 422, 423, 424, 425, 426, 427, 428, 429, 430, 431, 432, 433, 434, 435, 436, 437, 438, 439, 440, 441, 442, 443, 444, 445, 446, 447, 448, 449, 450, 451, 452, 453, 454, 455, 456, 457, 458, 459, 460, 461, 462, 463, 464, 465, 466, 467, 468, 469, 470, 471, 472, 473, 474, 475, 476, 478, 479, 480, 481, 482, 483, 484, 485, 486, 487, 488, 490, 491, 492, 493, 494, 495, 496, 497, 498, 499, 500, 501, 502, 503, 504, 505, 506, 507, 508, 509, 510, 511, 512, 513, 514, 515, 516, 517, 518, 519, 520, 521, 522, 523, 524, 525, 526, 527, 528, 529, 530, 531, 532, 533, 534, 535, 536, 537, 538, 539, 540, 541, 542, 543, 544, 545, 546, 547, 548, 549, 550, 551, 552, 553, 554, 555, 556, 557, 558, 559, 560, 561, 562, 563, 564, 565, 566, 567, 568, 569, 570, 571, 572, 573, 574, 575, 576, 577, 578, 579, 580, 581, 582, 583, 584, 585, 586, 587, 588, 589, 590, 591, 592, 593, 594, 595, 596, 597, 598, 599, 600, 601, 602, 603, 604, 605, 606, 607, 608, 609, 610, 611, 612, 613, 614, 615, 616, 617, 618, 619, 620, 621, 622, 623, 624, 625, 626, 627, 628, 629, 630, 631, 632, 633, 634, 635, 636, 637, 638, 639, 640, 641, 642, 643, 644, 645, 646, 647, 648, 649, 650, 651, 652, 653, 654, 655, 656, 657, 658, 659, 660, 661, 662, 663, 664, 665, 666, 667, 668, 669, 670, 671, 672, 673, 674, 675, 676, 677, 678, 679, 680, 681, 682, 683, 684, 685, 686, 687, 688, 689, 690, 691, 692, 693, 694, 695, 696, 697, 698, 699, 700, 701, 702, 703, 704, 705, 706, 707, 708, 709, 710, 711, 713, 714, 715, 716, 717, 718, 719, 720, 721, 722, 723, 724, 725, 726, 727, 728, 729, 730, 731, 732, 733, 734, 735, 736, 737, 738, 739, 740, 741, 742, 743, 744, 745, 746, 747, 748, 749, 750, 751, 752, 753, 754, 755, 756, 757, 758, 759, 760, 761, 762, 763, 764, 765, 766, 767, 768, 769, 770, 771, 772, 773, 774, 775, 776, 777, 778, 779, 780, 781, 782, 783, 784, 785, 786, 787, 788, 789, 790, 791, 792, 793, 794, 795, 796, 797, 798, 799, 800, 801, 802, 803, 804, 805, 806, 807, 808);

    return model;
  }

  public static Model run3(Model model) {
    model.result("pg7").set("titletype", "label");
    model.result("pg7").run();
    model.result().duplicate("pg8", "pg7");
    model.result("pg8").run();
    model.result("pg8").label("\u5438\u58f0\u7cfb\u6570");
    model.result("pg8").set("looplevel", new int[]{1, 1});
    model.result("pg8").selection().named("sel6");
    model.result("pg8").selection().geom("geom1", 2);
    model.result("pg8").selection()
         .set(4, 7, 10, 64, 198, 199, 200, 201, 202, 203, 204, 205, 210, 211, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 258, 267, 268, 269, 270, 275, 276, 277, 278, 293, 294, 295, 296, 297, 298, 299, 300, 326, 327, 328, 329, 330, 331, 332, 333, 334, 335, 336, 337, 338, 339, 340, 341, 342, 343, 344, 345, 346, 347, 348, 349, 354, 355, 367, 368, 381, 382, 383, 384, 385, 386, 387, 388, 389, 390, 391, 392, 401, 402, 403, 404, 405, 406, 407, 408, 409, 410, 411, 412, 413, 414, 418, 420, 421, 422, 423, 424, 427, 428, 429, 430, 431, 432, 433, 434, 449, 450, 451, 452, 453, 454, 455, 456, 465, 466, 467, 468, 469, 470, 471, 472, 473, 474, 475, 476, 478, 561, 562, 564, 565, 568, 569, 570, 571, 572, 573, 574, 575, 576, 577, 578, 579, 594, 595, 601, 602, 603, 604, 605, 606, 607, 641, 642, 643, 644, 645, 646, 647, 671, 672, 687, 698, 699, 700, 701, 702, 703, 704, 705, 706, 707, 708, 709, 710, 711, 713, 720, 721, 722, 723, 724, 725, 726, 727, 728, 729, 730, 731, 732, 733, 734, 736, 738, 747, 748, 749, 750, 751, 752, 753, 754, 756, 757, 758, 759, 760, 761, 762, 763, 764, 765, 766, 771, 777, 778, 779, 780, 781, 782, 783, 784, 785, 786, 787, 788, 789, 790, 791, 792, 793, 794, 795, 796, 798, 799, 804, 805);
    model.result("pg8").run();
    model.result("pg8").feature("surf1").set("expr", "alpha_all");
    model.result("pg8").feature("surf1").set("unit", "1");
    model.result("pg8").feature("surf1").set("rangecoloractive", true);
    model.result("pg8").feature("surf1").set("rangecolormin", 0);
    model.result("pg8").feature("surf1").set("rangecolormax", 1);
    model.result("pg8").run();
    model.result().create("pg9", "PlotGroup3D");
    model.result("pg9").run();
    model.result("pg9").label("\u51e0\u4f55\u5f62\u72b6");
    model.result("pg9").set("titletype", "none");
    model.result("pg9").set("showlegends", false);
    model.result("pg9").create("surf1", "Surface");
    model.result("pg9").feature("surf1").set("expr", "1");
    model.result("pg9").feature("surf1").set("coloring", "uniform");
    model.result("pg9").feature("surf1").set("color", "custom");
    model.result("pg9").feature("surf1")
         .set("customcolor", new double[]{0.5176470875740051, 0.7607843279838562, 0.9176470637321472});
    model.result("pg9").feature("surf1").create("sel1", "Selection");
    model.result("pg9").feature("surf1").feature("sel1").selection().named("sel2");
    model.result("pg9").feature("surf1").feature("sel1").selection()
         .set(4, 63, 489, 495, 771, 775, 798, 801, 804, 805);
    model.result("pg9").run();
    model.result("pg9").feature("surf1").create("tran1", "Transparency");
    model.result("pg9").run();
    model.result("pg9").feature("surf1").feature("tran1").set("transparency", 0.3);
    model.result("pg9").feature("surf1").feature("tran1").set("uniformblending", 0.5);
    model.result("pg9").run();
    model.result("pg9").feature().duplicate("surf2", "surf1");
    model.result("pg9").run();
    model.result("pg9").feature("surf2")
         .set("customcolor", new double[]{0.7686274647712708, 0.4156862795352936, 0.2823529541492462});
    model.result("pg9").run();
    model.result("pg9").feature("surf2").feature("sel1").selection().named("sel3");
    model.result("pg9").run();
    model.result("pg9").feature("surf2").feature().remove("tran1");
    model.result("pg9").run();
    model.result("pg9").run();
    model.result("pg9").feature().duplicate("surf3", "surf2");
    model.result("pg9").run();
    model.result("pg9").feature("surf3")
         .set("customcolor", new double[]{0.32549020648002625, 0.3294117748737335, 0.41960784792900085});
    model.result("pg9").run();
    model.result("pg9").feature("surf3").feature("sel1").selection().named("sel4");
    model.result("pg9").run();
    model.result("pg9").feature().duplicate("surf4", "surf3");
    model.result("pg9").run();
    model.result("pg9").run();
    model.result("pg9").feature("surf4").feature("sel1").selection().named("sel5");
    model.result("pg9").run();
    model.result("pg9").feature().duplicate("surf5", "surf4");
    model.result("pg9").run();
    model.result("pg9").feature("surf5")
         .set("customcolor", new double[]{0.4745098054409027, 0.08627451211214066, 0.23137255012989044});
    model.result("pg9").run();
    model.result("pg9").feature("surf5").feature("sel1").selection().named("sel6");
    model.result("pg9").run();
    model.result("pg9").feature().duplicate("surf6", "surf5");
    model.result("pg9").run();
    model.result("pg9").feature("surf6")
         .set("customcolor", new double[]{0.7529411911964417, 0.7529411911964417, 0.7529411911964417});
    model.result("pg9").run();
    model.result("pg9").feature("surf6").feature("sel1").selection().named("sel7");
    model.result("pg9").feature("surf6").feature("sel1").selection().set(478, 712, 713);
    model.result("pg9").run();
    model.result("pg9").feature().duplicate("surf7", "surf6");
    model.result("pg9").run();
    model.result("pg9").feature("surf7")
         .set("customcolor", new double[]{0.6980392336845398, 0.13333334028720856, 0.13333334028720856});
    model.result("pg9").run();
    model.result("pg9").feature("surf7").feature("sel1").selection().named("uni1");
    model.result("pg9").run();
    model.result("pg9").feature().duplicate("surf8", "surf7");
    model.result("pg9").run();
    model.result("pg9").feature("surf8")
         .set("customcolor", new double[]{0.4117647111415863, 0.4117647111415863, 0.4117647111415863});
    model.result("pg9").run();
    model.result("pg9").feature("surf8").feature("sel1").selection().named("dif1");
    model.result("pg9").run();
    model.result("pg9").run();
    model.result().duplicate("pg10", "pg9");
    model.result("pg10").run();
    model.result("pg10").label("\u7f29\u7565\u56fe");
    model.result("pg10").set("data", "dset7");
    model.result("pg10").run();
    model.result("pg10").feature("surf5").set("expr", "acpr.Lp");
    model.result("pg10").feature("surf5").set("unit", "dB");
    model.result("pg10").feature("surf5").set("coloring", "colortable");
    model.result("pg10").feature("surf5").set("colortable", "Rainbow");
    model.result("pg10").feature("surf5").set("colorscalemode", "linear");
    model.result("pg10").run();
    model.result("pg10").run();

    model.title("\u6c7d\u8f66\u8f66\u53a2\u58f0\u5b66 - \u9891\u57df\u5206\u6790");

    model
         .description("\u672c\u6a21\u578b\u5206\u6790\u6c7d\u8f66\u8f66\u53a2\u5185\u58f0\u97f3\u7cfb\u7edf\u5728\u4f4e\u5230\u4e2d\u9891\u8303\u56f4\u5185\u7684\u6027\u80fd\u3002\u8f66\u53a2\u662f\u5178\u578b\u7684\u8f7f\u8f66\u5185\u90e8\u7a7a\u95f4\uff0c\u5373\u786c\u9876\u4e58\u7528\u8f66\u7684\u5185\u90e8\u7ed3\u6784\uff0c\u7814\u7a76\u9ea6\u514b\u98ce\u9635\u5217\u4f4d\u7f6e\u7684\u9891\u7387\u54cd\u5e94\u548c\u4f4e\u9891\u6a21\u6001\u7279\u6027\u3002\n\n\u6a21\u578b\u7531\u653e\u7f6e\u5728\u6c7d\u8f66\u5185\u90e8\u5178\u578b\u4f4d\u7f6e\u7684\u626c\u58f0\u5668\u9a71\u52a8\uff0c\u501f\u52a9\u7528\u4e8e\u901a\u7528\u4e2d\u4f4e\u97f3\u626c\u58f0\u5668\u548c\u901a\u7528\u9ad8\u97f3\u626c\u58f0\u5668\u7684\u201c\u7535\u8def\u201d\u63a5\u53e3\u6765\u5b9e\u73b0\u96c6\u603b\u7684 Thiele-Small \u8868\u793a\uff08\u7535\u6c14\u548c\u673a\u68b0\u57df\uff09\uff0c\u5e76\u4f7f\u7528\u201c\u96c6\u603b\u626c\u58f0\u5668\u8fb9\u754c\u201d\u6761\u4ef6\u5c06\u5176\u8026\u5408\u5230\u58f0\u5b66\u57df\u3002\u6b64\u5916\uff0c\u8fd8\u4f7f\u7528\u201c\u5185\u90e8\u7a7f\u5b54\u677f\u201d\u6761\u4ef6\u6a21\u62df\u626c\u58f0\u5668\u524d\u65b9\u7684\u4fdd\u62a4\u7f51\u7f69\u3002\n\n\u672c\u4f8b\u4f7f\u7528\u901a\u7528\u6570\u636e\u6765\u5b9a\u4e49\u8fb9\u754c\u6761\u4ef6\uff0c\u4f7f\u7528\u6052\u5b9a\u5438\u6536\u7cfb\u6570\u6765\u6a21\u62df\u8f66\u7a97\u3001\u4eea\u8868\u76d8\u548c\u8f66\u95e8\uff0c\u4f7f\u7528\u590d\u503c\u8868\u9762\u963b\u6297\u6765\u8868\u793a\u76ae\u9769\u5ea7\u6905\uff0c\u5e76\u4f7f\u7528\u201c\u963b\u6297\u201d\u8fb9\u754c\u6761\u4ef6\u4e2d\u7684\u201c\u591a\u5b54\u5c42\u201d\u9009\u9879\u6765\u5b9a\u4e49\u8f66\u9876\u88c5\u9970\u548c\u5730\u6bef\u5730\u677f\u3002\u591a\u5b54\u6750\u6599\u7684\u5efa\u6a21\u4f9d\u6258\u4e8e\u534a\u7ecf\u9a8c\u7684 Delany-Bazley-Miki \u6a21\u578b\uff0c\u5176\u4e2d\u6ce1\u6cab\u548c\u7ea4\u7ef4\u6750\u6599\u7684\u7279\u6027\u5206\u522b\u901a\u8fc7 Qunli \u548c Miki \u5e38\u6570\u6765\u63cf\u8ff0\u3002\n\n\u6a21\u578b\u76f8\u5bf9\u5bb9\u6613\u5728\u6807\u51c6\u53f0\u5f0f\u8ba1\u7b97\u673a\u4e0a\u8fdb\u884c\u9891\u57df\u6c42\u89e3\uff0c\u5176\u4e2d\u4f7f\u7528\u76f4\u63a5\u6c42\u89e3\u5668\u6765\u6c42\u89e3 1\u00a0kHz \u4ee5\u4e0b\u7684\u6a21\u578b\u3002\u5728\u8f83\u9ad8\u9891\u7387\u4e0b\uff0c\u5219\u901a\u8fc7\u91c7\u7528\u590d\u6742\u4f4d\u79fb\u62c9\u666e\u62c9\u65af\u65b9\u6cd5 (CSL) \u7684\u8fed\u4ee3\u6cd5\u6765\u6c42\u89e3\u6a21\u578b\u3002\n\n\u9664\u201c\u58f0\u5b66\u6a21\u5757\u201d\u4ee5\u5916\uff0c\u672c\u6a21\u578b\u8fd8\u9700\u8981\u201cAC/DC \u6a21\u5757\u201d\u548c\u201cCAD \u5bfc\u5165\u6a21\u5757\u201d\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol8").clearSolutionData();
    model.sol("sol9").clearSolutionData();
    model.sol("sol10").clearSolutionData();

    model.label("car_cabin_acoustics.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
