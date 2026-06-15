/*
 * pharmaceutical_tableting_process.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:36 by COMSOL 6.3.0.290. */
public class pharmaceutical_tableting_process {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Nonlinear_Structural_Materials_Module\\Porous_Plasticity");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("para", "0", "\u53c2\u6570");
    model.param().set("H0", "12.5[mm]", "\u7c89\u672b\u6a21\u5177\u7684\u521d\u59cb\u9ad8\u5ea6");
    model.param().set("R0", "6[mm]", "\u7c89\u672b\u6a21\u5177\u7684\u521d\u59cb\u534a\u5f84");
    model.param().set("Rhof", "1.59[g/cm^3]", "\u771f\u5b9e\u7c89\u672b\u5bc6\u5ea6");
    model.param().set("Rhobulk", "0.36[g/cm^3]", "\u632f\u5b9e\u5806\u79ef\u7c89\u672b\u5bc6\u5ea6");
    model.param().set("Rhorel0", "Rhobulk/Rhof", "\u521d\u59cb\u76f8\u5bf9\u5bc6\u5ea6");
    model.param().set("A0", "pi*R0^2", "\u6a21\u5177\u533a");
    model.param().set("V0", "A0*H0", "\u521d\u59cb\u7c89\u672b\u4f53\u79ef");
    model.param().set("PowderMass", "Rhobulk*V0", "\u6a21\u5177\u5185\u7684\u7c89\u672b\u8d28\u91cf");
    model.param().set("Hf", "(PowderMass/(A0))/Rhof", "\u7c89\u672b\u6a21\u5177\u7684\u6700\u7ec8\u9ad8\u5ea6");

    model.func().create("an1", "Analytic");
    model.func("an1").label("\u6768\u6c0f\u6a21\u91cf");
    model.func("an1").set("funcname", "EE");
    model.func("an1").set("expr", "111.96*exp(4.395*x)");
    model.func("an1").setIndex("argunit", 1, 0);
    model.func("an1").set("fununit", "MPa");
    model.func("an1").setIndex("plotargs", 0.3, 0, 1);
    model.func().create("an2", "Analytic");
    model.func("an2").label("\u5c48\u670d\u5e94\u529b");
    model.func("an2").set("funcname", "sigmay");
    model.func("an2").set("expr", "0.2955*exp(4.5642*x)");
    model.func("an2").setIndex("argunit", 1, 0);
    model.func("an2").set("fununit", "MPa");
    model.func("an2").setIndex("plotargs", 0.6, 0, 1);
    model.func("an2").setIndex("plotargs", 0.875, 0, 2);
    model.func().create("an3", "Analytic");
    model.func("an3").label("\u5c48\u670d\u51fd\u6570\u53c2\u6570");
    model.func("an3").set("funcname", "a1");
    model.func("an3").set("expr", "tan((12.628*x+56.194)[deg])");
    model.func("an3").setIndex("argunit", 1, 0);
    model.func("an3").set("fununit", "1");
    model.func("an3").setIndex("plotargs", 0.6, 0, 1);
    model.func("an3").setIndex("plotargs", 0.875, 0, 2);

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"R0", "H0"});
    model.component("comp1").geom("geom1").feature().duplicate("r2", "r1");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"R0*1.1", "H0/10"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"0", "H0"});
    model.component("comp1").geom("geom1").feature().duplicate("r3", "r2");
    model.component("comp1").geom("geom1").feature("r3").set("pos", new String[]{"0", "-H0/10"});
    model.component("comp1").geom("geom1").feature().duplicate("r4", "r3");
    model.component("comp1").geom("geom1").feature("r4").set("size", new String[]{"R0/4", "H0*1.25"});
    model.component("comp1").geom("geom1").feature("r4").set("pos", new String[]{"R0", "-H0/8"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").feature("fin").set("pairtype", "contact");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop1").selection().set(7);
    model.component("comp1").cpl().duplicate("intop2", "intop1");
    model.component("comp1").cpl("intop2").selection().set(8);
    model.component("comp1").cpl("intop2").set("axisym", false);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("Punchforce", "intop1(-solid.sz)");
    model.component("comp1").variable("var1").descr("Punchforce", "\u51b2\u5934\u529b");
    model.component("comp1").variable("var1").set("Punchpressure", "Punchforce/A0");
    model.component("comp1").variable("var1").set("Rho", "PowderMass/(A0*intop2(1))", "\u51b2\u5934\u538b\u529b");
    model.component("comp1").variable("var1").descr("Rho", "\u5f53\u524d\u7c89\u672b\u5bc6\u5ea6");

    model.component("comp1").func().create("pw1", "Piecewise");
    model.component("comp1").func("pw1").set("funcname", "punchDisp");
    model.component("comp1").func("pw1").setIndex("pieces", 0, 0, 0);
    model.component("comp1").func("pw1").setIndex("pieces", 1, 0, 1);
    model.component("comp1").func("pw1").setIndex("pieces", "0.715*H0*x", 0, 2);
    model.component("comp1").func("pw1").setIndex("pieces", 1, 1, 0);
    model.component("comp1").func("pw1").setIndex("pieces", 2, 1, 1);
    model.component("comp1").func("pw1").setIndex("pieces", "0.715*H0-0.05*H0*(x-1)", 1, 2);
    model.component("comp1").func("pw1").set("argunit", "1");
    model.component("comp1").func("pw1").set("fununit", "m");

    model.component("comp1").physics("solid").selection().set(2, 3);
    model.component("comp1").physics("solid").feature("lemm1").create("popl1", "PorousPlasticity", 2);
    model.component("comp1").physics("solid").feature("lemm1").feature("popl1")
         .set("YieldFunction", "DruckerPrager");
    model.component("comp1").physics("solid").feature("lemm1").feature("popl1")
         .set("capHardeningModel", "exponential");
    model.component("comp1").physics("solid").feature("dcnt1").create("fric1", "Friction", 1);
    model.component("comp1").physics("solid").feature("dcnt1").feature("fric1").set("mu_fric", 0.1);
    model.component("comp1").physics("solid").create("rd1", "RigidDomain", 2);
    model.component("comp1").physics("solid").feature("rd1").selection().set(3);
    model.component("comp1").physics("solid").feature("rd1").set("rho_mat", "userdef");
    model.component("comp1").physics("solid").feature("rd1").create("pdr1", "PrescribedDispRot", -1);
    model.component("comp1").physics("solid").feature("rd1").feature("pdr1").set("W0", "-punchDisp(para)");
    model.component("comp1").physics("solid").create("gacc1", "GravityAcceleration", -1);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u5fae\u6676\u7ea4\u7ef4\u7d20 (MCC)");
    model.component("comp1").material("mat1").selection().set(2);
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu")
         .set("E", new String[]{"EE(solid.lemm1.popl1.rhorel)"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"0.16"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"Rhobulk"});
    model.component("comp1").material("mat1").propertyGroup()
         .create("PoroplasticModel", "PoroplasticModel", "Poroplastic_material_model");
    model.component("comp1").material("mat1").propertyGroup("PoroplasticModel")
         .set("sigmags", new String[]{"sigmay(solid.lemm1.popl1.rhorel)"});
    model.component("comp1").material("mat1").propertyGroup("PoroplasticModel").set("f0", new String[]{"1-Rhorel0"});
    model.component("comp1").material("mat1").propertyGroup()
         .create("PressureDependentPlasticity", "PressureDependentPlasticity", "Pressure_dependent_plasticity");
    model.component("comp1").material("mat1").propertyGroup("PressureDependentPlasticity")
         .set("a1yield", new String[]{"a1(solid.lemm1.popl1.rhorel)"});
    model.component("comp1").material("mat1").propertyGroup()
         .create("CapAndCutoff", "CapAndCutoff", "Cap_and_cutoff");
    model.component("comp1").material("mat1").propertyGroup("CapAndCutoff").set("pc0", new String[]{"0"});
    model.component("comp1").material("mat1").propertyGroup("CapAndCutoff").set("pcc0", new String[]{"-0.25[MPa]"});
    model.component("comp1").material("mat1").propertyGroup("CapAndCutoff").set("Kc", new String[]{"37.5[MPa]"});
    model.component("comp1").material("mat1").propertyGroup("CapAndCutoff")
         .set("epvolmax", new String[]{"-log(Hf/H0)"});

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(2, 4, 11, 12, 13, 14);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 1);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(6);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", 12);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis3", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").selection().set(5);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("numelem", 16);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "para", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "", 0);
    model.study("std1").feature("stat").setIndex("pname", "para", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(0,0.05,2)", 0);
    model.study("std1").feature("stat").setIndex("punit", 1, 0);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s1").feature("p1").set("paramtuning", true);
    model.sol("sol1").feature("s1").feature("p1").set("pinitstep", "1E-5");
    model.sol("sol1").feature("s1").feature("p1").set("pminstep", "1E-5");
    model.sol("sol1").feature("s1").feature("p1").set("porder", "auto");
    model.sol("sol1").feature("s1").feature("fc1").set("dtech", "const");
    model.sol("sol1").feature("s1").feature("fc1").set("maxiter", 8);
    model.sol("sol1").feature("s1").feature("fc1").set("ntolfact", 0.1);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 41, 0);
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
    model.result("pg1").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg1").feature("surf1").feature("def").set("scale", "1");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "w"});
    model.result("pg1").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().dataset().create("dset1solidrev", "Revolve2D");
    model.result().dataset("dset1solidrev").set("data", "dset1");
    model.result().dataset("dset1solidrev").set("revangle", 225);
    model.result().dataset("dset1solidrev").set("startangle", -90);
    model.result().dataset("dset1solidrev").set("hasspacevars", true);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1solidrev");
    model.result("pg2").setIndex("looplevel", 41, 0);
    model.result("pg2").label("\u5e94\u529b, 3D (solid)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg2").feature("surf1").set("threshold", "manual");
    model.result("pg2").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").feature("surf1").set("colortabletrans", "none");
    model.result("pg2").feature("surf1").set("colorscalemode", "linear");
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg2").feature("surf1").create("def", "Deform");
    model.result().dataset("dset1solidrev").set("hasspacevars", true);
    model.result("pg2").feature("surf1").feature("def").set("revcoordsys", "cylindrical");
    model.result("pg2").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg2").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg2").feature("surf1").feature("def").set("descractive", true);
    model.result("pg2").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg2").feature("surf1").feature("def").set("scale", "1");
    model.result("pg1").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"stress", "\u5e94\u529b\u5f20\u91cf", "N/m^2", "N/m^2"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "MPa", 0, 3);
    model.result().configuration("prfu1").set("applytosamedims", true);
    model.result().configuration("prfu1").apply();
    model.result().dataset("dset1solidrev").selection().geom("geom1", 2);
    model.result().dataset("dset1solidrev").selection().geom("geom1", 2);
    model.result().dataset("dset1solidrev").selection().set(2, 4);
    model.result("pg2").run();
    model.result("pg2").create("surf2", "Surface");
    model.result("pg2").feature("surf2").set("expr", "1");
    model.result("pg2").feature("surf2").set("titletype", "none");
    model.result("pg2").feature("surf2").create("mtrl1", "MaterialAppearance");
    model.result("pg2").run();
    model.result("pg2").feature("surf2").feature("mtrl1").set("appearance", "custom");
    model.result("pg2").feature("surf2").feature("mtrl1").set("family", "steel");
    model.result("pg2").run();
    model.result("pg2").feature("surf2").create("sel1", "Selection");
    model.result("pg2").feature("surf2").feature("sel1").selection().set(4);
    model.result("pg2").run();

    model.view("view2").set("showgrid", false);

    model.result("pg2").setIndex("looplevel", 11, 0);
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 21, 0);
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 41, 0);
    model.result("pg2").run();

    model.view("view2").set("showgrid", true);

    model.result().duplicate("pg3", "pg2");
    model.result("pg3").run();
    model.result("pg3").label("\u76f8\u5bf9\u5bc6\u5ea6");
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("expr", "solid.rhorelGp");
    model.result("pg3").feature("surf1").set("descr", "\u5f53\u524d\u76f8\u5bf9\u5bc6\u5ea6");
    model.result("pg3").feature("surf1").set("colortable", "Rainbow");
    model.result("pg3").run();

    model.view("view2").set("showgrid", false);

    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 21, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 41, 0);
    model.result("pg3").run();

    model.view("view2").set("showgrid", true);

    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").setIndex("looplevel", 41, 0);
    model.result("pg4").label("\u4f53\u79ef\u5851\u6027\u5e94\u53d8 (solid)");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"solid.epvolGp"});
    model.result("pg4").feature("surf1").set("inheritplot", "none");
    model.result("pg4").feature("surf1").set("resolution", "normal");
    model.result("pg4").feature("surf1").set("colortabletype", "discrete");
    model.result("pg4").feature("surf1").set("bandcount", 11);
    model.result("pg4").feature("surf1").set("colortable", "AuroraAustralisDark");
    model.result("pg4").feature("surf1").set("descractive", true);
    model.result("pg4").feature("surf1").set("descr", "\u4f53\u79ef\u5851\u6027\u5e94\u53d8");
    model.result("pg4").label("\u4f53\u79ef\u5851\u6027\u5e94\u53d8 (solid)");
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 21, 0);
    model.result("pg4").set("legendactive", true);
    model.result("pg4").set("legendprecision", 4);
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u8f74\u5411\u51b2\u5934\u538b\u529b vs. \u8f74\u5411\u538b\u5b9e\u5ea6");
    model.result("pg5").set("titletype", "none");
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("xlabel", "\u8f74\u5411\u538b\u5b9e\u5ea6 (mm)");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "\u8f74\u5411\u51b2\u5934\u538b\u529b (kN)");
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").setIndex("expr", "Punchpressure", 0);
    model.result("pg5").feature("glob1").setIndex("descr", "\u51b2\u5934\u538b\u529b", 0);
    model.result("pg5").feature("glob1").set("xdata", "expr");
    model.result("pg5").feature("glob1").set("xdataexpr", "punchDisp(para)");
    model.result("pg5").feature("glob1").set("xdataunit", "mm");
    model.result("pg5").feature("glob1").set("legend", false);
    model.result("pg5").run();
    model.result().dataset().create("av1", "Average");
    model.result().dataset("av1").set("intsurface", true);
    model.result().dataset("av1").set("intvolume", true);
    model.result().dataset("av1").set("showlevel", "off");
    model.result().dataset("av1").selection().geom("geom1", 2);
    model.result().dataset("av1").selection().set(2);
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u76f8\u5bf9\u5bc6\u5ea6\u548c\u4f53\u79ef\u6bd4");
    model.result("pg6").setIndex("looplevelinput", "manualindices", 0);
    model.result("pg6").setIndex("looplevelindices", "range(1,1,21)", 0);
    model.result("pg6").set("titletype", "none");
    model.result("pg6").set("xlabelactive", true);
    model.result("pg6").set("xlabel", "\u8f74\u5411\u538b\u5b9e\u5ea6 (mm)");
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6").set("ylabel", "\u76f8\u5bf9\u5bc6\u5ea6 (1)");
    model.result("pg6").set("twoyaxes", true);
    model.result("pg6").set("yseclabelactive", true);
    model.result("pg6").set("yseclabel", "\u4f53\u79ef\u6bd4 (1)");
    model.result("pg6").create("glob1", "Global");
    model.result("pg6").feature("glob1").set("markerpos", "datapoints");
    model.result("pg6").feature("glob1").set("linewidth", "preference");
    model.result("pg6").feature("glob1").setIndex("expr", "((PowderMass/(A0*intop2(1)))/Rhof)", 0);
    model.result("pg6").feature("glob1").setIndex("descr", "\u836f\u7247\u76f8\u5bf9\u5bc6\u5ea6", 0);
    model.result("pg6").feature("glob1").set("xdata", "expr");
    model.result("pg6").feature("glob1").set("xdataexpr", "punchDisp(para)");
    model.result("pg6").feature("glob1").set("xdataunit", "mm");
    model.result("pg6").feature("glob1").set("autosolution", false);
    model.result("pg6").feature().duplicate("glob2", "glob1");
    model.result("pg6").run();
    model.result("pg6").feature("glob2").set("data", "av1");
    model.result("pg6").feature("glob2").setIndex("looplevelinput", "manualindices", 0);
    model.result("pg6").feature("glob2").setIndex("looplevelindices", "range(1,1,21)", 0);
    model.result("pg6").feature("glob2").setIndex("expr", "solid.lemm1.popl1.rhorel", 0);
    model.result("pg6").feature("glob2").setIndex("descr", "\u5e73\u5747\u76f8\u5bf9\u5bc6\u5ea6", 0);
    model.result("pg6").feature().duplicate("glob3", "glob2");
    model.result("pg6").run();
    model.result("pg6").feature("glob3").setIndex("expr", "solid.J", 0);
    model.result("pg6").feature("glob3").setIndex("descr", "\u603b\u4f53\u79ef\u6bd4", 0);
    model.result("pg6").feature("glob3").setIndex("expr", "solid.Jp", 1);
    model.result("pg6").run();
    model.result("pg6").setIndex("plotonsecyaxis", true, 2, 1);
    model.result("pg6").set("legendpos", "middleleft");
    model.result("pg6").run();
    model.result("pg3").run();

    model.title("\u5236\u836f\u538b\u7247\u5de5\u827a");

    model
         .description("\u7c89\u672b\u538b\u5236\u4e0d\u4ec5\u5728\u7c89\u672b\u51b6\u91d1\u9886\u57df\u662f\u4e00\u79cd\u6d41\u884c\u7684\u5236\u9020\u5de5\u827a\uff0c\u5728\u5236\u836f\u5de5\u4e1a\u4e2d\u4e5f\u662f\u5982\u6b64\u3002\u201c\u5e26\u5e3d\u7684\u5fb7\u9c81\u514b-\u666e\u62c9\u683c\u201d\u6a21\u578b\u901a\u5e38\u7528\u4e8e\u6a21\u62df\u836f\u7269\u7c89\u672b\u7684\u538b\u5236\u8fc7\u7a0b\uff0c\u5176\u4e2d\u6750\u6599\u5c5e\u6027\u4e0e\u7c89\u672b\u5bc6\u5ea6\u76f8\u5173\u3002\u672c\u4f8b\u5bf9\u5fae\u6676\u7ea4\u7ef4\u7d20 (MCC) \u8fdb\u884c\u538b\u5236\uff0c\u5e76\u6839\u636e\u5b9e\u9a8c\u6570\u636e\u6765\u6821\u51c6\u5176\u672c\u6784\u6750\u6599\u5c5e\u6027\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("pharmaceutical_tableting_process.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
