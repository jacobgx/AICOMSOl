/*
 * modular_mixer_ke.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:15 by COMSOL 6.3.0.290. */
public class modular_mixer_ke {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Mixer_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.param().set("H", "0.5[m]");
    model.param().descr("H", "\u5bb9\u5668\u9ad8\u5ea6");
    model.param().set("T", "H");
    model.param().descr("T", "\u5bb9\u5668\u76f4\u5f84");
    model.param().set("alpha", "45[deg]");
    model.param().descr("alpha", "\u6868\u8ddd\u89d2");
    model.param().set("N_blades", "4");
    model.param().descr("N_blades", "\u659c\u53f6\u7247\u53f6\u8f6e\u7684\u53f6\u7247\u6570");
    model.param().set("B", "4");
    model.param().descr("B", "\u6321\u677f\u6570");
    model.param().set("Da", "1/2*T");
    model.param().descr("Da", "\u53f6\u8f6e\u76f4\u5f84");
    model.param().set("blade_width", "Da/5");
    model.param().descr("blade_width", "\u53f6\u8f6e\u53f6\u7247\u5bbd\u5ea6");
    model.param().set("bw", "T/12");
    model.param().descr("bw", "\u6321\u677f\u5bbd\u5ea6");
    model.param().set("C", "1/4*H");
    model.param().descr("C", "\u95f4\u9699");
    model.param().set("shaft_diameter", "1/10*Da");
    model.param().descr("shaft_diameter", "\u8f74\u5f84");

    model.geom()
         .load(new String[]{"part1"}, "Mixer_Module\\Impellers,_Axial\\pitched_blade_impeller.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi1")
         .setEntry("inputexpr", "d_hu", "blade_width*abs(cos(alpha))*1.1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "ap_ib", "alpha");
    model.component("comp1").geom("geom1").feature("pi1")
         .setEntry("inputexpr", "d_a_ib", "blade_width*cos(alpha)*0.95");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "lr_cl_ib", "0.3[1]");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "lr_cu_ib", "0.3[1]");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "n_ib", "N_blades");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "rf_ib", "0[m]");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "w_ib", "blade_width");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "w_a_ib", "0[m]");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "w_o_ib", "blade_width");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "w_cil_ib", "blade_width*0.25");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "w_ciu_ib", "blade_width*0.25");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "w_col_ib", "0[m]");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "d_im", "Da");
    model.component("comp1").geom("geom1").feature("pi1")
         .setEntry("inputexpr", "hp_im", "-blade_width*sin(alpha)/2");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "d_is", "shaft_diameter");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "pa_cs_im", 1);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "d_cs_im", "Da*1.2");
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u53f6\u8f6e\u57df");
    model.component("comp1").geom("geom1").selection().create("csel2", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel2").label("\u63a7\u5236\u57df");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetoobj", "pi1_csel3", "csel1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetoobj", "pi1_csel8", "csel2");
    model.component("comp1").geom("geom1").selection().create("csel3", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel3").label("\u65cb\u8f6c\u5185\u58c1");
    model.component("comp1").geom("geom1").selection().create("csel4", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel4").label("\u65cb\u8f6c\u58c1");
    model.component("comp1").geom("geom1").selection().create("csel5", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel5").label("\u89c6\u56fe\u6291\u5236");
    model.component("comp1").geom("geom1").selection().create("csel6", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel6").label("\u7f51\u683c");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetobnd", "pi1_csel1.bnd", "csel3");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetobnd", "pi1_csel2.bnd", "csel4");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetobnd", "pi1_csel12.bnd", "csel6");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetobnd", "pi1_csel13.bnd", "csel6");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetobnd", "pi1_csel14.bnd", "csel6");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetobnd", "pi1_csel15.bnd", "csel5");
    model.component("comp1").geom("geom1").selection().create("csel7", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel7").label("\u79fb\u9664\u8fb9");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetoedg", "pi1_sel1", "csel7");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", true);
    model.geom().load(new String[]{"part2"}, "Mixer_Module\\Shafts\\impeller_shaft.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").run("pi1");
    model.component("comp1").geom("geom1").create("pi2", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi2").set("part", "part2");
    model.component("comp1").geom("geom1").feature("pi2")
         .setEntry("inputexpr", "hp_im", "-blade_width*sin(alpha)/2");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "d_is", "shaft_diameter");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "l_is", "H-C");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selcontributetoobj", "pi2_csel1", "csel1");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selcontributetobnd", "pi2_csel1.bnd", "csel4");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selcontributetoedg", "pi2_sel1", "csel7");
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepnoncontr", true);
    model.geom().load(new String[]{"part3"}, "Mixer_Module\\Tanks\\dished_bottom_tank.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").run("pi2");
    model.component("comp1").geom("geom1").create("pi3", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi3").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi3").set("part", "part3");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "n_ba", "B");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "w_ba", "bw");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "d_im", "Da");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "d_ta", "T");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "h_ta", "H");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "hp_ta", "-C");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "rm_b_ta", "T/10");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "bo_rd_ta", "T*2");
    model.component("comp1").geom("geom1").selection().create("csel8", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel8").label("\u5185\u58c1");
    model.component("comp1").geom("geom1").selection().create("csel9", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel9").label("\u5bf9\u79f0");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selcontributetoobj", "pi3_csel1", "csel2");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selcontributetobnd", "pi3_sel5", "csel5");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selcontributetobnd", "pi3_csel7.bnd", "csel8");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selcontributetobnd", "pi3_csel2.bnd", "csel9");
    model.component("comp1").geom("geom1").feature("pi3").set("selkeepnoncontr", true);
    model.component("comp1").geom("geom1").run("pi3");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").label("\u65cb\u8f6c\u6d41\u4f53\u57df");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").named("csel2");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").named("csel1");
    model.component("comp1").geom("geom1").feature("dif1").set("repairtoltype", "relative");
    model.component("comp1").geom("geom1").feature("dif1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("dif1").set("selresultshow", "all");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new double[]{0.2, -0.2, -0.5});
    model.component("comp1").geom("geom1").feature("blk1").set("rot", 45);
    model.component("comp1").geom("geom1").feature().duplicate("blk2", "blk1");
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new double[]{-0.2, -0.2, -0.5});
    model.component("comp1").geom("geom1").run("blk2");
    model.component("comp1").geom("geom1").create("dif2", "Difference");
    model.component("comp1").geom("geom1").feature("dif2").selection("input").set("dif1");
    model.component("comp1").geom("geom1").feature("dif2").selection("input2").set("blk1", "blk2");
    model.component("comp1").geom("geom1").run("dif2");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").label("\u5e73\u538b\u70b9");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").init(0);
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("dif2", 19);
    model.component("comp1").geom("geom1").selection().create("csel10", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel10").label("\u538b\u529b\u70b9\u7ea6\u675f");
    model.component("comp1").geom("geom1").feature("sel1").set("contributeto", "csel10");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("ige1", "IgnoreEdges");
    model.component("comp1").geom("geom1").feature("ige1").selection("input").named("csel7");
    model.component("comp1").geom("geom1").run("ige1");
    model.component("comp1").geom("geom1").create("mcf1", "MeshControlFaces");
    model.component("comp1").geom("geom1").feature("mcf1").selection("input").named("csel6");
    model.component("comp1").geom("geom1").run("mcf1");

    model.title("\u6a21\u5757\u5316\u6e4d\u6d41\u6405\u62cc\u5668 - \u6a21\u677f\u6587\u4ef6");

    model
         .description("\u672c\u4f8b\u662f\u4e00\u4e2a\u6a21\u677f MPH \u6587\u4ef6\uff0c\u7528\u4e8e\u5206\u6790\u201c\u6a21\u5757\u5316\u6405\u62cc\u5668\u201d\u6a21\u578b\u7684\u6e4d\u6d41\u60c5\u51b5\uff08k-\u03b5 \u548c k-\u03c9 \u6e4d\u6d41\u6a21\u578b\uff09\u3002\u51e0\u4f55\u4f53\u4e3a\u659c\u53f6\u7247\u53f6\u8f6e\u4e0e\u789f\u5e95\u91dc\u7684\u7ec4\u5408\u7ed3\u6784\u3002\u5176\u4e2d\u4ece\u201c\u96f6\u4ef6\u5e93\u201d\u5bfc\u5165\u7528\u4e8e\u6784\u5efa\u53f6\u8f6e\u548c\u5bb9\u5668\u7684\u51e0\u4f55\u5b50\u5e8f\u5217\u3002");

    model.label("modular_mixer_turbulent_geom.mph");

    model.component("comp1").physics().create("spf", "TurbulentFlowkeps", "geom1");

    model.component("comp1").common().create("rot1", "RotatingDomain");
    model.component("comp1").common("rot1").set("rotationType", "rotationalVelocity");
    model.component("comp1").common("rot1").set("rotationalVelocityExpression", "generalRevolutionsPerTime");
    model.component("comp1").common("rot1").selection().all();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("rho", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("cs", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an3", "Analytic");
    model.component("comp1").material("mat1").label("Water, liquid");
    model.component("comp1").material("mat1").set("family", "water");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"273.15", "413.15", "1.3799566804-0.021224019151*T^1+1.3604562827E-4*T^2-4.6454090319E-7*T^3+8.9042735735E-10*T^4-9.0790692686E-13*T^5+3.8457331488E-16*T^6"}, {"413.15", "553.75", "0.00401235783-2.10746715E-5*T^1+3.85772275E-8*T^2-2.39730284E-11*T^3"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"273.15", "553.75", "12010.1471-80.4072879*T^1+0.309866854*T^2-5.38186884E-4*T^3+3.62536437E-7*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").label("Piecewise 3");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("smooth", "contd1");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("pieces", new String[][]{{"273.15", "293.15", "0.000063092789034*T^3-0.060367639882855*T^2+18.9229382407066*T-950.704055329848"}, {"293.15", "373.15", "0.000010335053319*T^3-0.013395065634452*T^2+4.969288832655160*T+432.257114008512"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").label("Piecewise 4");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"273.15", "553.75", "-0.869083936+0.00894880345*T^1-1.58366345E-5*T^2+7.97543259E-9*T^3"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").label("Interpolation");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("table", new String[][]{{"273", "1403"}, 
         {"278", "1427"}, 
         {"283", "1447"}, 
         {"293", "1481"}, 
         {"303", "1507"}, 
         {"313", "1526"}, 
         {"323", "1541"}, 
         {"333", "1552"}, 
         {"343", "1555"}, 
         {"353", "1555"}, 
         {"363", "1550"}, 
         {"373", "1543"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("interp", "piecewisecubic");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("fununit", new String[]{"m/s"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("expr", "-1/rho(T)*d(rho(T),T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").label("Analytic 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("funcname", "gamma_w");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("expr", "1+(T/Cp(T))*(alpha_p(T)*cs(T))^2");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("fununit", "1");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").label("Analytic 3");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("funcname", "muB");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("expr", "2.79*eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("an3").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an3")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an3")
         .set("plotargs", new String[][]{{"T", "273.15", "553.75"}});
    model.component("comp1").material("mat1").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(T)", "0", "0", "0", "alpha_p(T)", "0", "0", "0", "alpha_p(T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("ratioofspecificheat", "gamma_w(T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "rho(T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("temperature");

    model.component("comp1").physics("spf").create("iwbc1", "InteriorWallBC", 2);
    model.component("comp1").physics("spf").feature("iwbc1").selection().named("geom1_csel3_bnd");
    model.component("comp1").physics("spf").create("iwbc2", "InteriorWallBC", 2);
    model.component("comp1").physics("spf").feature("iwbc2").selection().named("geom1_csel8_bnd");
    model.component("comp1").physics("spf").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("spf").feature("sym1").selection().named("geom1_csel9_bnd");
    model.component("comp1").physics("spf").create("pfc1", "PeriodicFlowCondition", 2);
    model.component("comp1").physics("spf").feature("pfc1").selection().set(2, 6, 22, 23);
    model.component("comp1").physics("spf").create("prpc1", "PressurePointConstraint", 0);
    model.component("comp1").physics("spf").feature("prpc1").selection().named("geom1_csel10_pnt");

    model.component("comp1").common("rot1").selection().named("geom1_pi3_csel4_dom");
    model.component("comp1").common("rot1").set("revolutionsPerTime", "20[rpm]");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1")
         .set("tau_riw", "x*(spf.T_trac_uy+spf.T_trac_dy)-y*(spf.T_trac_ux+spf.T_trac_dx)");
    model.component("comp1").variable("var1")
         .descr("tau_riw", "\u5355\u4f4d\u9762\u79ef\u626d\u77e9\uff08\u5185\u58c1\uff09");
    model.component("comp1").variable("var1").set("tau_rw", "x*(spf.T_tracy)-y*(spf.T_tracx)");
    model.component("comp1").variable("var1")
         .descr("tau_rw", "\u5355\u4f4d\u9762\u79ef\u626d\u77e9\uff08\u65cb\u8f6c\u58c1\uff09");
    model.component("comp1").variable("var1").set("P_riw", "tau_riw*rot1.alphat");
    model.component("comp1").variable("var1")
         .descr("P_riw", "\u5355\u4f4d\u9762\u79ef\u529f\u8017\uff08\u65cb\u8f6c\u5185\u58c1\uff09");
    model.component("comp1").variable("var1").set("P_rw", "tau_rw*rot1.alphat");
    model.component("comp1").variable("var1")
         .descr("P_rw", "\u5355\u4f4d\u9762\u79ef\u529f\u8017\uff08\u65cb\u8f6c\u58c1\uff09");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop1").selection().named("geom1_csel3_bnd");
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop2").selection().named("geom1_csel4_bnd");

    model.component("comp1").mesh("mesh1").contribute("geom/detail", false);
    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", 0.02);

    model.component("comp1").view("view5").set("renderwireframe", true);

    model.component("comp1").mesh("mesh1").feature("size1").selection().set(1, 15, 16, 25);
    model.component("comp1").mesh("mesh1").run("size1");
    model.component("comp1").mesh("mesh1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("size2").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("size2").selection().set(3, 17);
    model.component("comp1").mesh("mesh1").feature("size2").set("table", "cfd");
    model.component("comp1").mesh("mesh1").feature("size2").set("hauto", 4);
    model.component("comp1").mesh("mesh1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").run("size2");
    model.component("comp1").mesh("mesh1").create("size3", "Size");
    model.component("comp1").mesh("mesh1").feature("size3").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("size3").selection().set(3);
    model.component("comp1").mesh("mesh1").feature("size3").set("table", "cfd");
    model.component("comp1").mesh("mesh1").feature("size3").set("hauto", 3);
    model.component("comp1").mesh("mesh1").feature("size3").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size3").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("size3").set("hmax", 0.0114);
    model.component("comp1").mesh("mesh1").feature("size3").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("size3").set("hmin", 0.00123);
    model.component("comp1").mesh("mesh1").feature("size3").set("hgradactive", true);
    model.component("comp1").mesh("mesh1").feature("size3").set("hgrad", 1.08);
    model.component("comp1").mesh("mesh1").create("size4", "Size");
    model.component("comp1").mesh("mesh1").feature("size4").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("size4").selection().set(9);

    model.component("comp1").view("view6").set("renderwireframe", true);

    model.component("comp1").mesh("mesh1").feature("size4").set("table", "cfd");
    model.component("comp1").mesh("mesh1").feature("size4").set("hauto", 2);
    model.component("comp1").mesh("mesh1").run();

    model.study().create("std1");
    model.study("std1").create("frrot", "FrozenRotor");
    model.study("std1").feature("frrot").set("solnum", "auto");
    model.study("std1").feature("frrot").set("notsolnum", "auto");
    model.study("std1").feature("frrot").set("outputmap", new String[]{});
    model.study("std1").feature("frrot").set("ngenAUX", "1");
    model.study("std1").feature("frrot").set("goalngenAUX", "1");
    model.study("std1").feature("frrot").set("ngenAUX", "1");
    model.study("std1").feature("frrot").set("goalngenAUX", "1");
    model.study("std1").feature("frrot").setSolveFor("/physics/spf", true);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u901f\u5ea6 (spf)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("slc1", "Slice");
    model.result("pg1").feature("slc1").label("\u5207\u9762");
    model.result("pg1").feature("slc1").set("showsolutionparams", "on");
    model.result("pg1").feature("slc1").set("smooth", "internal");
    model.result("pg1").feature("slc1").set("showsolutionparams", "on");
    model.result("pg1").feature("slc1").set("data", "parent");
    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").label("\u5916\u58c1");
    model.result().dataset("surf1").set("data", "none");
    model.result().dataset().create("surf2", "Surface");
    model.result().dataset("surf2").label("\u5185\u58c1");
    model.result().dataset("surf2").set("data", "none");
    model.result().dataset("surf1").set("data", "dset1");
    model.result().dataset("surf1").selection().geom("geom1", 2);
    model.result().dataset("surf1").selection().set(1, 3, 10, 11, 12, 13, 14, 16, 17);
    model.result().dataset("surf2").set("data", "dset1");
    model.result().dataset("surf2").selection().geom("geom1", 2);
    model.result().dataset("surf2").selection().set(9, 15);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u538b\u529b (spf)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("\u8868\u9762");
    model.result("pg2").feature("surf1").set("data", "surf1");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("expr", "p");
    model.result("pg2").feature("surf1").set("colortable", "Dipole");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "surf1");
    model.result("pg2").feature("surf1").feature().create("tran1", "Transparency");
    model.result("pg2").feature().create("slit1", "SurfaceSlit");
    model.result("pg2").feature("slit1").set("data", "surf2");
    model.result("pg2").feature("slit1").set("showsolutionparams", "on");
    model.result("pg2").feature("slit1").set("upexpr", "up(p)");
    model.result("pg2").feature("slit1").set("downexpr", "down(p)");
    model.result("pg2").feature("slit1").set("titletype", "none");
    model.result("pg2").feature("slit1").set("smooth", "internal");
    model.result("pg2").feature("slit1").set("showsolutionparams", "on");
    model.result("pg2").feature("slit1").set("data", "surf2");
    model.result("pg2").feature("slit1").set("inheritplot", "surf1");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u58c1\u5206\u8fa8\u7387 (spf)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("\u58c1\u5206\u8fa8\u7387");
    model.result("pg3").feature("surf1").set("data", "surf1");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("expr", "spf.Delta_wPlus");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "surf1");
    model.result("pg3").feature().create("slit1", "SurfaceSlit");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg3").feature("slit1").label("\u58c1\u5206\u8fa8\u7387\uff0c\u5185\u58c1");
    model.result("pg3").feature("slit1").set("data", "surf2");
    model.result("pg3").feature("slit1").set("showsolutionparams", "on");
    model.result("pg3").feature("slit1").set("upexpr", "spf.Delta_wPlus_u");
    model.result("pg3").feature("slit1").set("downexpr", "spf.Delta_wPlus_d");
    model.result("pg3").feature("slit1").set("smooth", "internal");
    model.result("pg3").feature("slit1").set("showsolutionparams", "on");
    model.result("pg3").feature("slit1").set("data", "surf2");
    model.result("pg3").feature("slit1").set("inheritplot", "surf1");
    model.result("pg1").run();
    model.result().dataset().create("cpl1", "CutPlane");
    model.result().dataset("cpl1").set("planetype", "general");
    model.result().dataset("cpl1").setIndex("genpoints", -0.2, 0, 0);
    model.result().dataset("cpl1").setIndex("genpoints", -0.2, 0, 1);
    model.result().dataset("cpl1").setIndex("genpoints", -0.2, 1, 0);
    model.result().dataset("cpl1").setIndex("genpoints", -0.2, 1, 1);
    model.result().dataset("cpl1").setIndex("genpoints", 0.2, 1, 2);
    model.result().dataset("cpl1").setIndex("genpoints", 0, 2, 1);
    model.result().dataset().create("cpl2", "CutPlane");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("coloring", "uniform");
    model.result("pg2").feature("surf1").set("color", "gray");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").feature().remove("tran1");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature().remove("slit1");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").label("\u901f\u5ea6\uff1a\u5927\u5c0f\u548c\u77e2\u91cf");
    model.result("pg2").create("surf2", "Surface");
    model.result("pg2").feature("surf2").set("data", "cpl1");
    model.result("pg2").feature("surf2").set("colortable", "RainbowLight");
    model.result("pg2").run();
    model.result("pg2").create("arws1", "ArrowSurface");
    model.result("pg2").feature("arws1").set("data", "cpl2");
    model.result("pg2").feature("arws1").set("expr", new String[]{"0", "v", "w"});
    model.result("pg2").feature("arws1").set("arrowlength", "logarithmic");
    model.result("pg2").feature("arws1").set("scaleactive", true);
    model.result("pg2").feature("arws1").set("scale", 0.26);
    model.result("pg2").feature("arws1").set("arrowcount", 500);
    model.result("pg2").feature("arws1").set("color", "white");
    model.result("pg2").run();
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "\u901f\u5ea6\uff1a\u5927\u5c0f\u548c\u9762\u5185\u77e2\u91cf");
    model.result("pg2").set("view", "view6");
    model.result("pg2").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").label("\u626d\u77e9");
    model.result().numerical("gev1").setIndex("expr", "4*abs(intop1(tau_riw)+intop2(tau_rw))", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u626d\u77e9");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result().numerical().create("gev2", "EvalGlobal");
    model.result().numerical("gev2").label("\u529f\u8017");
    model.result().numerical("gev2").setIndex("expr", "4*abs(intop1(P_riw)+intop2(P_rw))", 0);
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u529f\u8017");
    model.result().numerical("gev2").set("table", "tbl2");
    model.result().numerical("gev2").setResult();
    model.result("pg2").run();

    model.title("\u6a21\u5757\u5316\u6405\u62cc\u5668 - \u6e4d\u6d41\u6df7\u5408 (k-\u03b5)");

    model
         .description("\u672c\u4f8b\u57fa\u4e8e\u4e00\u4e2a\u73b0\u6709\u7684\u51e0\u4f55\u6a21\u578b\u6587\u4ef6\u8fdb\u884c\u6784\u5efa\uff0c\u5176\u4e2d\u5305\u542b\u7684\u51e0\u4f55\u96f6\u4ef6\u7528\u4e8e\u6784\u5efa\u5e26\u659c\u53f6\u7247\u53f6\u8f6e\u6216 Rushton \u6da1\u8f6e\u7684\u6321\u677f\u5f0f\u5e73\u5e95\u6405\u62cc\u5668\u548c\u789f\u5f62\u5e95\u6405\u62cc\u5668\u3002\u8be5\u6a21\u578b\u7684\u7279\u70b9\u662f\u4f7f\u7528 k-\u03b5 \u6a21\u578b\u6765\u6a21\u62df\u5e26\u659c\u53f6\u7247\u53f6\u8f6e\u7684\u789f\u5f62\u5e95\u5bb9\u5668\u4e2d\u7684\u6e4d\u6d41\u3002\n\n\u672c\u4f8b\u4f7f\u7528\u201c\u51bb\u7ed3\u8f6c\u5b50\u201d\u4eff\u771f\u65b9\u6cd5\uff0c\u65cb\u8f6c\u90e8\u4ef6\u3001\u5bb9\u5668\u58c1\u548c\u6321\u677f\u5747\u4fdd\u6301\u5728\u539f\u4f4d\uff0c\u901a\u8fc7\u5f15\u5165\u79bb\u5fc3\u529b\u548c\u79d1\u91cc\u5965\u5229\u529b\u6765\u5206\u6790\u65cb\u8f6c\u3002");

    model.label("modular_mixer_ke.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
