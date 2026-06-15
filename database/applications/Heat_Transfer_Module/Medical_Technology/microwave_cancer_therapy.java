/*
 * microwave_cancer_therapy.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:23 by COMSOL 6.3.0.290. */
public class microwave_cancer_therapy {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Heat_Transfer_Module\\Medical_Technology");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("emw", "ElectromagneticWaves", "geom1");
    model.component("comp1").physics().create("ht", "BioHeat", "geom1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").multiphysics().create("emh1", "ElectromagneticHeating", 2);

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").set("ftplistmethod", "manual");
    model.study("std1").feature("freq").set("punit", "Hz");
    model.study("std1").feature("freq").set("plist", "1[MHz]");
    model.study("std1").feature("freq").set("solnum", "auto");
    model.study("std1").feature("freq").set("notsolnum", "auto");
    model.study("std1").feature("freq").set("outputmap", new String[]{});
    model.study("std1").feature("freq").set("ngenAUX", "1");
    model.study("std1").feature("freq").set("goalngenAUX", "1");
    model.study("std1").feature("freq").set("ngenAUX", "1");
    model.study("std1").feature("freq").set("goalngenAUX", "1");
    model.study("std1").feature("freq").setSolveFor("/physics/emw", true);
    model.study("std1").feature("freq").setSolveFor("/physics/ht", true);
    model.study("std1").feature("freq").setSolveFor("/multiphysics/emh1", true);
    model.study("std1").feature("freq").setSolveFor("/physics/ht", false);
    model.study("std1").feature("freq").setSolveFor("/multiphysics/emh1", false);
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("ftplistmethod", "manual");
    model.study("std1").feature("time").set("initialtime", "0");
    model.study("std1").feature("time").set("solnum", "auto");
    model.study("std1").feature("time").set("notsolnum", "auto");
    model.study("std1").feature("time").set("outputmap", new String[]{});
    model.study("std1").feature("time").setSolveFor("/physics/emw", true);
    model.study("std1").feature("time").setSolveFor("/physics/ht", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/emh1", true);
    model.study("std1").feature("time").setSolveFor("/physics/emw", false);

    model.component("comp1").geom("geom1").insertFile("microwave_cancer_therapy_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("rho_blood", "1e3[kg/m^3]", "\u5bc6\u5ea6\uff0c\u8840\u6db2");
    model.param().set("Cp_blood", "3639[J/(kg*K)]", "\u6bd4\u70ed\u5bb9\uff0c\u8840\u6db2");
    model.param().set("omega_blood", "3.6e-3[1/s]", "\u8840\u6db2\u704c\u6ce8\u7387");
    model.param().set("T_blood", "37[degC]", "\u8840\u6db2\u6e29\u5ea6");
    model.param().set("eps_liver", "43.03", "\u76f8\u5bf9\u4ecb\u7535\u5e38\u6570\uff0c\u809d\u810f");
    model.param().set("sigma_liver", "1.69[S/m]", "\u7535\u5bfc\u7387\uff0c\u809d\u810f");
    model.param().set("eps_diel", "2.03", "\u76f8\u5bf9\u4ecb\u7535\u5e38\u6570\uff0c\u7535\u4ecb\u8d28");
    model.param().set("eps_cat", "2.6", "\u76f8\u5bf9\u4ecb\u7535\u5e38\u6570\uff0c\u5bfc\u7ba1");
    model.param().set("f", "2.45[GHz]", "\u5fae\u6ce2\u9891\u7387");
    model.param().set("P_in", "10[W]", "\u8f93\u5165\u5fae\u6ce2\u529f\u7387");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("Liver (human)");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "3540[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "1079[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.52[W/(m*K)]", "0", "0", "0", "0.52[W/(m*K)]", "0", "0", "0", "0.52[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("frequencyfactor", "7.39e39[1/s]");
    model.component("comp1").material("mat1").propertyGroup("def").set("activationenergy", "2.577e5[J/mol]");
    model.component("comp1").material("mat1").selection().set(1);
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermittivity", new String[]{"eps_liver"});
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"sigma_liver"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u5bfc\u7ba1");
    model.component("comp1").material("mat2").selection().named("geom1_uni1_dom");
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermittivity", new String[]{"eps_cat"});
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]"});
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").label("\u7535\u4ecb\u8d28");
    model.component("comp1").material("mat3").selection().named("geom1_r3_dom");
    model.component("comp1").material("mat3").propertyGroup("def").set("relpermittivity", new String[]{"eps_diel"});
    model.component("comp1").material("mat3").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]"});
    model.component("comp1").material().create("mat4", "Common");
    model.component("comp1").material("mat4").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat4").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat4").propertyGroup("def").func().create("rho", "Analytic");
    model.component("comp1").material("mat4").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat4").propertyGroup("def").func().create("cs", "Analytic");
    model.component("comp1").material("mat4").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat4").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat4").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat4").propertyGroup()
         .create("NonlinearModel", "NonlinearModel", "Nonlinear model");
    model.component("comp1").material("mat4").propertyGroup().create("idealGas", "idealGas", "Ideal gas");
    model.component("comp1").material("mat4").propertyGroup("idealGas").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat4").label("Air");
    model.component("comp1").material("mat4").set("family", "air");
    model.component("comp1").material("mat4").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat4").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp1").material("mat4").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat4").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-8.38278E-7+8.35717342E-8*T^1-7.69429583E-11*T^2+4.6437266E-14*T^3-1.06585607E-17*T^4"}});
    model.component("comp1").material("mat4").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat4").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat4").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat4").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat4").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat4").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat4").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat4").propertyGroup("def").func("rho").label("Analytic");
    model.component("comp1").material("mat4").propertyGroup("def").func("rho")
         .set("expr", "pA*0.02897/R_const[K*mol/J]/T");
    model.component("comp1").material("mat4").propertyGroup("def").func("rho").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat4").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat4").propertyGroup("def").func("rho")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat4").propertyGroup("def").func("rho")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat4").propertyGroup("def").func("rho")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat4").propertyGroup("def").func("rho")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "293.15"}});
    model.component("comp1").material("mat4").propertyGroup("def").func("k").label("Piecewise 3");
    model.component("comp1").material("mat4").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat4").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-0.00227583562+1.15480022E-4*T^1-7.90252856E-8*T^2+4.11702505E-11*T^3-7.43864331E-15*T^4"}});
    model.component("comp1").material("mat4").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat4").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat4").propertyGroup("def").func("cs").label("Analytic 2");
    model.component("comp1").material("mat4").propertyGroup("def").func("cs")
         .set("expr", "sqrt(1.4*R_const[K*mol/J]/0.02897*T)");
    model.component("comp1").material("mat4").propertyGroup("def").func("cs").set("args", new String[]{"T"});
    model.component("comp1").material("mat4").propertyGroup("def").func("cs").set("fununit", "m/s");
    model.component("comp1").material("mat4").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat4").propertyGroup("def").func("cs")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat4").propertyGroup("def").func("cs")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat4").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp1").material("mat4").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat4").propertyGroup("def").func("an1")
         .set("expr", "-1/rho(pA,T)*d(rho(pA,T),T)");
    model.component("comp1").material("mat4").propertyGroup("def").func("an1").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat4").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat4").propertyGroup("def").func("an1")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat4").propertyGroup("def").func("an1")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat4").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat4").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "373.15"}});
    model.component("comp1").material("mat4").propertyGroup("def").func("an2").label("Analytic 2a");
    model.component("comp1").material("mat4").propertyGroup("def").func("an2").set("funcname", "muB");
    model.component("comp1").material("mat4").propertyGroup("def").func("an2").set("expr", "0.6*eta(T)");
    model.component("comp1").material("mat4").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat4").propertyGroup("def").func("an2").set("fununit", "Pa*s");
    model.component("comp1").material("mat4").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat4").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"200"});
    model.component("comp1").material("mat4").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "200", "1600"}});
    model.component("comp1").material("mat4").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat4").propertyGroup("def").set("molarmass", "");
    model.component("comp1").material("mat4").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)"});
    model.component("comp1").material("mat4").propertyGroup("def").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat4").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat4").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat4").propertyGroup("def").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp1").material("mat4").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat4").propertyGroup("def").set("density", "rho(pA,T)");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat4").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp1").material("mat4").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat4").propertyGroup("def").addInput("pressure");
    model.component("comp1").material("mat4").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp1").material("mat4").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat4").propertyGroup("NonlinearModel").label("Nonlinear model");
    model.component("comp1").material("mat4").propertyGroup("NonlinearModel").set("BA", "def.gamma-1");
    model.component("comp1").material("mat4").propertyGroup("idealGas").label("Ideal gas");
    model.component("comp1").material("mat4").propertyGroup("idealGas").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat4").propertyGroup("idealGas").func("Cp").set("arg", "T");
    model.component("comp1").material("mat4").propertyGroup("idealGas").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat4").propertyGroup("idealGas").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat4").propertyGroup("idealGas").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat4").propertyGroup("idealGas").set("Rs", "R_const/Mn");
    model.component("comp1").material("mat4").propertyGroup("idealGas").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat4").propertyGroup("idealGas").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat4").propertyGroup("idealGas").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat4").propertyGroup("idealGas").addInput("temperature");
    model.component("comp1").material("mat4").propertyGroup("idealGas").addInput("pressure");
    model.component("comp1").material("mat4").materialType("nonSolid");
    model.component("comp1").material("mat4").selection().named("geom1_r5_dom");

    model.component("comp1").physics("emw").create("port1", "Port", 1);
    model.component("comp1").physics("emw").feature("port1").selection().set(8);
    model.component("comp1").physics("emw").feature("port1").set("PortType", "Coaxial");
    model.component("comp1").physics("emw").feature("port1").set("Pin", "P_in");
    model.component("comp1").physics("emw").create("sctr1", "Scattering", 1);
    model.component("comp1").physics("emw").feature("sctr1").selection().set(2, 17, 19, 20);
    model.component("comp1").physics("ht").selection().set(1);
    model.component("comp1").physics("ht").feature("bt1").create("tdam1", "ThermalDamage", 2);
    model.component("comp1").physics("ht").feature("bt1").feature("tdam1")
         .set("TransformationModel", "ArrheniusKinetics");
    model.component("comp1").physics("ht").feature("bt1").feature("bh1").set("Tb", "T_blood");
    model.component("comp1").physics("ht").feature("bt1").feature("bh1").set("rhobl", "rho_blood");
    model.component("comp1").physics("ht").feature("bt1").feature("bh1").set("Cp_b", "Cp_blood");
    model.component("comp1").physics("ht").feature("bt1").feature("bh1").set("omegab", "omega_blood");
    model.component("comp1").physics("ht").feature("init1").set("Tinit", "T_blood");

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "3[mm]");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().named("geom1_r3_dom");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", "0.15[mm]");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("freq").set("plist", "f");
    model.study("std1").feature("time").set("tunit", "min");
    model.study("std1").feature("time").set("tlist", "range(0,15[s],10)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u7535\u573a (emw)");
    model.result("pg1").set("dataisaxisym", "off");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").label("\u4e8c\u7ef4\u65cb\u8f6c");
    model.result().dataset("rev1").set("data", "none");
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("data", "dset1");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u6e29\u5ea6 (ht)");
    model.result("pg2").set("dataisaxisym", "off");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("solutionparams", "parent");
    model.result("pg2").feature("surf1").set("expr", "T");
    model.result("pg2").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "log10(comp1.emw.normE)");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "emw.Qh");
    model.result("pg1").feature("surf1").set("descr", "\u603b\u529f\u8017\u5bc6\u5ea6");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("rangecoloractive", true);
    model.result("pg1").feature("surf1").set("rangecolormax", "1e6");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").label("\u603b\u529f\u8017\u5bc6\u5ea6 (emw)");
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"temperature", "\u6e29\u5ea6", "K", "K"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "\u00b0C", 0, 3);
    model.result().configuration("prfu1").apply();
    model.result("pg2").run();
    model.result("pg2").label("\u6e29\u5ea6\uff0c\u4e8c\u7ef4");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("expr", "T");
    model.result("pg2").feature("surf1").set("descr", "\u6e29\u5ea6");
    model.result("pg2").run();
    model.result().dataset().create("cln1", "CutLine2D");
    model.result().dataset("cln1").setIndex("genpoints", 2.5, 0, 0);
    model.result().dataset("cln1").setIndex("genpoints", 80, 0, 1);
    model.result().dataset("cln1").setIndex("genpoints", 2.5, 1, 0);
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("Qh/rho vs. \u5f27\u957f");
    model.result("pg3").set("data", "cln1");
    model.result("pg3").setIndex("looplevelinput", "last", 0);
    model.result("pg3").set("titletype", "manual");
    model.result("pg3").set("title", "\u6cbf R=2.5 mm \u5782\u7ebf\u7684\u603b\u8d28\u91cf\u529f\u7387\u8017\u6563");
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg3").feature("lngr1").set("linewidth", "preference");
    model.result("pg3").feature("lngr1").set("expr", "z");
    model.result("pg3").feature("lngr1").set("xdata", "expr");
    model.result("pg3").feature("lngr1").set("xdataexpr", "emw.Qh/rho_blood");
    model.result("pg3").feature("lngr1").set("xdatadescractive", true);
    model.result("pg3").feature("lngr1").set("xdatadescr", "\u603b\u8d28\u91cf\u529f\u7387\u8017\u6563");
    model.result("pg3").run();
    model.result().dataset().create("rev2", "Revolve2D");
    model.result().dataset("rev2").set("startangle", -90);
    model.result().dataset("rev2").set("revangle", 225);
    model.result().dataset("rev2").set("data", "dset1");
    model.result().dataset("rev2")
         .set("defaultPlotIDs", new String[]{"ht/HT_PhysicsInterfaces/icom8/pdef1/pcond2/pcond4/pcond2/pg2|ht", "ht/HT_PhysicsInterfaces/icom8/pdef1/pcond2/pcond4/pcond2/pg3|ht", "ht/HT_PhysicsInterfaces/icom8/pdef1/pcond2/pcond4/pcond2/pg4|ht", "ht/HT_PhysicsInterfaces/icom12/pdef1/pcond4/pcond2/pcond5/pg2|ht", "ht/HT_PhysicsInterfaces/icom12/pdef1/pcond4/pcond2/pcond5/pg1|ht", "ht/HT_PhysicsInterfaces/icom12/pdef1/pcond4/pcond2/pcond5/pg3|ht"});
    model.result().dataset("rev2").label("\u4e8c\u7ef4\u65cb\u8f6c 1");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u6e29\u5ea6 (ht) 1");
    model.result("pg4").feature().create("vol1", "Volume");
    model.result("pg4").feature("vol1").set("solutionparams", "parent");
    model.result("pg4").feature("vol1").set("expr", "T");
    model.result("pg4").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg4").feature("vol1").set("smooth", "internal");
    model.result("pg4").feature("vol1").set("showsolutionparams", "on");
    model.result("pg4").feature("vol1").set("data", "parent");
    model.result("pg4").label("\u6e29\u5ea6 (ht) 1");
    model.result("pg4").run();
    model.result().numerical().create("int1", "IntSurface");
    model.result().numerical("int1").set("intvolume", true);
    model.result().numerical("int1").setIndex("looplevelinput", "last", 0);
    model.result().numerical("int1").selection().set(1);
    model.result().numerical("int1").set("expr", new String[]{"emw.Qh"});
    model.result().numerical("int1").set("descr", new String[]{"\u603b\u529f\u8017\u5bc6\u5ea6"});
    model.result().numerical("int1").set("unit", new String[]{"W"});
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u8868\u9762\u79ef\u5206 1");
    model.result().numerical("int1").set("table", "tbl1");
    model.result().numerical("int1").setResult();
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").run();
    model.result("pg5").label("\u53d7\u635f\u7ec4\u7ec7\uff0c\u4e8c\u7ef4");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", "ht.theta_d");
    model.result("pg5").feature("surf1").set("descr", "\u635f\u4f24\u5206\u6570");
    model.result("pg5").feature("surf1").set("resolution", "norefine");
    model.result("pg5").run();
    model.result().dataset().create("cpt1", "CutPoint2D");
    model.result().dataset("cpt1").set("pointx", "range(5,5,20)");
    model.result().dataset("cpt1").set("pointy", 20);
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u6e29\u5ea6\uff0c\u4e00\u7ef4");
    model.result("pg6").set("data", "cpt1");
    model.result("pg6").set("legendpos", "upperleft");
    model.result("pg6").create("ptgr1", "PointGraph");
    model.result("pg6").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg6").feature("ptgr1").set("linewidth", "preference");
    model.result("pg6").feature("ptgr1").set("expr", "T");
    model.result("pg6").feature("ptgr1").set("descr", "\u6e29\u5ea6");
    model.result("pg6").feature("ptgr1").set("linewidth", 3);
    model.result("pg6").feature("ptgr1").set("legend", true);
    model.result("pg6").feature("ptgr1").set("legendprefix", "\u70b9\uff1a");
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u53d7\u635f\u7ec4\u7ec7\uff0c\u4e00\u7ef4");
    model.result("pg7").set("data", "cpt1");
    model.result("pg7").create("ptgr1", "PointGraph");
    model.result("pg7").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg7").feature("ptgr1").set("linewidth", "preference");
    model.result("pg7").feature("ptgr1").set("expr", "ht.theta_d");
    model.result("pg7").feature("ptgr1").set("descr", "\u635f\u4f24\u5206\u6570");
    model.result("pg7").feature("ptgr1").set("linewidth", 3);
    model.result("pg7").feature("ptgr1").set("legend", true);
    model.result("pg7").feature("ptgr1").set("legendprefix", "\u70b9\uff1a");
    model.result("pg7").run();
    model.result("pg4").run();

    model.title("\u764c\u75c7\u80bf\u7624\u7684\u5fae\u6ce2\u70ed\u7597\u6cd5");

    model
         .description("\u672c\u4f8b\u4e2d\u5c06\u4e00\u6839\u7ec6\u957f\u7684\u5fae\u6ce2\u5929\u7ebf\u63d2\u5165\u80bf\u7624\uff0c\u4f7f\u80bf\u7624\u53d1\u70ed\u4ea7\u751f\u51dd\u7ed3\u533a\u57df\uff0c\u4ece\u800c\u6740\u6b7b\u764c\u7ec6\u80de\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("microwave_cancer_therapy.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
