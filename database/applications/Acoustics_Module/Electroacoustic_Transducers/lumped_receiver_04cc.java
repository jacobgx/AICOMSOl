/*
 * lumped_receiver_04cc.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:39 by COMSOL 6.3.0.290. */
public class lumped_receiver_04cc {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Electroacoustic_Transducers");

    model.param().set("L", "49[mm]", "\u7ba1\u957f\u5ea6");
    model.param().set("a", "0.5[mm]", "\u7ba1\u534a\u5f84");
    model.param().set("a_mic", "0.5*0.25[in]", "\u9ea6\u514b\u98ce\u534a\u5f84");
    model.param().set("a_cpl", "4.7[mm]", "\u8026\u5408\u5668\u534a\u5f84");
    model.param().set("Vrms", "0.17[V]", "\u5747\u65b9\u6839\u9a71\u52a8\u7535\u538b");
    model.param().set("V0", "sqrt(2)*Vrms", "\u5cf0\u503c\u9a71\u52a8\u7535\u538b");
    model.param().set("Cmic", "6[mm^3]/(1.4*p0)", "\u9ea6\u514b\u98ce\u58f0\u987a");
    model.param().set("Lmic", "2500[kg/m^4]", "\u9ea6\u514b\u98ce\u58f0\u8d28\u91cf");
    model.param().set("Rmic", "1200e6[N*s/m^5]", "\u9ea6\u514b\u98ce\u58f0\u963b");
    model.param().set("T0", "20[degC]", "\u73af\u5883\u6e29\u5ea6");
    model.param().set("p0", "1[atm]", "\u5927\u6c14\u538b");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);

    model.func().create("int1", "Interpolation");
    model.func("int1").set("source", "file");
    model.func("int1").set("importedname", "lumped_receiver_04cc_measured_data.txt");
    model.func("int1").set("filecolumns", 17);
    model.func("int1")
         .set("fileheaders", new String[]{"Frequency", "Re(VoltageSensitivity)", "Im(VoltageSensitivity)", "Re(InputImpedance)", "Im(InputImpedance)", "Re(VoltageSensitivity)", "Im(VoltageSensitivity)", "Re(InputImpedance)", "Im(InputImpedance)", "Re(VoltageSensitivity)", 
         "Im(VoltageSensitivity)", "Re(InputImpedance)", "Im(InputImpedance)", "Re(VoltageSensitivity)", "Im(VoltageSensitivity)", "Re(InputImpedance)", "Im(InputImpedance)"});
    model.func("int1")
         .set("columnKeys", new String[]{"col1", "col2", "col3", "col4", "col5", "col6", "col7", "col8", "col9", "col10", 
         "col11", "col12", "col13", "col14", "col15", "col16", "col17"});
    model.func("int1")
         .set("columnType", new String[]{"col1", "arg", "col2", "value", "col3", "value", "col4", "value", "col5", "value", 
         "col6", "none", "col7", "none", "col8", "none", "col9", "none", "col10", "none", 
         "col11", "none", "col12", "none", "col13", "none", "col14", "none", "col15", "none", 
         "col16", "none", "col17", "none"});
    model.func("int1")
         .set("funcnames", new String[]{"col1", "int1", "col2", "preal", "col3", "pimag", "col4", "Zreal", "col5", "Zimag", 
         "col6", "int1", "col7", "int1", "col8", "int1", "col9", "int1", "col10", "int1", 
         "col11", "int1", "col12", "int1", "col13", "int1", "col14", "int1", "col15", "int1", 
         "col16", "int1", "col17", "int1"});
    model.func("int1").set("interp", "piecewisecubic");
    model.func("int1").set("fununit", new String[]{"\u03a9", "\u03a9", "\u03a9", "\u03a9"});
    model.func("int1").set("argunit", new String[]{"Hz"});
    model.func("int1")
         .set("filename", "C:\\tmp\\241111\\patch\\startdir_testusr\\models_xs_zh_CN\\aco.SuiteModelsAco\\cstesttmpdir\\csjavabridge40731\\lumped_receiver_04cc_measured_data.txt");
    model.func("int1").importData();

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").geom("geom1").geomRep("comsol");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "0.5[mm]");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "49[mm]");
    model.component("comp1").geom("geom1").feature("cyl1").set("selresult", true);
    model.component("comp1").geom("geom1").create("cyl2", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl2").set("r", "4.72[mm]");
    model.component("comp1").geom("geom1").feature("cyl2").set("h", "5.7[mm]");
    model.component("comp1").geom("geom1").feature("cyl2").set("pos", new String[]{"0", "0", "49[mm]"});
    model.component("comp1").geom("geom1").feature("cyl2").set("selresult", true);
    model.component("comp1").geom("geom1").create("cyl3", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl3").set("r", "3.175[mm]");
    model.component("comp1").geom("geom1").feature("cyl3").set("h", "0.5[mm]");
    model.component("comp1").geom("geom1").feature("cyl3").set("pos", new String[]{"0", "0", "54.2[mm]"});
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("cyl2");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("cyl3");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").label("\u6cd5\u5411\u52a0\u901f\u5ea6");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("fin(1)", 10);
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").label("\u963b\u6297");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("fin(1)", 7);
    model.component("comp1").geom("geom1").run();

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

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop1").selection().set(7);

    model.component("comp1").physics().create("cir", "Circuit", "geom1");
    model.component("comp1").physics("cir").create("ED23146", "SubCircuitBlock", -1);
    model.component("comp1").physics("cir").feature("ED23146").create("CBACK", "Capacitor", -1);
    model.component("comp1").physics("cir").feature("ED23146").create("CEFF", "Capacitor", -1);
    model.component("comp1").physics("cir").feature("ED23146").create("CFRONT", "Capacitor", -1);
    model.component("comp1").physics("cir").feature("ED23146").create("CGAP", "Capacitor", -1);
    model.component("comp1").physics("cir").feature("ED23146").create("CMECH2", "Capacitor", -1);
    model.component("comp1").physics("cir").feature("ED23146").create("G1_1", "VoltageCurrentSource", -1);
    model.component("comp1").physics("cir").feature("ED23146").create("G1_2", "VoltageCurrentSource", -1);
    model.component("comp1").physics("cir").feature("ED23146").create("LEFF", "Inductor", -1);
    model.component("comp1").physics("cir").feature("ED23146").create("LMECH2", "Inductor", -1);
    model.component("comp1").physics("cir").feature("ED23146").create("LPORT", "Inductor", -1);
    model.component("comp1").physics("cir").feature("ED23146").create("RDC", "Resistor", -1);
    model.component("comp1").physics("cir").feature("ED23146").create("RPIERCE", "Resistor", -1);
    model.component("comp1").physics("cir").feature("ED23146").create("RPORT", "Resistor", -1);
    model.component("comp1").physics("cir").feature("ED23146").create("RKARM", "Resistor", -1);
    model.component("comp1").physics().create("acpr", "PressureAcoustics", "geom1");

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

    model.component("comp1").cpl("intop1").set("opname", "intop_mic");

    model.component("comp1").physics("cir").feature("ED23146")
         .set("Connections", new String[][]{{"P1"}, {"N1"}, {"P2"}, {"N2"}});
    model.component("comp1").physics("cir").feature("ED23146").label("\u5b50\u7535\u8def\u5b9a\u4e49 ED23146");
    model.component("comp1").physics("cir").feature("ED23146").feature("CBACK").set("C", 3.07E-13);
    model.component("comp1").physics("cir").feature("ED23146").feature("CBACK")
         .set("Connections", new String[][]{{"N0010"}, {"N0005"}});
    model.component("comp1").physics("cir").feature("ED23146").feature("CBACK").label("\u7535\u5bb9\u5668 CBACK");
    model.component("comp1").physics("cir").feature("ED23146").feature("CEFF").set("C", 8.18E-14);
    model.component("comp1").physics("cir").feature("ED23146").feature("CEFF")
         .set("Connections", new String[][]{{"N0035"}, {"N0030"}});
    model.component("comp1").physics("cir").feature("ED23146").feature("CEFF").label("\u7535\u5bb9\u5668 CEFF");
    model.component("comp1").physics("cir").feature("ED23146").feature("CFRONT").set("C", 4.55E-14);
    model.component("comp1").physics("cir").feature("ED23146").feature("CFRONT")
         .set("Connections", new String[][]{{"N0010"}, {"N2"}});
    model.component("comp1").physics("cir").feature("ED23146").feature("CFRONT").label("\u7535\u5bb9\u5668 CFRONT");
    model.component("comp1").physics("cir").feature("ED23146").feature("CGAP").set("C", 1.02E-13);
    model.component("comp1").physics("cir").feature("ED23146").feature("CGAP")
         .set("Connections", new String[][]{{"N0025"}, {"N2"}});
    model.component("comp1").physics("cir").feature("ED23146").feature("CGAP").label("\u7535\u5bb9\u5668 CGAP");
    model.component("comp1").physics("cir").feature("ED23146").feature("CMECH2").set("C", 2.89E-14);
    model.component("comp1").physics("cir").feature("ED23146").feature("CMECH2")
         .set("Connections", new String[][]{{"N0035"}, {"N2"}});
    model.component("comp1").physics("cir").feature("ED23146").feature("CMECH2").label("\u7535\u5bb9\u5668 CMECH2");
    model.component("comp1").physics("cir").feature("ED23146").feature("G1_1")
         .set("Connections", new String[][]{{"N0020"}, {"N1"}, {"N0015"}, {"N2"}});
    model.component("comp1").physics("cir").feature("ED23146").feature("G1_1").set("gain", -1.8E-6);
    model.component("comp1").physics("cir").feature("ED23146").feature("G1_1")
         .label("\u7535\u538b\u63a7\u5236\u7684\u7535\u6d41\u6e90 G1_1");
    model.component("comp1").physics("cir").feature("ED23146").feature("G1_2")
         .set("Connections", new String[][]{{"N0015"}, {"N2"}, {"N0020"}, {"N1"}});
    model.component("comp1").physics("cir").feature("ED23146").feature("G1_2").set("gain", 1.8E-6);
    model.component("comp1").physics("cir").feature("ED23146").feature("G1_2")
         .label("\u7535\u538b\u63a7\u5236\u7684\u7535\u6d41\u6e90 G1_2");
    model.component("comp1").physics("cir").feature("ED23146").feature("LEFF").set("L", "50200.0");
    model.component("comp1").physics("cir").feature("ED23146").feature("LEFF")
         .set("Connections", new String[][]{{"N0025"}, {"N0030"}});
    model.component("comp1").physics("cir").feature("ED23146").feature("LEFF").label("\u7535\u611f\u5668 LEFF");
    model.component("comp1").physics("cir").feature("ED23146").feature("LMECH2").set("L", "8070.0");
    model.component("comp1").physics("cir").feature("ED23146").feature("LMECH2")
         .set("Connections", new String[][]{{"N0035"}, {"N0005"}});
    model.component("comp1").physics("cir").feature("ED23146").feature("LMECH2").label("\u7535\u611f\u5668 LMECH2");
    model.component("comp1").physics("cir").feature("ED23146").feature("LPORT").set("L", "4880.0");
    model.component("comp1").physics("cir").feature("ED23146").feature("LPORT")
         .set("Connections", new String[][]{{"N0040"}, {"P2"}});
    model.component("comp1").physics("cir").feature("ED23146").feature("LPORT").label("\u7535\u611f\u5668 LPORT");
    model.component("comp1").physics("cir").feature("ED23146").feature("RDC").set("R", "89.0");
    model.component("comp1").physics("cir").feature("ED23146").feature("RDC")
         .set("Connections", new String[][]{{"P1"}, {"N0020"}});
    model.component("comp1").physics("cir").feature("ED23146").feature("RDC").label("\u7535\u963b RDC");
    model.component("comp1").physics("cir").feature("ED23146").feature("RPIERCE").set("R", 1.57E10);
    model.component("comp1").physics("cir").feature("ED23146").feature("RPIERCE")
         .set("Connections", new String[][]{{"N0005"}, {"N2"}});
    model.component("comp1").physics("cir").feature("ED23146").feature("RPIERCE").label("\u7535\u963b RPIERCE");
    model.component("comp1").physics("cir").feature("ED23146").feature("RPORT").set("R", "6.3E7");
    model.component("comp1").physics("cir").feature("ED23146").feature("RPORT")
         .set("Connections", new String[][]{{"N0040"}, {"N0010"}});
    model.component("comp1").physics("cir").feature("ED23146").feature("RPORT").label("\u7535\u963b RPORT");
    model.component("comp1").physics("cir").feature("ED23146").feature("RKARM").set("R", "1.0");
    model.component("comp1").physics("cir").feature("ED23146").feature("RKARM")
         .set("Connections", new String[][]{{"N0025"}, {"N0015"}});
    model.component("comp1").physics("cir").feature("ED23146").feature("RKARM").label("\u7535\u963b RKARM");

    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");

    model.component("comp1").physics("cir").feature("ED23146").feature("RKARM")
         .set("R", "1/(4.85e-11[1/ohm]*sqrt(i*2*pi*freq[1/Hz]))");
    model.component("comp1").physics("cir").create("V1", "VoltageSource", -1);
    model.component("comp1").physics("cir").feature("V1").setIndex("Connections", "p1", 0, 0);
    model.component("comp1").physics("cir").feature("V1").setIndex("Connections", 0, 1, 0);
    model.component("comp1").physics("cir").feature("V1").set("value", "V0");
    model.component("comp1").physics("cir").create("X1", "SubCircuit", -1);
    model.component("comp1").physics("cir").feature("X1").set("subCircuitName", "ED23146");
    model.component("comp1").physics("cir").feature("X1").setIndex("Connections", "p1", 0, 0);
    model.component("comp1").physics("cir").feature("X1").setIndex("Connections", 0, 1, 0);
    model.component("comp1").physics("cir").feature("X1").setIndex("Connections", "p2", 2, 0);
    model.component("comp1").physics("cir").feature("X1").setIndex("Connections", 0, 3, 0);
    model.component("comp1").physics("cir").create("IvsU1", "ModelDeviceIV", -1);
    model.component("comp1").physics("cir").feature("IvsU1").setIndex("Connections", "p2", 0, 0);
    model.component("comp1").physics("cir").feature("IvsU1").setIndex("Connections", 0, 1, 0);
    model.component("comp1").physics("acpr").feature("fpam1").set("minput_temperature", "T0");
    model.component("comp1").physics("acpr").feature("fpam1").set("minput_pressure", "p0");
    model.component("comp1").physics("acpr").create("nra1", "NarrowRegionAcousticsModel", 3);
    model.component("comp1").physics("acpr").feature("nra1").selection().set(2);
    model.component("comp1").physics("acpr").feature("nra1").set("minput_temperature", "T0");
    model.component("comp1").physics("acpr").feature("nra1").set("minput_pressure", "p0");
    model.component("comp1").physics("acpr").feature("nra1").set("DuctType", "CircularDuct");
    model.component("comp1").physics("acpr").feature("nra1").set("a", "a");
    model.component("comp1").physics("acpr").create("nra2", "NarrowRegionAcousticsModel", 3);
    model.component("comp1").physics("acpr").feature("nra2").selection().set(1);
    model.component("comp1").physics("acpr").feature("nra2").set("minput_temperature", "T0");
    model.component("comp1").physics("acpr").feature("nra2").set("minput_pressure", "p0");
    model.component("comp1").physics("acpr").feature("nra2").set("DuctType", "CircularDuct");
    model.component("comp1").physics("acpr").feature("nra2").set("a", "a_cpl");
    model.component("comp1").physics("acpr").create("imp1", "Impedance", 2);
    model.component("comp1").physics("acpr").feature("imp1").selection().set(7);
    model.component("comp1").physics("acpr").feature("imp1").set("ImpedanceModel", "RCL");
    model.component("comp1").physics("acpr").feature("imp1").set("Rac", "Rmic");
    model.component("comp1").physics("acpr").feature("imp1").set("Cac", "Cmic");
    model.component("comp1").physics("acpr").feature("imp1").set("Lac", "Lmic");
    model.component("comp1").physics("acpr").create("lport1", "LumpedPort", 2);
    model.component("comp1").physics("acpr").feature("lport1").selection().set(10);
    model.component("comp1").physics("acpr").feature("lport1").set("ConnectionType", "Circuit");
    model.component("comp1").physics("cir").feature("IvsU1").set("V_src", "root.comp1.acpr.lport1.V_cir");

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(3, 11);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "3*a");
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", "0.1[mm]");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().set(11);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", "a");
    model.component("comp1").mesh("mesh1").run("ftri1");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 15);
    model.component("comp1").mesh("mesh1").run("swe1");
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76 1 - \u72ed\u7a84\u533a\u57df");
    model.study("std1").setGenPlots(false);
    model.study("std1").feature("freq").set("plist", "10^{range(2,2.2/199,4.2)}");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.study().create("std2");
    model.study("std2").create("freq", "Frequency");
    model.study("std2").feature("freq").setSolveFor("/physics/cir", true);
    model.study("std2").feature("freq").setSolveFor("/physics/acpr", true);
    model.study("std2").label("\u7814\u7a76 2 - \u65e0\u635f\u58f0\u5b66");
    model.study("std2").setGenPlots(false);
    model.study("std2").feature("freq").set("plist", "10^{range(2,2.2/199,4.2)}");
    model.study("std2").feature("freq").set("useadvanceddisable", true);
    model.study("std2").feature("freq").set("disabledphysics", new String[]{"acpr/nra1", "acpr/nra2"});
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").label("\u9ea6\u514b\u98ce\u54cd\u5e94");
    model.result("pg1").set("titletype", "manual");
    model.result("pg1")
         .set("title", "\u8026\u5408\u5668\u54cd\u5e94\uff080.4cc \u8026\u5408\u5668\u4e0a\u7684 50mm/1mm\u00d8 \u8033\u6a21\u7ba1\uff09");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "\u58f0\u538b\u7ea7\uff08dB \u76f8\u5bf9\u4e8e 1V\uff09");
    model.result("pg1").set("legendpos", "lowerleft");
    model.result("pg1").create("glob1", "Global");
    model.result("pg1").feature("glob1").set("markerpos", "datapoints");
    model.result("pg1").feature("glob1").set("linewidth", "preference");
    model.result("pg1").feature("glob1").setIndex("expr", "20*log10(abs(intop_mic(p)/intop_mic(1))/V0)", 0);
    model.result("pg1").feature("glob1").setIndex("descr", "\u5168\u6a21\u578b", 0);
    model.result("pg1").feature("glob1").setIndex("expr", "20*log10(abs(preal(freq)+i*pimag(freq)))", 1);
    model.result("pg1").feature("glob1").setIndex("descr", "Knowles \u6d4b\u91cf\u6570\u636e", 1);
    model.result("pg1").run();
    model.result("pg1").create("glob2", "Global");
    model.result("pg1").feature("glob2").set("markerpos", "datapoints");
    model.result("pg1").feature("glob2").set("linewidth", "preference");
    model.result("pg1").feature("glob2").set("data", "dset2");
    model.result("pg1").feature("glob2").setIndex("expr", "20*log10(abs(intop_mic(p)/intop_mic(1))/V0)", 0);
    model.result("pg1").feature("glob2").setIndex("descr", "\u4e0d\u542b\u58f0\u635f\u8017\u7684\u6a21\u578b", 0);
    model.result("pg1").run();
    model.result("pg1").feature("glob2").set("linestyle", "dotted");
    model.result("pg1").set("xlog", true);
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u7535\u8f93\u5165\u963b\u6297");
    model.result("pg2").set("titletype", "label");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "Z_in (Ohm)");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg2").set("legendpos", "upperleft");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("linewidth", "preference");
    model.result("pg2").feature("glob1").setIndex("expr", "real(-cir.V1.v/cir.V1.i)", 0);
    model.result("pg2").feature("glob1").setIndex("descr", "real(Zin)\uff1a\u6a21\u578b\u7ed3\u679c", 0);
    model.result("pg2").feature("glob1").setIndex("expr", "imag(-cir.V1.v/cir.V1.i)", 1);
    model.result("pg2").feature("glob1").setIndex("descr", "imag(Zin)\uff1a\u6a21\u578b\u7ed3\u679c", 1);
    model.result("pg2").feature("glob1").setIndex("expr", "Zreal(freq)", 2);
    model.result("pg2").feature("glob1").setIndex("unit", "", 2);
    model.result("pg2").feature("glob1").setIndex("descr", "real(Zin)\uff1aKnowles \u6d4b\u91cf\u6570\u636e", 2);
    model.result("pg2").feature("glob1").setIndex("expr", "Zimag(freq)", 3);
    model.result("pg2").feature("glob1").setIndex("unit", "", 3);
    model.result("pg2").feature("glob1").setIndex("descr", "imag(Zin)\uff1aKnowles \u6d4b\u91cf\u6570\u636e", 3);
    model.result("pg2").run();
    model.result("pg2").set("xlog", true);
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").setIndex("looplevel", 200, 0);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg3").feature("surf1").set("colortable", "Wave");
    model.result("pg3").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").label("\u58f0\u538b (acpr)");
    model.result("pg3").label("\u58f0\u538b (acpr)");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").setIndex("looplevel", 200, 0);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"acpr.Lp_t"});
    model.result("pg4").feature("surf1").set("colortable", "Rainbow");
    model.result("pg4").feature("surf1").set("colorscalemode", "linear");
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").label("\u58f0\u538b\u7ea7 (acpr)");
    model.result("pg4").label("\u58f0\u538b\u7ea7 (acpr)");
    model.result("pg4").run();
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 99, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 137, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 151, 0);
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 99, 0);
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 137, 0);
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 151, 0);
    model.result("pg4").run();
    model.result("pg1").run();

    model
         .title("\u4e0e\u6d4b\u8bd5\u88c5\u7f6e\uff08\u542b 0.4cc \u8026\u5408\u5668\uff09\u76f8\u8fde\u7684\u63a5\u6536\u5668\u96c6\u603b\u6a21\u578b");

    model
         .description("\u5728\u672c\u4f8b\u4e2d\uff0cKnowles ED23146 \u63a5\u6536\u5668\uff08\u5fae\u578b\u626c\u58f0\u5668\uff09\u8fde\u63a5\u5230\u4e00\u4e2a\u6d4b\u8bd5\u88c5\u7f6e\uff0c\u8be5\u88c5\u7f6e\u7531 50\u00a0mm\uff08\u76f4\u5f84\u4e3a 1\u00a0mm\uff09\u7684\u8033\u6a21\u7ba1\u548c 0.4cc \u8026\u5408\u5668\u7ec4\u6210\u3002\u63a5\u6536\u5668\u901a\u8fc7\u96c6\u603b SPICE \u7f51\u7edc\u5efa\u6a21\uff0c\u5e76\u8fde\u63a5\u5230\u7ba1\u5165\u53e3\u5904\u7684\u6709\u9650\u5143\u57df\u3002\u672c\u4f8b\u5c06\u8026\u5408\u5668\u4e2d\u6d4b\u91cf\u9ea6\u514b\u98ce\u7684\u54cd\u5e94\u548c\u63a5\u6536\u5668\u7684\u7535\u8f93\u5165\u963b\u6297\u4e0e\u6d4b\u91cf\u7ed3\u679c\u8fdb\u884c\u6bd4\u8f83\u3002\u5176\u4e2d\u4f7f\u7528\u201c\u538b\u529b\u58f0\u5b66\uff0c\u9891\u57df\u201d\u7269\u7406\u573a\u63a5\u53e3\u4e2d\u7684\u201c\u72ed\u7a84\u533a\u57df\u58f0\u5b66\u201d\u7279\u5f81\u6765\u5305\u542b\u72ed\u957f\u7ba1\u4e2d\u7684\u635f\u8017\u3002\n\n\u672c\u4f8b\u9700\u8981\u201c\u58f0\u5b66\u6a21\u5757\u201d\u548c\u201cAC/DC \u6a21\u5757\u201d\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("lumped_receiver_04cc.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
