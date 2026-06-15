/*
 * isothermal_box.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:23 by COMSOL 6.3.0.290. */
public class isothermal_box {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Heat_Transfer_Module\\Medical_Technology");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("ht", "HeatTransfer", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/ht", true);

    model.param().set("box_w", "40[cm]");
    model.param().descr("box_w", "\u7bb1\u5bbd\u5ea6");
    model.param().set("box_d", "40[cm]");
    model.param().descr("box_d", "\u7bb1\u6df1\u5ea6");
    model.param().set("box_h", "24[cm]");
    model.param().descr("box_h", "\u7bb1\u9ad8\u5ea6");
    model.param().set("content_w", "24[cm]");
    model.param().descr("content_w", "\u5185\u88c5\u7269\u5bbd\u5ea6");
    model.param().set("content_d", "24[cm]");
    model.param().descr("content_d", "\u5185\u88c5\u7269\u6df1\u5ea6");
    model.param().set("content_h", "20[cm]");
    model.param().descr("content_h", "\u5185\u88c5\u7269\u9ad8\u5ea6");
    model.param().set("ice_t", "2[cm]");
    model.param().descr("ice_t", "\u5171\u6676\u677f\u539a\u5ea6");
    model.param().set("foam_t", "4[cm]");
    model.param().descr("foam_t", "\u6ce1\u6cab\u5c42\u539a\u5ea6");
    model.param().set("lid_t", "4[cm]");
    model.param().descr("lid_t", "\u7bb1\u76d6\u539a\u5ea6");

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"box_w", "box_d", "box_h"});
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new String[]{"content_w", "content_d", "1"});
    model.component("comp1").geom("geom1").feature("blk2").setIndex("size", "content_h", 2);
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new String[]{"(box_w-content_w)/2", "0", "0"});
    model.component("comp1").geom("geom1").feature("blk2").setIndex("pos", "(box_d-content_d)/2", 1);
    model.component("comp1").geom("geom1").feature("blk2").setIndex("pos", "box_h-content_h", 2);
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u5185\u88c5\u7269");
    model.component("comp1").geom("geom1").feature("blk2").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").set("transparency", true);

    model.component("comp1").geom("geom1").run("blk2");
    model.component("comp1").geom("geom1").create("blk3", "Block");
    model.component("comp1").geom("geom1").feature("blk3").set("size", new String[]{"ice_t", "content_d", "1"});
    model.component("comp1").geom("geom1").feature("blk3").setIndex("size", "content_h", 2);
    model.component("comp1").geom("geom1").feature("blk3").set("pos", new String[]{"box_w-foam_t-ice_t", "0", "0"});
    model.component("comp1").geom("geom1").feature("blk3").setIndex("pos", "(box_d-content_d)/2", 1);
    model.component("comp1").geom("geom1").feature("blk3").setIndex("pos", "box_h-content_h", 2);
    model.component("comp1").geom("geom1").selection().create("csel2", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel2").label("\u51b0");
    model.component("comp1").geom("geom1").feature("blk3").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").feature().duplicate("blk4", "blk3");
    model.component("comp1").geom("geom1").feature("blk4").setIndex("pos", "foam_t", 0);
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").run("blk4");
    model.component("comp1").geom("geom1").create("blk5", "Block");
    model.component("comp1").geom("geom1").feature("blk5").set("size", new String[]{"content_w", "ice_t", "1"});
    model.component("comp1").geom("geom1").feature("blk5").setIndex("size", "content_h", 2);
    model.component("comp1").geom("geom1").feature("blk5").set("pos", new String[]{"(box_w-content_w)/2", "0", "0"});
    model.component("comp1").geom("geom1").feature("blk5").setIndex("pos", "foam_t", 1);
    model.component("comp1").geom("geom1").feature("blk5").setIndex("pos", "box_h-content_h", 2);
    model.component("comp1").geom("geom1").feature("blk5").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").feature().duplicate("blk6", "blk5");
    model.component("comp1").geom("geom1").feature("blk6").setIndex("pos", "box_d-foam_t-ice_t", 1);
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").run("blk6");
    model.component("comp1").geom("geom1").create("blk7", "Block");
    model.component("comp1").geom("geom1").feature("blk7")
         .set("size", new String[]{"(box_w-content_w)/2-foam_t", "1", "1"});
    model.component("comp1").geom("geom1").feature("blk7").setIndex("size", "(box_d-content_d)/2-foam_t", 1);
    model.component("comp1").geom("geom1").feature("blk7").setIndex("size", "content_h", 2);
    model.component("comp1").geom("geom1").feature("blk7").set("pos", new String[]{"foam_t", "foam_t", "0"});
    model.component("comp1").geom("geom1").feature("blk7").setIndex("pos", "box_h-content_h", 2);
    model.component("comp1").geom("geom1").selection().create("csel3", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel3").label("\u5269\u4f59\u7a7a\u95f4");
    model.component("comp1").geom("geom1").feature("blk7").set("contributeto", "csel3");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").feature().duplicate("blk8", "blk7");
    model.component("comp1").geom("geom1").feature("blk8").setIndex("pos", "(box_w+content_w)/2", 0);
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").feature().duplicate("blk9", "blk8");
    model.component("comp1").geom("geom1").feature("blk9").setIndex("pos", "(box_d+content_d)/2", 1);
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").feature().duplicate("blk10", "blk9");
    model.component("comp1").geom("geom1").feature("blk10").setIndex("pos", "foam_t", 0);
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").run("blk10");
    model.component("comp1").geom("geom1").create("blk11", "Block");
    model.component("comp1").geom("geom1").feature("blk11").set("size", new String[]{"box_w", "box_d", "lid_t"});
    model.component("comp1").geom("geom1").feature("blk11").set("pos", new String[]{"0", "0", "box_h"});
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u6ce1\u6cab");
    model.component("comp1").selection("sel1").set(1, 2);
    model.component("comp1").selection().create("adj1", "Adjacent");
    model.component("comp1").selection("adj1").label("\u8584\u7a7a\u6c14\u7535\u963b\u5c42");
    model.component("comp1").selection("adj1").set("input", new String[]{"sel1", "geom1_csel1_dom"});
    model.component("comp1").selection("adj1").set("exterior", false);
    model.component("comp1").selection("adj1").set("interior", true);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u5916\u8868\u9762");
    model.component("comp1").selection("sel2").all();
    model.component("comp1").selection("sel2").geom("geom1", 3, 2, new String[]{"exterior"});
    model.component("comp1").selection("sel2").all();

    model.component("comp1").probe().create("dom1", "Domain");
    model.component("comp1").probe("dom1").set("intsurface", true);
    model.component("comp1").probe("dom1").set("intvolume", true);
    model.component("comp1").probe("dom1").label("\u6700\u5c0f\u5185\u88c5\u7269");
    model.component("comp1").probe("dom1").set("type", "minimum");
    model.component("comp1").probe("dom1").selection().named("geom1_csel1_dom");
    model.component("comp1").probe("dom1").set("unit", "\u00b0C");
    model.component("comp1").probe().create("dom2", "Domain");
    model.component("comp1").probe("dom2").set("intsurface", true);
    model.component("comp1").probe("dom2").set("intvolume", true);
    model.component("comp1").probe("dom2").label("\u5e73\u5747\u5185\u88c5\u7269");
    model.component("comp1").probe("dom2").selection().named("geom1_csel1_dom");
    model.component("comp1").probe("dom2").set("unit", "\u00b0C");
    model.component("comp1").probe().create("dom3", "Domain");
    model.component("comp1").probe("dom3").set("intsurface", true);
    model.component("comp1").probe("dom3").set("intvolume", true);
    model.component("comp1").probe("dom3").label("\u6700\u5927\u5185\u88c5\u7269");
    model.component("comp1").probe("dom3").set("type", "maximum");
    model.component("comp1").probe("dom3").selection().named("geom1_csel1_dom");
    model.component("comp1").probe("dom3").set("unit", "\u00b0C");
    model.component("comp1").probe().create("dom4", "Domain");
    model.component("comp1").probe("dom4").set("intsurface", true);
    model.component("comp1").probe("dom4").set("intvolume", true);
    model.component("comp1").probe("dom4").label("\u5e73\u5747\u51b0\u7528\u91cf");
    model.component("comp1").probe("dom4").selection().named("geom1_csel2_dom");
    model.component("comp1").probe("dom4").set("unit", "\u00b0C");

    model.component("comp1").physics("ht").create("fluid1", "FluidHeatTransferModel", 3);
    model.component("comp1").physics("ht").feature("fluid1").selection().named("geom1_csel2_dom");
    model.component("comp1").physics("ht").feature("fluid1").create("phc1", "PhaseChangeMaterial", 3);
    model.component("comp1").physics("ht").create("fls1", "FluidLayeredShell", 2);
    model.component("comp1").physics("ht").feature("fls1").selection().named("adj1");
    model.component("comp1").physics("ht").create("sls1", "SolidLayeredShell", 2);
    model.component("comp1").physics("ht").feature("sls1").selection().named("geom1_csel2_bnd");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u6ce1\u6cab");
    model.component("comp1").material("mat1").selection().named("sel1");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.03[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"25[kg/m^3]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", new String[]{"2[J/(kg*K)]"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u5185\u88c5\u7269\u6750\u6599");
    model.component("comp1").material("mat2").selection().named("geom1_csel1_dom");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"1[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"2000[kg/m^3]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("heatcapacity", new String[]{"800[J/(kg*K)]"});
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("rho", "Analytic");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("cs", "Analytic");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat3").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat3").propertyGroup()
         .create("NonlinearModel", "NonlinearModel", "Nonlinear model");
    model.component("comp1").material("mat3").propertyGroup().create("idealGas", "idealGas", "Ideal gas");
    model.component("comp1").material("mat3").propertyGroup("idealGas").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat3").label("Air");
    model.component("comp1").material("mat3").set("family", "air");
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp1").material("mat3").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat3").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-8.38278E-7+8.35717342E-8*T^1-7.69429583E-11*T^2+4.6437266E-14*T^3-1.06585607E-17*T^4"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat3").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat3").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat3").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat3").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat3").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat3").propertyGroup("def").func("rho").label("Analytic");
    model.component("comp1").material("mat3").propertyGroup("def").func("rho")
         .set("expr", "pA*0.02897/R_const[K*mol/J]/T");
    model.component("comp1").material("mat3").propertyGroup("def").func("rho").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat3").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat3").propertyGroup("def").func("rho")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat3").propertyGroup("def").func("rho")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat3").propertyGroup("def").func("rho")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat3").propertyGroup("def").func("rho")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "293.15"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("k").label("Piecewise 3");
    model.component("comp1").material("mat3").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat3").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-0.00227583562+1.15480022E-4*T^1-7.90252856E-8*T^2+4.11702505E-11*T^3-7.43864331E-15*T^4"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat3").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat3").propertyGroup("def").func("cs").label("Analytic 2");
    model.component("comp1").material("mat3").propertyGroup("def").func("cs")
         .set("expr", "sqrt(1.4*R_const[K*mol/J]/0.02897*T)");
    model.component("comp1").material("mat3").propertyGroup("def").func("cs").set("args", new String[]{"T"});
    model.component("comp1").material("mat3").propertyGroup("def").func("cs").set("fununit", "m/s");
    model.component("comp1").material("mat3").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat3").propertyGroup("def").func("cs")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat3").propertyGroup("def").func("cs")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp1").material("mat3").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat3").propertyGroup("def").func("an1")
         .set("expr", "-1/rho(pA,T)*d(rho(pA,T),T)");
    model.component("comp1").material("mat3").propertyGroup("def").func("an1").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat3").propertyGroup("def").func("an1")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an1")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "373.15"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("an2").label("Analytic 2a");
    model.component("comp1").material("mat3").propertyGroup("def").func("an2").set("funcname", "muB");
    model.component("comp1").material("mat3").propertyGroup("def").func("an2").set("expr", "0.6*eta(T)");
    model.component("comp1").material("mat3").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an2").set("fununit", "Pa*s");
    model.component("comp1").material("mat3").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"200"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "200", "1600"}});
    model.component("comp1").material("mat3").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat3").propertyGroup("def").set("molarmass", "");
    model.component("comp1").material("mat3").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)"});
    model.component("comp1").material("mat3").propertyGroup("def").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat3").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat3").propertyGroup("def").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "rho(pA,T)");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat3").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp1").material("mat3").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat3").propertyGroup("def").addInput("pressure");
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("NonlinearModel").label("Nonlinear model");
    model.component("comp1").material("mat3").propertyGroup("NonlinearModel").set("BA", "def.gamma-1");
    model.component("comp1").material("mat3").propertyGroup("idealGas").label("Ideal gas");
    model.component("comp1").material("mat3").propertyGroup("idealGas").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat3").propertyGroup("idealGas").func("Cp").set("arg", "T");
    model.component("comp1").material("mat3").propertyGroup("idealGas").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat3").propertyGroup("idealGas").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat3").propertyGroup("idealGas").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat3").propertyGroup("idealGas").set("Rs", "R_const/Mn");
    model.component("comp1").material("mat3").propertyGroup("idealGas").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat3").propertyGroup("idealGas").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat3").propertyGroup("idealGas").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat3").propertyGroup("idealGas").addInput("temperature");
    model.component("comp1").material("mat3").propertyGroup("idealGas").addInput("pressure");
    model.component("comp1").material("mat3").materialType("nonSolid");
    model.component("comp1").material("mat3").selection().named("geom1_csel3_dom");
    model.component("comp1").material().create("mat4", "Common");
    model.component("comp1").material("mat4").label("\u6c34");
    model.component("comp1").material("mat4").selection().named("geom1_csel2_dom");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.6[W/(m*K)]"});
    model.component("comp1").material("mat4").propertyGroup("def").set("density", new String[]{"1000[kg/m^3]"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("heatcapacity", new String[]{"4200[J/(kg*K)]"});
    model.component("comp1").material().create("mat5", "Common");
    model.component("comp1").material("mat5").label("\u51b0");
    model.component("comp1").material("mat5").selection().named("geom1_csel2_dom");
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("thermalconductivity", new String[]{"2.3[W/(m*K)]"});
    model.component("comp1").material("mat5").propertyGroup("def").set("density", new String[]{"1000[kg/m^3]"});
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("heatcapacity", new String[]{"2050[J/(kg*K)]"});
    model.component("comp1").material().create("mat6", "Common");
    model.component("comp1").material("mat6").label("\u5171\u6676\u677f\u5305\u88c5");
    model.component("comp1").material("mat6").selection().geom("geom1", 2);
    model.component("comp1").material("mat6").selection().named("geom1_csel2_bnd");
    model.component("comp1").material("mat6").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.3[W/(m*K)]"});
    model.component("comp1").material("mat6").propertyGroup("def").set("density", new String[]{"1050[kg/m^3]"});
    model.component("comp1").material("mat6").propertyGroup("def").set("heatcapacity", new String[]{"20[J/(kg*K)]"});
    model.component("comp1").material("mat6").propertyGroup().create("shell", "shell", "Shell");
    model.component("comp1").material("mat6").propertyGroup("shell").set("lth", new String[]{"300[um]"});
    model.component("comp1").material().create("mat7", "Common");
    model.component("comp1").material("mat7").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat7").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat7").propertyGroup("def").func().create("rho", "Analytic");
    model.component("comp1").material("mat7").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat7").propertyGroup("def").func().create("cs", "Analytic");
    model.component("comp1").material("mat7").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat7").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat7").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat7").propertyGroup()
         .create("NonlinearModel", "NonlinearModel", "Nonlinear model");
    model.component("comp1").material("mat7").propertyGroup().create("idealGas", "idealGas", "Ideal gas");
    model.component("comp1").material("mat7").propertyGroup("idealGas").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat7").label("Air 1");
    model.component("comp1").material("mat7").set("family", "air");
    model.component("comp1").material("mat7").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat7").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp1").material("mat7").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat7").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-8.38278E-7+8.35717342E-8*T^1-7.69429583E-11*T^2+4.6437266E-14*T^3-1.06585607E-17*T^4"}});
    model.component("comp1").material("mat7").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat7").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat7").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat7").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat7").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat7").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat7").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat7").propertyGroup("def").func("rho").label("Analytic");
    model.component("comp1").material("mat7").propertyGroup("def").func("rho")
         .set("expr", "pA*0.02897/R_const[K*mol/J]/T");
    model.component("comp1").material("mat7").propertyGroup("def").func("rho").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat7").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat7").propertyGroup("def").func("rho")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat7").propertyGroup("def").func("rho")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat7").propertyGroup("def").func("rho")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat7").propertyGroup("def").func("rho")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "293.15"}});
    model.component("comp1").material("mat7").propertyGroup("def").func("k").label("Piecewise 3");
    model.component("comp1").material("mat7").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat7").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-0.00227583562+1.15480022E-4*T^1-7.90252856E-8*T^2+4.11702505E-11*T^3-7.43864331E-15*T^4"}});
    model.component("comp1").material("mat7").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat7").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat7").propertyGroup("def").func("cs").label("Analytic 2");
    model.component("comp1").material("mat7").propertyGroup("def").func("cs")
         .set("expr", "sqrt(1.4*R_const[K*mol/J]/0.02897*T)");
    model.component("comp1").material("mat7").propertyGroup("def").func("cs").set("args", new String[]{"T"});
    model.component("comp1").material("mat7").propertyGroup("def").func("cs").set("fununit", "m/s");
    model.component("comp1").material("mat7").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat7").propertyGroup("def").func("cs")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat7").propertyGroup("def").func("cs")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat7").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp1").material("mat7").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat7").propertyGroup("def").func("an1")
         .set("expr", "-1/rho(pA,T)*d(rho(pA,T),T)");
    model.component("comp1").material("mat7").propertyGroup("def").func("an1").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat7").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat7").propertyGroup("def").func("an1")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat7").propertyGroup("def").func("an1")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat7").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat7").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "373.15"}});
    model.component("comp1").material("mat7").propertyGroup("def").func("an2").label("Analytic 2a");
    model.component("comp1").material("mat7").propertyGroup("def").func("an2").set("funcname", "muB");
    model.component("comp1").material("mat7").propertyGroup("def").func("an2").set("expr", "0.6*eta(T)");
    model.component("comp1").material("mat7").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat7").propertyGroup("def").func("an2").set("fununit", "Pa*s");
    model.component("comp1").material("mat7").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").material("mat7").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"200"});
    model.component("comp1").material("mat7").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "200", "1600"}});
    model.component("comp1").material("mat7").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat7").propertyGroup("def").set("molarmass", "");
    model.component("comp1").material("mat7").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp1").material("mat7").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)"});
    model.component("comp1").material("mat7").propertyGroup("def").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat7").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp1").material("mat7").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat7").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat7").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat7").propertyGroup("def").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat7").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp1").material("mat7").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat7").propertyGroup("def").set("density", "rho(pA,T)");
    model.component("comp1").material("mat7").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat7").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp1").material("mat7").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat7").propertyGroup("def").addInput("pressure");
    model.component("comp1").material("mat7").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp1").material("mat7").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat7").propertyGroup("NonlinearModel").label("Nonlinear model");
    model.component("comp1").material("mat7").propertyGroup("NonlinearModel").set("BA", "def.gamma-1");
    model.component("comp1").material("mat7").propertyGroup("idealGas").label("Ideal gas");
    model.component("comp1").material("mat7").propertyGroup("idealGas").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat7").propertyGroup("idealGas").func("Cp").set("arg", "T");
    model.component("comp1").material("mat7").propertyGroup("idealGas").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat7").propertyGroup("idealGas").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat7").propertyGroup("idealGas").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat7").propertyGroup("idealGas").set("Rs", "R_const/Mn");
    model.component("comp1").material("mat7").propertyGroup("idealGas").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat7").propertyGroup("idealGas").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat7").propertyGroup("idealGas").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat7").propertyGroup("idealGas").addInput("temperature");
    model.component("comp1").material("mat7").propertyGroup("idealGas").addInput("pressure");
    model.component("comp1").material("mat7").materialType("nonSolid");
    model.component("comp1").material("mat7").label("\u7a7a\u6c14\u8fb9\u754c");
    model.component("comp1").material("mat7").selection().geom("geom1", 2);
    model.component("comp1").material("mat7").selection().named("adj1");
    model.component("comp1").material("mat7").propertyGroup().create("shell", "shell", "Shell");
    model.component("comp1").material("mat7").propertyGroup("shell").set("lth", new String[]{"50[um]"});

    model.component("comp1").common().create("ampr1", "AmbientProperties");
    model.component("comp1").common("ampr1").set("AmbientData", "MeteorologicalData2021");
    model.component("comp1").common("ampr1").set("ashrae2021Station", "083910");
    model.component("comp1").common("ampr1").setIndex("ashrae2021LocalTime", 10, 0);

    model.component("comp1").physics("ht").feature("fluid1").feature("phc1").set("dT_pc12", "3.5[K]");
    model.component("comp1").physics("ht").feature("fluid1").feature("phc1").set("MaterialPhase1", "mat5");
    model.component("comp1").physics("ht").feature("fluid1").feature("phc1").set("MaterialPhase2", "mat4");
    model.component("comp1").physics("ht").create("id1", "IsothermalDomain", 3);
    model.component("comp1").physics("ht").feature("id1").selection().named("geom1_csel3_dom");
    model.component("comp1").physics("ht").feature("idi1").set("InterfaceType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("idi1").set("h", "5[W/(m^2*K)]");
    model.component("comp1").physics("ht").feature("init1").set("Tinit", "20[degC]");
    model.component("comp1").physics("ht").create("init2", "init", 3);
    model.component("comp1").physics("ht").feature("init2").selection().named("geom1_csel1_dom");
    model.component("comp1").physics("ht").feature("init2").set("Tinit", "5[degC]");
    model.component("comp1").physics("ht").create("init3", "init", 3);
    model.component("comp1").physics("ht").feature("init3").selection().named("geom1_csel2_dom");
    model.component("comp1").physics("ht").feature("init3").set("Tinit", "-5[degC]");
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 2);
    model.component("comp1").physics("ht").feature("hf1").selection().named("sel2");
    model.component("comp1").physics("ht").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf1").set("h", "5[W/(m^2*K)]");
    model.component("comp1").physics("ht").feature("hf1").set("Text_src", "root.comp1.ampr1.T_amb");

    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().named("geom1_csel2_dom");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature().create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hauto", 2);
    model.component("comp1").mesh("mesh1").run("ftet1");
    model.component("comp1").mesh("mesh1").create("ftet2", "FreeTet");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tunit", "h");
    model.study("std1").feature("time").set("tlist", "range(0,3,72)");
    model.study("std1").feature("time").set("usertol", true);
    model.study("std1").feature("time").set("rtol", "1e-3");
    model.study("std1").createAutoSequences("all");

    model.component("comp1").probe("dom1").genResult("none");
    model.component("comp1").probe("dom2").genResult("none");
    model.component("comp1").probe("dom3").genResult("none");
    model.component("comp1").probe("dom4").genResult("none");

    model.sol("sol1").runAll();

    model.result().dataset().create("lshl1", "LayeredMaterial");
    model.result().dataset("lshl1").set("data", "dset1");
    model.result().dataset("lshl1").selection().geom("geom1", 2);
    model.result().dataset("lshl1").selection()
         .set(6, 14, 15, 16, 17, 19, 23, 25, 27, 28, 29, 30, 32, 33, 34, 35, 36, 37, 39, 40, 41, 42, 43, 44, 45, 46, 51, 53, 58, 60, 61, 62, 63, 64, 66);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u6e29\u5ea6 (ht)");
    model.result("pg2").selection().geom("geom1", 3);
    model.result("pg2").selection().set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
    model.result("pg2").feature().create("vol1", "Volume");
    model.result("pg2").feature("vol1").label("\u57df");
    model.result("pg2").feature("vol1").set("showsolutionparams", "on");
    model.result("pg2").feature("vol1").set("solutionparams", "parent");
    model.result("pg2").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg2").feature("vol1").set("smooth", "internal");
    model.result("pg2").feature("vol1").set("showsolutionparams", "on");
    model.result("pg2").feature("vol1").set("data", "parent");
    model.result("pg2").feature().create("vol2", "Volume");
    model.result("pg2").feature("vol2").label("\u591a\u5c42\u58f3");
    model.result("pg2").feature("vol2").set("data", "lshl1");
    model.result("pg2").feature("vol2").setIndex("looplevel", 25, 0);
    model.result("pg2").feature("vol2").set("solutionparams", "parent");
    model.result("pg2").feature("vol2").set("titletype", "none");
    model.result("pg2").feature("vol2").set("smooth", "internal");
    model.result("pg2").feature("vol2").set("data", "lshl1");
    model.result("pg2").feature("vol2").set("inheritplot", "vol1");
    model.result("pg2").feature().create("line1", "Line");
    model.result("pg2").feature("line1").label("\u591a\u5c42\u58f3\u8fb9");
    model.result("pg2").feature("line1").set("data", "lshl1");
    model.result("pg2").feature("line1").setIndex("looplevel", 25, 0);
    model.result("pg2").feature("line1").set("solutionparams", "parent");
    model.result("pg2").feature("line1").set("expr", "1");
    model.result("pg2").feature("line1").set("titletype", "none");
    model.result("pg2").feature("line1").set("coloring", "uniform");
    model.result("pg2").feature("line1").set("color", "fromtheme");
    model.result("pg2").feature("line1").set("smooth", "internal");
    model.result("pg2").feature("line1").set("data", "lshl1");
    model.result("pg2").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"temperature", "\u6e29\u5ea6", "K", "K"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "\u00b0C", 0, 3);
    model.result().configuration("prfu1").apply();
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg1").label("\u5185\u88c5\u7269\u6e29\u5ea6");
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "\u63a2\u9488\u6e29\u5ea6\u968f\u65f6\u95f4\u7684\u53d8\u5316");
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "\u6e29\u5ea6 (\u00b0C)");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").set("legendpos", "uppermiddle");
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg1").feature("tblp1").set("linewidth", 2);
    model.result("pg1").feature("tblp1").set("legendmethod", "manual");
    model.result("pg1").feature("tblp1").setIndex("legends", "\u6700\u5c0f\u5185\u88c5\u7269", 0);
    model.result("pg1").feature("tblp1").setIndex("legends", "\u5e73\u5747\u5185\u88c5\u7269", 1);
    model.result("pg1").feature("tblp1").setIndex("legends", "\u6700\u5927\u5185\u88c5\u7269", 2);
    model.result("pg1").feature("tblp1").setIndex("legends", "\u5e73\u5747\u51b0\u7528\u91cf", 3);
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg1").create("glob1", "Global");
    model.result("pg1").feature("glob1").set("markerpos", "datapoints");
    model.result("pg1").feature("glob1").set("linewidth", "preference");
    model.result("pg1").feature("glob1").setIndex("expr", "2[degC]", 0);
    model.result("pg1").feature("glob1").setIndex("unit", "degC", 0);
    model.result("pg1").feature("glob1").setIndex("descr", "\u6e29\u5ea6\u9650\u5236\uff0c\u4e0b\u754c", 0);
    model.result("pg1").feature("glob1").set("titletype", "none");
    model.result("pg1").feature("glob1").set("linestyle", "dotted");
    model.result("pg1").feature("glob1").set("linecolor", "blue");
    model.result("pg1").feature("glob1").set("legend", false);
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg1").create("glob2", "Global");
    model.result("pg1").feature("glob2").set("markerpos", "datapoints");
    model.result("pg1").feature("glob2").set("linewidth", "preference");
    model.result("pg1").feature("glob2").setIndex("expr", "8[degC]", 0);
    model.result("pg1").feature("glob2").setIndex("unit", "degC", 0);
    model.result("pg1").feature("glob2").setIndex("descr", "\u6e29\u5ea6\u9650\u5236\uff0c\u4e0a\u754c", 0);
    model.result("pg1").feature("glob2").set("titletype", "none");
    model.result("pg1").feature("glob2").set("linestyle", "dotted");
    model.result("pg1").feature("glob2").set("linecolor", "red");
    model.result("pg1").feature("glob2").set("legend", false);
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 9, 0);
    model.result("pg2").create("iso1", "Isosurface");
    model.result("pg2").feature("iso1").set("expr", "ht.alpha12");
    model.result("pg2").feature("iso1").set("descr", "\u76f8 1 \u4e0e 2 \u4e4b\u95f4\u7684\u76f8\u53d8");
    model.result("pg2").feature("iso1").set("levelmethod", "levels");
    model.result("pg2").feature("iso1").set("levels", 0.5);
    model.result("pg2").feature("iso1").set("coloring", "uniform");
    model.result("pg2").feature("iso1").set("color", "white");
    model.result("pg2").feature("iso1").set("colorlegend", false);
    model.result("pg2").run();
    model.result().numerical().create("av1", "AvVolume");
    model.result().numerical("av1").selection().set(4);
    model.result().numerical("av1").setIndex("expr", "ht.theta1", 0);
    model.result().numerical("av1").setIndex("descr", "", 0);
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u4f53\u79ef\u5e73\u5747 1");
    model.result().numerical("av1").set("table", "tbl2");
    model.result().numerical("av1").setResult();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u51b0\u5206\u6570");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "\u4f53\u79ef\u5206\u6570");
    model.result("pg3").set("titletype", "manual");
    model.result("pg3").set("title", "\u5171\u6676\u677f\u4e2d\u7684\u51b0\u5206\u6570");
    model.result("pg3").create("tblp1", "Table");
    model.result("pg3").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg3").feature("tblp1").set("linewidth", "preference");
    model.result("pg3").feature("tblp1").set("table", "tbl2");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u73af\u5883\u6e29\u5ea6");
    model.result("pg4").set("showlegends", false);
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").set("expr", new String[]{"ampr1.T_amb"});
    model.result("pg4").feature("glob1").set("descr", new String[]{"\u73af\u5883\u6e29\u5ea6"});
    model.result("pg4").feature("glob1").set("unit", new String[]{"\u00b0C"});
    model.result("pg4").run();
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();

    model.title("\u6052\u6e29\u7bb1");

    model
         .description("\u672c\u4f8b\u6c42\u89e3\u6052\u6e29\u7bb1\u4e2d\u7684\u4f20\u70ed\u95ee\u9898\uff0c\u6052\u6e29\u7bb1\u7684\u4f5c\u7528\u662f\u8fd0\u8f93\u9700\u8981 24\u00a0\u5c0f\u65f6\u51b7\u85cf\u4fdd\u5b58\u7684\u533b\u7528\u6750\u6599\u7b49\u7269\u54c1\u3002\u5728\u8fd9\u79cd\u60c5\u51b5\u4e0b\uff0c\u7bb1\u4f53\u4e0d\u4ec5\u9700\u8981\u4f7f\u5176\u4e2d\u7684\u7269\u54c1\u957f\u65f6\u95f4\u4fdd\u6301\u4f4e\u6e29\uff0c\u8fd8\u5fc5\u987b\u9075\u5faa\u50a8\u5b58\u6e29\u5ea6\u9650\u5236\u3002\u8be5\u6a21\u578b\u4e2d\u7684\u6e29\u5ea6\u9650\u5236\u533a\u95f4\u4e3a 2\u00b0C \u5230 8\u00b0C\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("isothermal_box.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
