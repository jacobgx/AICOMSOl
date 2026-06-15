/*
 * accelerated_life_testing.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:02 by COMSOL 6.3.0.290. */
public class accelerated_life_testing {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Fatigue_Module\\Strain_Life");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new double[]{2, 0.5});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new double[]{0.5, 0.25});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new double[]{0, 0.5});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new double[]{2, 0.5});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new double[]{0, 0.75});
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", 0.25, 0);
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", 0.625, 1);
    model.component("comp1").geom("geom1").run("pt1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.component("comp1").geom("geom1").run();

    model.param().set("A_I", "8.03e-12[1/s]");
    model.param().descr("A_I", "\u4f4e\u5e94\u529b\u8815\u53d8\u7387\u7cfb\u6570");
    model.param().set("n_I", "3");
    model.param().descr("n_I", "\u4f4e\u5e94\u529b\u8815\u53d8\u7387\u6307\u6570");
    model.param().set("A_II", "1.96e-23[1/s]");
    model.param().descr("A_II", "\u9ad8\u5e94\u529b\u8815\u53d8\u7387\u7cfb\u6570");
    model.param().set("n_II", "12");
    model.param().descr("n_II", "\u9ad8\u5e94\u529b\u8815\u53d8\u7387\u6307\u6570");
    model.param().set("s_ref", "1[MPa]");
    model.param().descr("s_ref", "\u53c2\u8003\u5e94\u529b");

    model.func().create("int1", "Interpolation");
    model.func("int1").set("funcname", "thermLC");
    model.func("int1").setIndex("table", 0, 0, 0);
    model.func("int1").setIndex("table", 25, 0, 1);
    model.func("int1").setIndex("table", 15, 1, 0);
    model.func("int1").setIndex("table", 100, 1, 1);
    model.func("int1").setIndex("table", 30, 2, 0);
    model.func("int1").setIndex("table", 100, 2, 1);
    model.func("int1").setIndex("table", 45, 3, 0);
    model.func("int1").setIndex("table", 25, 3, 1);
    model.func("int1").setIndex("table", 60, 4, 0);
    model.func("int1").setIndex("table", 25, 4, 1);
    model.func("int1").setIndex("argunit", "min", 0);
    model.func("int1").setIndex("fununit", "degC", 0);

    model.component("comp1").physics("solid").feature("lemm1").set("CalculateDissipatedEnergy", true);
    model.component("comp1").physics("solid").feature("lemm1").create("te1", "ThermalExpansion", 2);
    model.component("comp1").physics("solid").feature("lemm1").feature("te1")
         .set("minput_temperature_src", "userdef");
    model.component("comp1").physics("solid").feature("lemm1").feature("te1")
         .set("minput_temperature", "thermLC(t)");
    model.component("comp1").physics("solid").feature("lemm1").create("cmm1", "Creep2", 2);
    model.component("comp1").physics("solid").feature("lemm1").feature("cmm1").selection().set(2);
    model.component("comp1").physics("solid").feature("lemm1").feature("cmm1").set("A_nor_mat", "userdef");
    model.component("comp1").physics("solid").feature("lemm1").feature("cmm1").set("A_nor", "A_I");
    model.component("comp1").physics("solid").feature("lemm1").feature("cmm1").set("sigRef_nor_mat", "userdef");
    model.component("comp1").physics("solid").feature("lemm1").feature("cmm1").set("sigRef_nor", "s_ref");
    model.component("comp1").physics("solid").feature("lemm1").feature("cmm1").set("n_nor_mat", "userdef");
    model.component("comp1").physics("solid").feature("lemm1").feature("cmm1").set("n_nor", "n_I");
    model.component("comp1").physics("solid").feature("lemm1").feature("cmm1").create("acr1", "AdditionalCreep", 2);
    model.component("comp1").physics("solid").feature("lemm1").feature("cmm1").feature("acr1").selection().set(2);
    model.component("comp1").physics("solid").feature("lemm1").feature("cmm1").feature("acr1")
         .set("A_nor_mat", "userdef");
    model.component("comp1").physics("solid").feature("lemm1").feature("cmm1").feature("acr1").set("A_nor", "A_II");
    model.component("comp1").physics("solid").feature("lemm1").feature("cmm1").feature("acr1")
         .set("sigRef_nor_mat", "userdef");
    model.component("comp1").physics("solid").feature("lemm1").feature("cmm1").feature("acr1")
         .set("sigRef_nor", "s_ref");
    model.component("comp1").physics("solid").feature("lemm1").feature("cmm1").feature("acr1")
         .set("n_nor_mat", "userdef");
    model.component("comp1").physics("solid").feature("lemm1").feature("cmm1").feature("acr1").set("n_nor", "n_II");
    model.component("comp1").physics("solid").create("sym1", "SymmetrySolid", 1);
    model.component("comp1").physics("solid").feature("sym1").selection().set(11, 12);
    model.component("comp1").physics("solid").create("fix1", "Fixed", 0);
    model.component("comp1").physics("solid").feature("fix1").selection().set(8);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("PCB");
    model.component("comp1").material("mat1").selection().set(1);
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"22[GPa]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"0.4"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"21e-6"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u710a\u9521");
    model.component("comp1").material("mat2").selection().set(2);
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", new String[]{"50[GPa]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", new String[]{"0.4"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"21e-6"});
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").label("\u6c27\u5316\u94dd");
    model.component("comp1").material("mat3").selection().set(3);
    model.component("comp1").material("mat3").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("E", new String[]{"300[GPa]"});
    model.component("comp1").material("mat3").propertyGroup("Enu").set("nu", new String[]{"0.22"});
    model.component("comp1").material("mat3").propertyGroup("def").set("density", new String[]{"1"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"8e-6"});

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1")
         .set("s_mises", "sqrt(solid.sx^2+solid.sy^2+solid.sz^2-solid.sx*solid.sy-solid.sy*solid.sz-solid.sz*solid.sx+3*solid.sxy^2+3*solid.syz^2+3*solid.sxz^2+(1e-6[MPa])^2)");
    model.component("comp1").variable("var1").set("alpha_I", "3/2*A_I/s_mises*(s_mises/s_ref)^n_I");
    model.component("comp1").variable("var1").set("alpha_II", "3/2*A_II/s_mises*(s_mises/s_ref)^n_II");

    model.component("comp1").physics().create("dode", "DomainODE", "geom1");
    model.component("comp1").physics("dode").prop("EquationForm").set("form", "Automatic");
    model.component("comp1").physics().create("dode2", "DomainODE", "geom1");
    model.component("comp1").physics("dode2").prop("EquationForm").set("form", "Automatic");
    model.component("comp1").physics().create("dode3", "DomainODE", "geom1");
    model.component("comp1").physics("dode3").prop("EquationForm").set("form", "Automatic");
    model.component("comp1").physics("dode").selection().set(2);
    model.component("comp1").physics("dode").prop("Units").setIndex("CustomSourceTermUnit", "1/s", 0, 0);
    model.component("comp1").physics("dode").field("dimensionless").field("ec_I");
    model.component("comp1").physics("dode").field("dimensionless")
         .component(new String[]{"u2", "ec_I2", "ec_I3", "ec_I4", "ec_I5"});
    model.component("comp1").physics("dode").field("dimensionless").component(1, "ecx_I");
    model.component("comp1").physics("dode").field("dimensionless").component(2, "ecy_I");
    model.component("comp1").physics("dode").field("dimensionless").component(3, "ecz_I");
    model.component("comp1").physics("dode").field("dimensionless").component(4, "ecxy_I");
    model.component("comp1").physics("dode").field("dimensionless").component(5, "ece_I");
    model.component("comp1").physics("dode").prop("ShapeProperty").set("shapeFunctionType", "shgp");
    model.component("comp1").physics("dode").prop("ShapeProperty").set("order", 4);
    model.component("comp1").physics("dode").feature("dode1").setIndex("f", "alpha_I*solid.sdevx", 0);
    model.component("comp1").physics("dode").feature("dode1").setIndex("f", "alpha_I*solid.sdevy", 1);
    model.component("comp1").physics("dode").feature("dode1").setIndex("f", "alpha_I*solid.sdevz", 2);
    model.component("comp1").physics("dode").feature("dode1").setIndex("f", "alpha_I*solid.sdevxy", 3);
    model.component("comp1").physics("dode").feature("dode1")
         .setIndex("f", "(2/3*(d(ecx_I,TIME)^2+d(ecy_I,TIME)^2+d(ecz_I,TIME)^2+2*(d(ecxy_I,TIME)^2))+(1e-20))^0.5", 4);
    model.component("comp1").physics("dode2").selection().set(2);
    model.component("comp1").physics("dode2").prop("Units").setIndex("CustomSourceTermUnit", "1/s", 0, 0);
    model.component("comp1").physics("dode2").field("dimensionless").field("ec_II");
    model.component("comp1").physics("dode2").field("dimensionless")
         .component(new String[]{"u3", "ec_II2", "ec_II3", "ec_II4", "ec_II5"});
    model.component("comp1").physics("dode2").field("dimensionless").component(1, "ecx_II");
    model.component("comp1").physics("dode2").field("dimensionless").component(2, "ecy_II");
    model.component("comp1").physics("dode2").field("dimensionless").component(3, "ecz_II");
    model.component("comp1").physics("dode2").field("dimensionless").component(4, "ecxy_II");
    model.component("comp1").physics("dode2").field("dimensionless").component(5, "ece_II");
    model.component("comp1").physics("dode2").prop("ShapeProperty").set("shapeFunctionType", "shgp");
    model.component("comp1").physics("dode2").prop("ShapeProperty").set("order", 4);
    model.component("comp1").physics("dode2").feature("dode1").setIndex("f", "alpha_II*solid.sdevx", 0);
    model.component("comp1").physics("dode2").feature("dode1").setIndex("f", "alpha_II*solid.sdevy", 1);
    model.component("comp1").physics("dode2").feature("dode1").setIndex("f", "alpha_II*solid.sdevz", 2);
    model.component("comp1").physics("dode2").feature("dode1").setIndex("f", "alpha_II*solid.sdevxy", 3);
    model.component("comp1").physics("dode2").feature("dode1")
         .setIndex("f", "(2/3*(d(ecx_II,TIME)^2+d(ecy_II,TIME)^2+d(ecz_II,TIME)^2+2*(d(ecxy_II,TIME)^2))+(1e-20))^0.5", 4);
    model.component("comp1").physics("dode3").selection().set(2);
    model.component("comp1").physics("dode3").prop("Units").set("DependentVariableQuantity", "energydensity");
    model.component("comp1").physics("dode3").prop("Units").setIndex("CustomSourceTermUnit", "J/(s*m^3)", 0, 0);
    model.component("comp1").physics("dode3").field("dimensionless").field("Wc");
    model.component("comp1").physics("dode3").field("dimensionless").component(new String[]{"u4", "Wc2"});
    model.component("comp1").physics("dode3").field("dimensionless").component(1, "Wc_I");
    model.component("comp1").physics("dode3").field("dimensionless").component(2, "Wc_II");
    model.component("comp1").physics("dode3").prop("ShapeProperty").set("shapeFunctionType", "shgp");
    model.component("comp1").physics("dode3").prop("ShapeProperty").set("order", 4);
    model.component("comp1").physics("dode3").feature("dode1").setIndex("f", "d(ece_I,TIME)*s_mises", 0);
    model.component("comp1").physics("dode3").feature("dode1").setIndex("f", "d(ece_II,TIME)*s_mises", 1);

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(3);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 6);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(4);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", 12);
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hauto", 4);
    model.component("comp1").mesh("mesh1").run();

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/solid", true);
    model.study("std1").feature("time").setSolveFor("/physics/dode", true);
    model.study("std1").feature("time").setSolveFor("/physics/dode2", true);
    model.study("std1").feature("time").setSolveFor("/physics/dode3", true);
    model.study("std1").feature("time").set("tunit", "min");
    model.study("std1").feature("time")
         .set("tlist", "range(0,0.5,14.5) range(14.6,0.1,15.4) range(15.5,0.5,29.5) range(29.6,0.1,30.4) range(30.5,0.5,44.5) range(44.6,0.1,45.4) range(45.5,0.5,60)");
    model.study("std1").showAutoSequences("all");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 145, 0);
    model.result("pg1").label("\u5e94\u529b (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg1").feature("surf1").set("threshold", "manual");
    model.result("pg1").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("surf1").set("colortable", "Rainbow");
    model.result("pg1").feature("surf1").set("colortabletrans", "none");
    model.result("pg1").feature("surf1").set("colorscalemode", "linear");
    model.result("pg1").feature("surf1").set("resolution", "normal");
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "v"});
    model.result("pg1").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 145, 0);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").label("\u57df\u5e38\u5fae\u5206\u548c\u5fae\u5206\u4ee3\u6570\u65b9\u7a0b");
    model.result("pg2").feature("surf1").set("expr", "ecx_I");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").setIndex("looplevel", 145, 0);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").label("\u57df\u5e38\u5fae\u5206\u548c\u5fae\u5206\u4ee3\u6570\u65b9\u7a0b 2");
    model.result("pg3").feature("surf1").set("expr", "ecx_II");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").setIndex("looplevel", 145, 0);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").label("\u57df\u5e38\u5fae\u5206\u548c\u5fae\u5206\u4ee3\u6570\u65b9\u7a0b 3");
    model.result("pg4").feature("surf1").set("expr", "Wc_I");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").label("\u8815\u53d8\u5e94\u53d8 I (dode)");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("expr", "ece_I");
    model.result("pg3").run();
    model.result("pg3").label("\u8815\u53d8\u5e94\u53d8 II (dode2)");
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("expr", "ece_II");
    model.result("pg4").run();
    model.result("pg4").label("\u8017\u6563\u80fd (dode3)");
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u8815\u53d8\u5e94\u53d8\u5386\u53f2");
    model.result("pg5").create("ptgr1", "PointGraph");
    model.result("pg5").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg5").feature("ptgr1").set("linewidth", "preference");
    model.result("pg5").feature("ptgr1").selection().set(5);
    model.result("pg5").feature("ptgr1").set("expr", "solid.eclGp11");
    model.result("pg5").feature("ptgr1").set("legend", true);
    model.result("pg5").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg5").feature("ptgr1").setIndex("legends", "ec_x", 0);
    model.result("pg5").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg5").run();
    model.result("pg5").feature("ptgr2").set("expr", "solid.eclGp22");
    model.result("pg5").feature("ptgr2").setIndex("legends", "ec_y", 0);
    model.result("pg5").feature().duplicate("ptgr3", "ptgr2");
    model.result("pg5").run();
    model.result("pg5").feature("ptgr3").set("expr", "solid.eclGp33");
    model.result("pg5").feature("ptgr3").setIndex("legends", "ec_z", 0);
    model.result("pg5").feature().duplicate("ptgr4", "ptgr3");
    model.result("pg5").run();
    model.result("pg5").feature("ptgr4").set("expr", "solid.eclGp12");
    model.result("pg5").feature("ptgr4").setIndex("legends", "ec_xy", 0);
    model.result("pg5").run();
    model.result("pg5").set("legendpos", "lowerright");
    model.result("pg5").set("titletype", "none");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "\u8815\u53d8\u5e94\u53d8 (1)");
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").create("ptgr1", "PointGraph");
    model.result("pg6").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg6").feature("ptgr1").set("linewidth", "preference");
    model.result("pg6").feature("ptgr1").set("expr", "solid.eceGp");
    model.result("pg6").feature("ptgr1").selection().set(5);
    model.result("pg6").feature("ptgr1").set("legend", true);
    model.result("pg6").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg6").feature("ptgr1").setIndex("legends", "ec (solid)", 0);
    model.result("pg6").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg6").run();
    model.result("pg6").feature("ptgr2").set("expr", "ece_I");
    model.result("pg6").feature("ptgr2").setIndex("legends", "ece_I (dode)", 0);
    model.result("pg6").feature().duplicate("ptgr3", "ptgr2");
    model.result("pg6").run();
    model.result("pg6").feature("ptgr3").set("expr", "ece_II");
    model.result("pg6").feature("ptgr3").setIndex("legends", "ece_II (dode2)", 0);
    model.result("pg6").feature().duplicate("ptgr4", "ptgr3");
    model.result("pg6").run();
    model.result("pg6").feature("ptgr4").set("expr", "ece_I+ece_II");
    model.result("pg6").feature("ptgr4").setIndex("legends", "ece_I+ece_II", 0);
    model.result("pg6").feature("ptgr4").set("linestyle", "dotted");
    model.result("pg6").feature("ptgr4").set("linewidth", 4);
    model.result("pg6").run();
    model.result("pg6").label("\u6709\u6548\u8815\u53d8\u5386\u53f2");
    model.result("pg6").set("legendpos", "upperleft");
    model.result("pg6").set("titletype", "none");
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6").set("ylabel", "\u6709\u6548\u8815\u53d8\u5e94\u53d8 (1)");
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").run();
    model.result("pg7").label("\u8815\u53d8\u8017\u6563\u5386\u53f2");
    model.result("pg7").run();
    model.result("pg7").feature("ptgr1").set("expr", "solid.Wc");
    model.result("pg7").feature("ptgr1").setIndex("legends", "Wc (solid)", 0);
    model.result("pg7").run();
    model.result("pg7").feature("ptgr2").set("expr", "Wc_I");
    model.result("pg7").feature("ptgr2").setIndex("legends", "Wc_I (dode3)", 0);
    model.result("pg7").run();
    model.result("pg7").feature("ptgr3").set("expr", "Wc_II");
    model.result("pg7").feature("ptgr3").setIndex("legends", "Wc_II (dode3)", 0);
    model.result("pg7").run();
    model.result("pg7").feature("ptgr4").set("expr", "Wc_I+Wc_II");
    model.result("pg7").feature("ptgr4").setIndex("legends", "Wc_I+Wc_II", 0);
    model.result("pg7").run();
    model.result("pg7").set("ylabel", "\u8815\u53d8\u8017\u6563 (J/m^3)");
    model.result("pg7").run();

    model.component("comp1").physics().create("ftg", "Fatigue", "geom1");

    model.study("std1").feature("time").setSolveFor("/physics/ftg", false);

    model.component("comp1").physics("ftg").create("elif1", "StrainLifeModel", 2);
    model.component("comp1").physics("ftg").feature("elif1").selection().set(2);
    model.component("comp1").physics("ftg").feature("elif1").set("ftgElifCrit", "CoffinManson");
    model.component("comp1").physics("ftg").feature("elif1").set("strainTypeCM", "User");
    model.component("comp1").physics("ftg").feature("elif1").set("ftgUserExpr", "2*ecxy_II");
    model.component("comp1").physics("ftg").feature("elif1").set("epsilonf_CM_mat", "userdef");
    model.component("comp1").physics("ftg").feature("elif1").set("epsilonf_CM", 0.587);
    model.component("comp1").physics("ftg").feature("elif1").set("c_CM_mat", "userdef");
    model.component("comp1").physics("ftg").feature("elif1").set("c_CM", -0.61);
    model.component("comp1").physics().create("ftg2", "Fatigue", "geom1");

    model.study("std1").feature("time").setSolveFor("/physics/ftg2", false);

    model.component("comp1").physics("ftg2").create("ener1", "EnergyBasedModel", 2);
    model.component("comp1").physics("ftg2").feature("ener1").selection().set(2);
    model.component("comp1").physics("ftg2").feature("ener1").set("ftgEnerType", "User");
    model.component("comp1").physics("ftg2").feature("ener1").set("ftgUserExpr", "Wc_II");
    model.component("comp1").physics("ftg2").feature("ener1").set("Wf_Morrow_mat", "userdef");
    model.component("comp1").physics("ftg2").feature("ener1").set("Wf_Morrow", "74e6");
    model.component("comp1").physics("ftg2").feature("ener1").set("m_Morrow_mat", "userdef");
    model.component("comp1").physics("ftg2").feature("ener1").set("m_Morrow", -0.79);

    model.study().create("std2");
    model.study("std2").create("ftge", "Fatigue");
    model.study("std2").feature("ftge").set("ftplistmethod", "manual");
    model.study("std2").feature("ftge").set("solnum", "auto");
    model.study("std2").feature("ftge").set("usesol", "off");
    model.study("std2").feature("ftge").set("outputmap", new String[]{});
    model.study("std2").feature("ftge").setSolveFor("/physics/solid", false);
    model.study("std2").feature("ftge").setSolveFor("/physics/dode", false);
    model.study("std2").feature("ftge").setSolveFor("/physics/dode2", false);
    model.study("std2").feature("ftge").setSolveFor("/physics/dode3", false);
    model.study("std2").feature("ftge").setSolveFor("/physics/ftg", true);
    model.study("std2").feature("ftge").setSolveFor("/physics/ftg2", true);
    model.study("std2").feature("ftge").set("usesol", true);
    model.study("std2").feature("ftge").set("notsolmethod", "sol");
    model.study("std2").feature("ftge").set("notstudy", "std1");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg8", "PlotGroup2D");
    model.result("pg8").set("data", "dset2");
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("expr", new String[]{"ftg.ctf"});
    model.result("pg8").feature("surf1").set("colortable", "Rainbow");
    model.result("pg8").feature("surf1").set("colortabletrans", "none");
    model.result("pg8").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg8").feature("surf1").set("colortablerev", true);
    model.result("pg8").feature("surf1").set("colortable", "Traffic");
    model.result("pg8").label("\u5931\u6548\u5faa\u73af\u6b21\u6570 (ftg)");
    model.result("pg8").feature("surf1").create("mrkr1", "Marker");
    model.result("pg8").feature("surf1").feature("mrkr1").set("precision", 3);
    model.result("pg8").feature("surf1").feature("mrkr1").set("display", "min");
    model.result().create("pg9", "PlotGroup2D");
    model.result("pg9").set("data", "dset2");
    model.result("pg9").create("surf1", "Surface");
    model.result("pg9").feature("surf1").set("expr", new String[]{"ftg2.ctf"});
    model.result("pg9").feature("surf1").set("colortable", "Rainbow");
    model.result("pg9").feature("surf1").set("colortabletrans", "none");
    model.result("pg9").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg9").feature("surf1").set("colortablerev", true);
    model.result("pg9").feature("surf1").set("colortable", "Traffic");
    model.result("pg9").label("\u5931\u6548\u5faa\u73af\u6b21\u6570 (ftg2)");
    model.result("pg9").feature("surf1").create("mrkr1", "Marker");
    model.result("pg9").feature("surf1").feature("mrkr1").set("precision", 3);
    model.result("pg9").feature("surf1").feature("mrkr1").set("display", "min");
    model.result("pg8").run();
    model.result("pg8").run();
    model.result("pg8").feature("surf1").feature("mrkr1").set("anchorpoint", "lowerleft");
    model.result("pg8").run();
    model.result("pg9").run();
    model.result("pg9").feature("surf1").feature("mrkr1").set("anchorpoint", "lowerleft");
    model.result("pg9").run();
    model.result("pg6").run();

    model.title("\u52a0\u901f\u5bff\u547d\u6d4b\u8bd5");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u7535\u5b50\u5143\u4ef6\u7684\u7b80\u5355\u793a\u610f\u56fe\u8868\u793a\u6765\u8bc4\u4f30\u7531\u6bd4\u5e94\u53d8\u6216\u6bd4\u80fd\u9a71\u52a8\u7684\u75b2\u52b3\u3002\u901a\u8fc7\u5e38\u5fae\u5206\u65b9\u7a0b\u5b9a\u4e49\u6240\u9700\u7684\u5e94\u53d8\u548c\u80fd\u91cf\u53d8\u91cf\uff0c\u5e76\u5728\u70ed\u8f7d\u8377\u5faa\u73af\u8fc7\u7a0b\u4e2d\u8fdb\u884c\u8ba1\u7b97\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("accelerated_life_testing.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
