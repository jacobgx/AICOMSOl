/*
 * microstrip_patch_antenna_inset.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:30 by COMSOL 6.3.0.290. */
public class microstrip_patch_antenna_inset {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\RF_Module\\Antenna_Arrays");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("emw", "ElectromagneticWaves", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").set("solnum", "auto");
    model.study("std1").feature("freq").set("notsolnum", "auto");
    model.study("std1").feature("freq").set("outputmap", new String[]{});
    model.study("std1").feature("freq").set("ngenAUX", "1");
    model.study("std1").feature("freq").set("goalngenAUX", "1");
    model.study("std1").feature("freq").set("ngenAUX", "1");
    model.study("std1").feature("freq").set("goalngenAUX", "1");
    model.study("std1").feature("freq").setSolveFor("/physics/emw", true);
    model.study("std1").feature("freq").set("plist", "1.575[GHz]");

    model.param().set("d", "60[mil]");
    model.param().descr("d", "\u57fa\u677f\u539a\u5ea6");
    model.param().set("w_line", "3.2[mm]");
    model.param().descr("w_line", "50 \u6b27\u59c6\u7ebf\u5bbd");
    model.param().set("w_patch", "53[mm]");
    model.param().descr("w_patch", "\u8d34\u7247\u5bbd\u5ea6");
    model.param().set("l_patch", "52[mm]");
    model.param().descr("l_patch", "\u8d34\u7247\u957f\u5ea6");
    model.param().set("w_stub", "7[mm]");
    model.param().descr("w_stub", "\u8c03\u8c10\u77ed\u622a\u7ebf\u5bbd\u5ea6");
    model.param().set("l_stub", "15.5[mm]");
    model.param().descr("l_stub", "\u8c03\u8c10\u77ed\u622a\u7ebf\u957f\u5ea6");
    model.param().set("w_sub", "100[mm]");
    model.param().descr("w_sub", "\u57fa\u677f\u5bbd\u5ea6");
    model.param().set("l_sub", "100[mm]");
    model.param().descr("l_sub", "\u57fa\u677f\u957f\u5ea6");
    model.param().set("freq_min", "1.545[GHz]");
    model.param().descr("freq_min", "\u6700\u5c0f\u9891\u7387");
    model.param().set("freq_max", "1.605[GHz]");
    model.param().descr("freq_max", "\u6700\u5927\u9891\u7387");
    model.param().set("lda_min", "c_const/freq_max");
    model.param().descr("lda_min", "\u6700\u5c0f\u6ce2\u957f");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").label("\u57fa\u677f");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"w_sub", "l_sub", "d"});
    model.component("comp1").geom("geom1").feature("blk1").set("base", "center");
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").label("\u8d34\u7247");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new String[]{"w_patch", "l_patch", "1"});
    model.component("comp1").geom("geom1").feature("blk2").setIndex("size", "d", 2);
    model.component("comp1").geom("geom1").feature("blk2").set("base", "center");
    model.component("comp1").geom("geom1").run("blk2");
    model.component("comp1").geom("geom1").create("blk3", "Block");
    model.component("comp1").geom("geom1").feature("blk3").label("\u77ed\u622a\u7ebf");
    model.component("comp1").geom("geom1").feature("blk3").set("size", new String[]{"w_stub", "l_stub", "1"});
    model.component("comp1").geom("geom1").feature("blk3").setIndex("size", "d", 2);
    model.component("comp1").geom("geom1").feature("blk3").set("base", "center");
    model.component("comp1").geom("geom1").feature("blk3").set("pos", new String[]{"w_stub/2+w_line/2", "0", "0"});
    model.component("comp1").geom("geom1").feature("blk3").setIndex("pos", "l_stub/2-l_patch/2", 1);
    model.component("comp1").geom("geom1").run("blk3");
    model.component("comp1").geom("geom1").create("copy1", "Copy");
    model.component("comp1").geom("geom1").feature("copy1").selection("input").set("blk3");
    model.component("comp1").geom("geom1").feature("copy1").set("displx", "-w_stub-w_line");
    model.component("comp1").geom("geom1").run("copy1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("blk2");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("blk3", "copy1");
    model.component("comp1").geom("geom1").run("dif1");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").create("sph1", "Sphere");
    model.component("comp1").geom("geom1").feature("sph1").set("r", "l_sub");
    model.component("comp1").geom("geom1").feature("sph1").setIndex("layer", "l_sub/5", 0);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").label("\u96c6\u603b\u7aef\u53e3");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").set(26);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u57fa\u677f");
    model.component("comp1").selection("sel2").set(6, 7);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("PML");
    model.component("comp1").selection("sel3").set(1, 2, 3, 4, 8, 9, 10, 11);
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").label("\u7a7a\u6c14");
    model.component("comp1").selection("sel4").set(5);
    model.component("comp1").selection().create("adj1", "Adjacent");
    model.component("comp1").selection("adj1").label("PML\uff0c\u5916\u90e8\u8fb9\u754c");
    model.component("comp1").selection("adj1").set("input", new String[]{"sel3"});
    model.component("comp1").selection().create("adj2", "Adjacent");
    model.component("comp1").selection("adj2").label("\u7a7a\u6c14\uff0c\u5916\u90e8\u8fb9\u754c");
    model.component("comp1").selection("adj2").set("input", new String[]{"sel4"});
    model.component("comp1").selection().create("int1", "Intersection");
    model.component("comp1").selection("int1").label("PML\uff0c\u5185\u90e8\u8fb9\u754c");
    model.component("comp1").selection("int1").set("entitydim", 2);
    model.component("comp1").selection("int1").set("input", new String[]{"adj1", "adj2"});
    model.component("comp1").selection().create("adj3", "Adjacent");
    model.component("comp1").selection("adj3").label("\u57fa\u677f\u8fb9\u754c");
    model.component("comp1").selection("adj3").set("input", new String[]{"sel2"});

    model.component("comp1").coordSystem().create("pml1", "PML");
    model.component("comp1").coordSystem("pml1").selection().named("sel3");
    model.component("comp1").coordSystem("pml1").set("ScalingType", "Spherical");

    model.component("comp1").view("view1").hideEntities().create("hide1");
    model.component("comp1").view("view1").hideEntities("hide1").set(2, 9);
    model.component("comp1").view("view1").hideEntities().create("hide2");
    model.component("comp1").view("view1").hideEntities("hide2").geom("geom1", 2);
    model.component("comp1").view("view1").hideEntities("hide2").set(10, 33);

    model.component("comp1").physics("emw").create("pec2", "PerfectElectricConductor", 2);
    model.component("comp1").physics("emw").feature("pec2").selection().set(15, 20, 21);
    model.component("comp1").physics("emw").create("lport1", "LumpedPort", 2);
    model.component("comp1").physics("emw").feature("lport1").selection().named("sel1");
    model.component("comp1").physics("emw").create("ffd1", "FarFieldDomain", 3);

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
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u57fa\u677f");
    model.component("comp1").material("mat2").selection().named("sel2");
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermittivity", new String[]{"3.38"});
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("electricconductivity", new String[]{"0"});

    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u7535\u573a (emw)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature().create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").label("\u591a\u5207\u9762");
    model.result("pg1").feature("mslc1").set("smooth", "internal");
    model.result("pg1").feature("mslc1").set("data", "parent");
    model.result("pg1").feature("mslc1").feature().create("filt1", "Filter");
    model.result("pg1").feature("mslc1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").label("S \u53c2\u6570 (emw)");
    model.result().numerical("gev1").set("data", "dset1");
    model.result().numerical("gev1").set("expr", new String[]{"emw.S11dB"});
    model.result().table().create("tbl1", "Table");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").run();
    model.result().numerical("gev1").setResult();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").label("\u7535\u573a, \u5bf9\u6570 (emw)");

    model.component("comp1").measure().selection().geom(2);
    model.component("comp1").measure().selection().set(15, 20, 21);
    model.component("comp1").measure().selection().geom(2);
    model.component("comp1").measure().selection().set(5, 6, 7, 8, 30, 31, 36, 41);

    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "emw.normE");
    model.result("pg2").feature("surf1").create("sel1", "Selection");
    model.result("pg2").feature("surf1").feature("sel1").selection().set(15, 20, 21);
    model.result("pg2").feature("surf1").set("colortable", "Dipole");
    model.result("pg2").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg2").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg2").feature("surf1").feature("mtrl1").set("appearance", "custom");
    model.result("pg2").feature("surf1").feature("mtrl1").set("family", "chrome");
    model.result("pg2").feature("surf1").set("expr", "1");
    model.result("pg2").create("mslc1", "Multislice");
    model.result("pg2").feature("mslc1").set("expr", "emw.normE");
    model.result("pg2").feature("mslc1").create("sel1", "Selection");
    model.result("pg2").feature("mslc1").feature("sel1").selection().set(5);
    model.result("pg2").feature("mslc1").set("colortable", "Prism");
    model.result("pg2").feature("mslc1").set("colorscalemode", "logarithmic");
    model.result("pg2").feature("mslc1").set("colortabletype", "discrete");
    model.result("pg2").feature("mslc1").set("bandcount", 20);
    model.result("pg2").feature("mslc1").create("tran1", "Transparency");
    model.result("pg2").feature("mslc1").feature("tran1").set("transparency", 0.5);
    model.result("pg2").feature("mslc1").set("znumber", "0");
    model.result("pg2").create("surf2", "Surface");
    model.result("pg2").feature("surf2").set("expr", "emw.normE");
    model.result("pg2").feature("surf2").create("sel1", "Selection");
    model.result("pg2").feature("surf2").feature("sel1").selection()
         .set(13, 14, 16, 17, 18, 19, 22, 23, 24, 25, 26, 43, 44, 45, 46, 47, 48);
    model.result("pg2").feature("surf2").set("colortable", "Dipole");
    model.result("pg2").feature("surf2").set("colorscalemode", "logarithmic");
    model.result("pg2").feature("surf2").create("tran1", "Transparency");
    model.result("pg2").feature("surf2").feature("tran1").set("transparency", 0.7);

    model.component("comp1").view().create("view2", "geom1");
    model.component("comp1").view("view2").camera()
         .set("position", new double[]{-406.82144828464675, -542.428615404212, 406.82144828464675});
    model.component("comp1").view("view2").set("environmentmap", "envmap_machineshop2");
    model.component("comp1").view("view2").camera().set("zoomanglefull", 19.975101947784424);

    model.result("pg2").set("view", "view2");
    model.result().create("pg3", "PolarGroup");
    model.result("pg3").label("\u4e8c\u7ef4\u8fdc\u573a (emw)");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").create("rp1", "RadiationPattern");
    model.result("pg3").feature("rp1").set("legend", "on");
    model.result("pg3").feature("rp1").set("phidisc", "180");
    model.result("pg3").feature("rp1").set("expr", new String[]{"emw.normEfar"});
    model.result("pg3").feature("rp1").create("exp1", "Export");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u4e09\u7ef4\u8fdc\u573a\uff0c\u589e\u76ca (emw)");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").set("view", "new");
    model.result("pg4").set("edges", "off");
    model.result("pg4").set("showlegendsmaxmin", true);
    model.result("pg4").create("rp1", "RadiationPattern");
    model.result("pg4").feature("rp1").set("expr", new String[]{"emw.rGaindBEfar"});
    model.result("pg4").feature("rp1").set("colorexpr", new String[]{"emw.normEfar"});
    model.result("pg4").feature("rp1").set("useradiusascolor", true);
    model.result("pg4").feature("rp1").set("directivityexpr", new String[]{"emw.normEfar^2"});
    model.result("pg4").feature("rp1").set("thetadisc", "45");
    model.result("pg4").feature("rp1").set("phidisc", "90");
    model.result("pg4").feature("rp1").set("directivity", "on");
    model.result("pg4").feature("rp1").set("colortable", "RainbowLight");
    model.result("pg4").feature("rp1").create("exp1", "Export");
    model.result("pg4").feature("rp1").feature("exp1").setIndex("expr", "comp1.emw.theta", 0);
    model.result("pg4").feature("rp1").feature("exp1").setIndex("expr", "comp1.emw.phi", 1);
    model.result("pg3").feature("rp1").feature("exp1").setIndex("expr", "comp1.emw.theta", 0);
    model.result("pg3").feature("rp1").feature("exp1").setIndex("expr", "comp1.emw.phi", 1);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("mslc1").set("xnumber", "0");
    model.result("pg1").feature("mslc1").set("ynumber", "0");
    model.result("pg1").feature("mslc1").set("colortable", "ThermalDark");
    model.result("pg1").feature("mslc1").create("sel1", "Selection");
    model.result("pg1").feature("mslc1").feature("sel1").selection().set(6, 7);
    model.result("pg1").run();
    model.result("pg1").set("edges", false);
    model.result("pg1").create("arwv1", "ArrowVolume");
    model.result("pg1").run();
    model.result("pg1").feature("arwv1").set("xnumber", 1);
    model.result("pg1").feature("arwv1").set("ynumber", 31);
    model.result("pg1").feature("arwv1").set("znumber", 31);
    model.result("pg1").feature("arwv1").set("arrowlength", "logarithmic");
    model.result("pg1").feature("arwv1").create("sel1", "Selection");
    model.result("pg1").feature("arwv1").feature("sel1").selection().set(5);
    model.result("pg1").run();
    model.result("pg1").feature("arwv1").create("col1", "Color");
    model.result("pg1").run();
    model.result("pg1").feature("arwv1").feature("col1").set("expr", "20*log10(emw.normE)");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").set("titletype", "manual");
    model.result("pg3").set("title", "\u8fdc\u573a\u589e\u76ca\uff0cdBi");
    model.result("pg3").run();
    model.result("pg3").feature("rp1").set("expr", "emw.gaindBEfar");
    model.result("pg3").feature("rp1").set("descr", "\u8fdc\u573a\u589e\u76ca\uff0cdBi");
    model.result("pg3").feature("rp1").set("refdir", new int[]{0, 1, 0});
    model.result("pg3").feature("rp1").set("normal", new int[]{1, 0, 0});
    model.result("pg3").feature("rp1").set("legendmethod", "manual");
    model.result("pg3").feature("rp1").setIndex("legends", "E \u5e73\u9762", 0);
    model.result("pg3").feature().duplicate("rp2", "rp1");
    model.result("pg3").run();
    model.result("pg3").feature("rp2").set("normal", new int[]{0, -1, 0});
    model.result("pg3").feature("rp2").set("refdir", new int[]{1, 0, 0});
    model.result("pg3").feature("rp2").set("linestyle", "dashed");
    model.result("pg3").feature("rp2").setIndex("legends", "H \u5e73\u9762", 0);
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").create("ann1", "Annotation");
    model.result("pg4").feature("ann1").set("text", "\u6700\u5927\u65b9\u5411\u6027\u7cfb\u6570\uff1a6.9 dB");
    model.result("pg4").feature("ann1").set("poszexpr", 1.71);
    model.result("pg4").feature("ann1").set("backgroundcolor", "white");
    model.result("pg4").feature("ann1").set("showframe", true);
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").run();
    model.result("pg5").create("iso1", "Isosurface");
    model.result("pg5").feature("iso1").set("expr", "20*log10(emw.normE)");
    model.result("pg5").feature("iso1").set("number", 20);
    model.result("pg5").feature("iso1").create("sel1", "Selection");
    model.result("pg5").feature("iso1").feature("sel1").selection().set(5, 6, 7);
    model.result("pg5").run();
    model.result("pg5").feature("iso1").create("filt1", "Filter");
    model.result("pg5").run();
    model.result("pg5").feature("iso1").feature("filt1").set("expr", "x>0");
    model.result("pg5").run();

    model.study().create("std2");
    model.study("std2").create("frawe", "FrequencyAdaptive");
    model.study("std2").feature("frawe").set("plotgroup", "Default");
    model.study("std2").feature("frawe").set("solnum", "auto");
    model.study("std2").feature("frawe").set("notsolnum", "auto");
    model.study("std2").feature("frawe").set("outputmap", new String[]{});
    model.study("std2").feature("frawe").setSolveFor("/physics/emw", true);
    model.study("std2").setGenPlots(false);
    model.study("std2").feature("frawe").set("plist", "range(freq_min,100[kHz],freq_max)");
    model.study("std2").feature("frawe").setEntry("outputmap", "emw", "selection");
    model.study("std2").feature("frawe").setEntry("outputselectionmap", "emw", "sel1");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("S \u53c2\u6570\uff0c\u6e10\u8fd1\u6ce2\u5f62\u4f30\u8ba1");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").set("titletype", "manual");
    model.result("pg6")
         .set("title", "\u81ea\u9002\u5e94\u9891\u7387\u626b\u63cf\uff0c\u5fae\u5e26\u8d34\u7247\u5929\u7ebf");
    model.result("pg6").set("legendpos", "lowerright");
    model.result("pg6").create("glob1", "Global");
    model.result("pg6").feature("glob1").set("markerpos", "datapoints");
    model.result("pg6").feature("glob1").set("linewidth", "preference");
    model.result("pg6").feature("glob1").set("expr", new String[]{"emw.S11dB"});
    model.result("pg6").feature("glob1").set("descr", new String[]{"S11"});
    model.result("pg6").run();
    model.result().dataset().create("af1", "ArrayFactor");
    model.result().dataset("af1").set("absolutevalue", true);
    model.result().dataset("af1").setIndex("array3d", 8, 0, 0);
    model.result().dataset("af1").setIndex("array3d", 8, 0, 1);
    model.result().dataset("af1").setIndex("array3d", 0.48, 2, 0);
    model.result().dataset("af1").setIndex("array3d", 0.48, 2, 1);
    model.result().dataset("af1").set("scale", "dB");
    model.result().dataset("af1").set("normalization", true);
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").run();
    model.result("pg7").label("\u4e09\u7ef4\u8fdc\u573a\uff0c\u865a\u62df\u9635\u5217");
    model.result("pg7").set("data", "af1");
    model.result("pg7").set("showlegendsmaxmin", true);

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg7").create("rp1", "RadiationPattern");
    model.result("pg7").feature("rp1").set("expr", "emw.gaindBEfar");
    model.result("pg7").feature("rp1").set("thresholdactive", true);
    model.result("pg7").feature("rp1").set("threshold", -30);
    model.result("pg7").feature("rp1").set("thetadisc", 180);
    model.result("pg7").feature("rp1").set("phidisc", 180);
    model.result("pg7").feature("rp1").set("colortable", "HeatCamera");
    model.result("pg7").run();
    model.result().dataset().duplicate("af2", "af1");
    model.result().dataset("af2").setIndex("array3d", "-2*pi*0.48*cos(pi/3)", 1, 0);
    model.result().create("pg8", "PolarGroup");
    model.result("pg8").run();
    model.result("pg8").label("\u4e8c\u7ef4\u8fdc\u573a\u589e\u76ca (dB)\uff0c\u865a\u62df\u9635\u5217");
    model.result("pg8").set("titletype", "manual");
    model.result("pg8").set("title", "8x8 \u5fae\u5e26\u8d34\u7247\u5929\u7ebf\u9635\u5217");
    model.result("pg8").set("axislimits", true);
    model.result("pg8").set("rmin", -15);
    model.result("pg8").set("rmax", 25);
    model.result("pg8").set("legendpos", "upperleft");
    model.result("pg8").create("rp1", "RadiationPattern");
    model.result("pg8").feature("rp1").set("markerpos", "datapoints");
    model.result("pg8").feature("rp1").set("linewidth", "preference");
    model.result("pg8").feature("rp1").set("expr", "emw.gaindBEfar");
    model.result("pg8").feature("rp1").set("phidisc", 360);
    model.result("pg8").feature("rp1").set("normal", new int[]{0, -1, 0});
    model.result("pg8").feature("rp1").set("legend", true);
    model.result("pg8").feature("rp1").set("legendmethod", "manual");
    model.result("pg8").feature("rp1").setIndex("legends", "\u5355\u8d34\u7247\u5929\u7ebf\u589e\u76ca", 0);
    model.result("pg8").run();
    model.result("pg8").feature().duplicate("rp2", "rp1");
    model.result("pg8").run();
    model.result("pg8").feature("rp2").set("data", "af2");
    model.result("pg8").feature("rp2").set("expr", "1");
    model.result("pg8").feature("rp2").setIndex("legends", "8x8 \u5747\u5300\u9635\u5217\u56e0\u5b50", 0);
    model.result("pg8").run();
    model.result("pg8").feature().duplicate("rp3", "rp2");
    model.result("pg8").run();
    model.result("pg8").feature("rp3").set("expr", "emw.gaindBEfar");
    model.result("pg8").feature("rp3")
         .setIndex("legends", "8x8 \u8d34\u7247\u5929\u7ebf\u9635\u5217\u589e\u76ca", 0);
    model.result("pg8").run();

    model.title("\u5fae\u5e26\u8d34\u7247\u5929\u7ebf");

    model
         .description("\u5fae\u5e26\u8d34\u7247\u5929\u7ebf\u7531\u4e8e\u5176\u4f4e\u5256\u9762\u7684\u4fdd\u5f62\u8bbe\u8ba1\u3001\u76f8\u5bf9\u8f83\u4f4e\u7684\u6210\u672c\u4ee5\u53ca\u975e\u5e38\u7a84\u7684\u5e26\u5bbd\u7b49\u4f18\u70b9\u800c\u5e7f\u53d7\u6b22\u8fce\u3002\u672c\u4f8b\u8ba1\u7b97\u4e00\u4e2a\u5d4c\u5165\u9988\u7535\u8d34\u7247\u5929\u7ebf\u7684\u53cd\u5c04\u529f\u7387\u548c\u8fdc\u573a\u8f90\u5c04\u65b9\u5411\u56fe\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("microstrip_patch_antenna_inset.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
