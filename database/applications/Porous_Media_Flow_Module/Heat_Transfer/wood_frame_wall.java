/*
 * wood_frame_wall.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:13 by COMSOL 6.3.0.290. */
public class wood_frame_wall {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Porous_Media_Flow_Module\\Heat_Transfer");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ht", "HeatTransferInBuildingMaterials", "geom1");
    model.component("comp1").physics().create("mt", "MoistureTransportInBuildingMaterials", "geom1");

    model.component("comp1").multiphysics().create("ham1", "HeatAndMoisture", 2);
    model.component("comp1").multiphysics("ham1").set("Heat_physics", "ht");
    model.component("comp1").multiphysics("ham1").set("Moist_physics", "mt");
    model.component("comp1").multiphysics("ham1").selection().all();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std1").feature("stat").setSolveFor("/physics/mt", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/ham1", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("L", "800[mm]", "\u58c1\u603b\u957f");
    model.param().set("t_il", "12.5[mm]", "\u5185\u90e8\u5c42\u539a\u5ea6");
    model.param().set("t_i", "120[mm]", "\u9694\u677f\u539a\u5ea6");
    model.param().set("t_b", "15[mm]", "\u652f\u6491\u539a\u5ea6");
    model.param().set("d_wf", "45[mm]", "\u6728\u8d28\u6846\u67b6\u76f4\u5f84");
    model.param().set("h_ext", "25[W/(m^2*K)]", "\u4f20\u70ed\u7cfb\u6570\uff0c\u5916\u90e8");
    model.param().set("h_int", "8[W/(m^2*K)]", "\u4f20\u70ed\u7cfb\u6570\uff0c\u5185\u90e8");
    model.param().set("T_ext", "0[degC]", "\u6e29\u5ea6\uff0c\u5916\u90e8");
    model.param().set("T_int", "19[degC]", "\u6e29\u5ea6\uff0c\u5185\u90e8");
    model.param().set("beta_ext", "25e-8[s/m]", "\u6c34\u5206\u8f93\u9001\u7cfb\u6570\uff0c\u5916\u90e8");
    model.param().set("beta_int", "8e-8[s/m]", "\u6c34\u5206\u8f93\u9001\u7cfb\u6570\uff0c\u5185\u90e8");
    model.param().set("phi_ext", "0.8", "\u76f8\u5bf9\u6e7f\u5ea6\uff0c\u5916\u90e8");
    model.param().set("phi_int", "0.5", "\u76f8\u5bf9\u6e7f\u5ea6\uff0c\u5185\u90e8");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"L", "t_il + t_i + t_b"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "t_il", 0);
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "t_i", 1);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"d_wf", "t_i"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"L/4-d_wf/2", "t_il"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("copy1", "Copy");
    model.component("comp1").geom("geom1").feature("copy1").selection("input").set("r2");
    model.component("comp1").geom("geom1").feature("copy1").set("displx", "L/2");
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").common().create("ampr1", "AmbientProperties");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").common("ampr1").set("T_amb", "T_ext");
    model.component("comp1").common("ampr1").set("phi_amb", "phi_ext");

    model.component("comp1").physics("ht").feature("bm1").set("MaterialType", "VaporResistanceFactor");
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 1);
    model.component("comp1").physics("ht").feature("hf1").selection().set(7);
    model.component("comp1").physics("ht").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf1").set("h", "h_ext");
    model.component("comp1").physics("ht").feature("hf1").set("Text_src", "root.comp1.ampr1.T_amb");
    model.component("comp1").physics("ht").create("hf2", "HeatFluxBoundary", 1);
    model.component("comp1").physics("ht").feature("hf2").selection().set(2);
    model.component("comp1").physics("ht").feature("hf2").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf2").set("h", "h_int");
    model.component("comp1").physics("ht").feature("hf2").set("Text", "T_int");
    model.component("comp1").physics("ht").feature("init1").set("Tinit", "T_int");
    model.component("comp1").physics("mt").feature("bm1").set("MaterialType", "VaporResistanceFactor");
    model.component("comp1").physics("mt").create("mf1", "MoistureFlux", 1);
    model.component("comp1").physics("mt").feature("mf1").selection().set(7);
    model.component("comp1").physics("mt").feature("mf1").set("MoistureFluxType", "ConvectiveMoistureFluxPressures");
    model.component("comp1").physics("mt").feature("mf1").set("betaext", "beta_ext");
    model.component("comp1").physics("mt").feature("mf1").set("TExtPressures_src", "root.comp1.ampr1.T_amb");
    model.component("comp1").physics("mt").feature("mf1").set("phiExtPressures_src", "root.comp1.ampr1.phi_amb");
    model.component("comp1").physics("mt").create("mf2", "MoistureFlux", 1);
    model.component("comp1").physics("mt").feature("mf2").selection().set(2);
    model.component("comp1").physics("mt").feature("mf2").set("MoistureFluxType", "ConvectiveMoistureFluxPressures");
    model.component("comp1").physics("mt").feature("mf2").set("betaext", "beta_int");
    model.component("comp1").physics("mt").feature("mf2").set("TExtPressures", "T_int");
    model.component("comp1").physics("mt").feature("mf2").set("phiExtPressures", "phi_int");
    model.component("comp1").physics("mt").feature("init1").set("phi_init", "phi_int");
    model.component("comp1").physics("mt").create("tmb1", "ThinMoistureBarrier", 1);
    model.component("comp1").physics("mt").feature("tmb1").selection().set(4, 9, 12, 15, 18);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("int2", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("int3", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat1").label("Wood (pine)");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").func("int1").label("wc");
    model.component("comp1").material("mat1").propertyGroup("def").func("int1").set("funcname", "wc");
    model.component("comp1").material("mat1").propertyGroup("def").func("int1")
         .set("table", new String[][]{{"0", "0"}, {"0.55", "45"}, {"0.75", "80"}, {"0.97", "185"}, {"1", "870"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("int1").set("interp", "piecewisecubic");
    model.component("comp1").material("mat1").propertyGroup("def").func("int1")
         .set("fununit", new String[]{"kg/m^3"});
    model.component("comp1").material("mat1").propertyGroup("def").func("int1").set("argunit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("def").func("int2").label("Dw");
    model.component("comp1").material("mat1").propertyGroup("def").func("int2").set("funcname", "Dw");
    model.component("comp1").material("mat1").propertyGroup("def").func("int2")
         .set("table", new String[][]{{"0", "1.32e-13"}, {"0.65", "1.32e-13"}, {"1", "8.03e-11"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("int2").set("interp", "piecewisecubic");
    model.component("comp1").material("mat1").propertyGroup("def").func("int2")
         .set("fununit", new String[]{"m^2/s"});
    model.component("comp1").material("mat1").propertyGroup("def").func("int2").set("argunit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").label("delta_p");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("funcname", "delta_p");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("expr", "max(7e-13*exp(2.84*phi),0)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("args", new String[]{"phi"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("fununit", "s");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("argunit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"phi", "0", "1"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("int3").label("k");
    model.component("comp1").material("mat1").propertyGroup("def").func("int3")
         .set("funcnametable", new String[][]{{"int2", "1"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("int3").set("funcname", "k");
    model.component("comp1").material("mat1").propertyGroup("def").func("int3")
         .set("table", new String[][]{{"0", "0.1"}, {"0.97", "0.15"}, {"1", "0.6"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("int3").set("interp", "piecewisecubic");
    model.component("comp1").material("mat1").propertyGroup("def").func("int3")
         .set("fununit", new String[]{"W/(m*K)"});
    model.component("comp1").material("mat1").propertyGroup("def").func("int3").set("argunit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").label("mu_vrf");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("funcname", "mu_vrf");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("expr", "max(2.01E-7*T^0.81/p/delta_p(phi),0)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("args", new String[]{"T", "p", "phi"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("fununit", "1");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("argunit", new String[]{"K", "Pa", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotaxis", new String[]{"off", "off", "on"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"293.15", "1e5", "0"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "293.15", "293.15"}, {"p", "1e5", "1e5"}, {"phi", "0", "1"}});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "532[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "2700[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(phi)", "0", "0", "0", "k(phi)", "0", "0", "0", "k(phi)"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("diffusion", new String[]{"Dw(phi)", "0", "0", "0", "Dw(phi)", "0", "0", "0", "Dw(phi)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("watercontent", "wc(phi)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("vaporpermeability", new String[]{"delta_p(phi)", "0", "0", "0", "delta_p(phi)", "0", "0", "0", "delta_p(phi)"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("vaporresistancefactor", new String[]{"mu_vrf(T,pA,phi)", "0", "0", "0", "mu_vrf(T,pA,phi)", "0", "0", "0", "mu_vrf(T,pA,phi)"});
    model.component("comp1").material("mat1").propertyGroup("def").addInput("relativehumidity");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("pressure");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("temperature");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("int1", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("int2", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat2").label("Cellulose board");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").label("wc");
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").set("funcname", "wc");
    model.component("comp1").material("mat2").propertyGroup("def").func("int1")
         .set("table", new String[][]{{"0", "0"}, {"0.83", "13"}, {"0.97", "33"}, {"1", "710"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").set("interp", "piecewisecubic");
    model.component("comp1").material("mat2").propertyGroup("def").func("int1")
         .set("fununit", new String[]{"kg/m^3"});
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").set("argunit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("def").func("int2").label("Dw");
    model.component("comp1").material("mat2").propertyGroup("def").func("int2").set("funcname", "Dw");
    model.component("comp1").material("mat2").propertyGroup("def").func("int2")
         .set("table", new String[][]{{"0", "8.1e-11"}, {"0.8", "8.1e-11"}, {"1", "7.2e-8"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("int2").set("interp", "piecewisecubic");
    model.component("comp1").material("mat2").propertyGroup("def").func("int2")
         .set("fununit", new String[]{"m^2/s"});
    model.component("comp1").material("mat2").propertyGroup("def").func("int2").set("argunit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").label("delta_p");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("funcname", "delta_p");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2")
         .set("expr", "max(1.7e-11/33*(T-263.15)+8.3e-11,0)");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("fununit", "s");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"263.15"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "263.15", "296.15"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").label("k");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("funcname", "k");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1")
         .set("expr", "(0.0398-0.0365)/30*max(0,T-263.15)+0.0365");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("args", new String[]{"T"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"263.15"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"T", "263.15", "293.15"}});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "63[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "2000[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("diffusion", new String[]{"Dw(phi)", "0", "0", "0", "Dw(phi)", "0", "0", "0", "Dw(phi)"});
    model.component("comp1").material("mat2").propertyGroup("def").set("watercontent", "wc(phi)");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("vaporresistancefactor", new String[]{"2", "0", "0", "0", "2", "0", "0", "0", "2"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("vaporpermeability", new String[]{"delta_p(T)", "0", "0", "0", "delta_p(T)", "0", "0", "0", "delta_p(T)"});
    model.component("comp1").material("mat2").propertyGroup("def").addInput("relativehumidity");
    model.component("comp1").material("mat2").propertyGroup("def").addInput("temperature");
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("int1", "Interpolation");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("int2", "Interpolation");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("int3", "Interpolation");
    model.component("comp1").material("mat3").label("Gypsum board");
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").label("wc");
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").set("funcname", "wc");
    model.component("comp1").material("mat3").propertyGroup("def").func("int1")
         .set("table", new String[][]{{"0", "0"}, {"0.33", "5"}, {"0.75", "7"}, {"0.97", "18"}, {"1", "370"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").set("interp", "piecewisecubic");
    model.component("comp1").material("mat3").propertyGroup("def").func("int1")
         .set("fununit", new String[]{"kg/m^3"});
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").set("argunit", new String[]{"1"});
    model.component("comp1").material("mat3").propertyGroup("def").func("int2").label("Dw");
    model.component("comp1").material("mat3").propertyGroup("def").func("int2").set("funcname", "Dw");
    model.component("comp1").material("mat3").propertyGroup("def").func("int2")
         .set("table", new String[][]{{"0", "1.85e-10"}, {"0.8", "1.85e-10"}, {"1", "1.59e-7"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("int2").set("interp", "piecewisecubic");
    model.component("comp1").material("mat3").propertyGroup("def").func("int2")
         .set("fununit", new String[]{"m^2/s"});
    model.component("comp1").material("mat3").propertyGroup("def").func("int2").set("argunit", new String[]{"1"});
    model.component("comp1").material("mat3").propertyGroup("def").func("int3").label("k");
    model.component("comp1").material("mat3").propertyGroup("def").func("int3")
         .set("funcnametable", new String[][]{{"int2", "1"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("int3").set("funcname", "k");
    model.component("comp1").material("mat3").propertyGroup("def").func("int3")
         .set("table", new String[][]{{"0", "0.19"}, {"0.97", "0.21"}, {"1", "0.6"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("int3").set("interp", "piecewisecubic");
    model.component("comp1").material("mat3").propertyGroup("def").func("int3")
         .set("fununit", new String[]{"W/(m*K)"});
    model.component("comp1").material("mat3").propertyGroup("def").func("int3").set("argunit", new String[]{"1"});
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "574[kg/m^3]");
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", "1100[J/(kg*K)]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(phi)", "0", "0", "0", "k(phi)", "0", "0", "0", "k(phi)"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("diffusion", new String[]{"Dw(phi)", "0", "0", "0", "Dw(phi)", "0", "0", "0", "Dw(phi)"});
    model.component("comp1").material("mat3").propertyGroup("def").set("watercontent", "wc(phi)");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("vaporresistancefactor", new String[]{"6.9", "0", "0", "0", "6.9", "0", "0", "0", "6.9"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("vaporpermeability", new String[]{"2.9e-11[s]", "0", "0", "0", "2.9e-11[s]", "0", "0", "0", "2.9e-11[s]"});
    model.component("comp1").material("mat3").propertyGroup("def").addInput("relativehumidity");
    model.component("comp1").material().create("mat4", "Common");
    model.component("comp1").material("mat4").propertyGroup("def").func().create("int1", "Interpolation");
    model.component("comp1").material("mat4").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat4").label("Plastic coated paper");
    model.component("comp1").material("mat4").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat4").propertyGroup("def").func("int1").label("mu_vrf");
    model.component("comp1").material("mat4").propertyGroup("def").func("int1").set("funcname", "mu_vrf");
    model.component("comp1").material("mat4").propertyGroup("def").func("int1")
         .set("table", new String[][]{{"0", "9900"}, {"0.35", "9900"}, {"1", "9290"}});
    model.component("comp1").material("mat4").propertyGroup("def").func("int1").set("interp", "piecewisecubic");
    model.component("comp1").material("mat4").propertyGroup("def").func("int1").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat4").propertyGroup("def").func("int1").set("argunit", new String[]{"1"});
    model.component("comp1").material("mat4").propertyGroup("def").func("an1").label("delta_p");
    model.component("comp1").material("mat4").propertyGroup("def").func("an1").set("funcname", "delta_p");
    model.component("comp1").material("mat4").propertyGroup("def").func("an1")
         .set("expr", "max(2.01E-7*T^0.81/p/mu_vrf(phi),0)");
    model.component("comp1").material("mat4").propertyGroup("def").func("an1")
         .set("args", new String[]{"T", "p", "phi"});
    model.component("comp1").material("mat4").propertyGroup("def").func("an1").set("fununit", "s");
    model.component("comp1").material("mat4").propertyGroup("def").func("an1")
         .set("argunit", new String[]{"K", "Pa", "1"});
    model.component("comp1").material("mat4").propertyGroup("def").func("an1")
         .set("plotaxis", new String[]{"on", "off", "on"});
    model.component("comp1").material("mat4").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"263", "1", "0"});
    model.component("comp1").material("mat4").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"T", "263", "293"}, {"p", "1", "1"}, {"phi", "0", "1"}});
    model.component("comp1").material("mat4").propertyGroup("def").set("density", "940[kg/m^3]");
    model.component("comp1").material("mat4").propertyGroup("def").set("heatcapacity", "2000[J/(kg*K)]");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.1[W/(m*K)]", "0", "0", "0", "0.1[W/(m*K)]", "0", "0", "0", "0.1[W/(m*K)]"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("vaporresistancefactor", new String[]{"mu_vrf(phi)", "0", "0", "0", "mu_vrf(phi)", "0", "0", "0", "mu_vrf(phi)"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("vaporpermeability", new String[]{"delta_p(T,pA,phi)", "0", "0", "0", "delta_p(T,pA,phi)", "0", "0", "0", "delta_p(T,pA,phi)"});
    model.component("comp1").material("mat4").propertyGroup("def").addInput("relativehumidity");
    model.component("comp1").material("mat4").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat4").propertyGroup("def").addInput("pressure");
    model.component("comp1").material("mat1").selection().set(4, 6);
    model.component("comp1").material("mat2").selection().set(2, 5, 7);
    model.component("comp1").material("mat3").selection().set(1);
    model.component("comp1").material("mat4").selection().geom("geom1", 1);
    model.component("comp1").material("mat4").selection().set(4, 9, 12, 15, 18);
    model.component("comp1").material().create("mat5", "Common");
    model.component("comp1").material("mat5").label("\u6728\u5236\u9762\u677f (OSB)");
    model.component("comp1").material("mat5").selection().set(3);
    model.component("comp1").material("mat5").propertyGroup("def").func().create("int1", "Interpolation");
    model.component("comp1").material("mat5").propertyGroup("def").func("int1").label("k_eff");
    model.component("comp1").material("mat5").propertyGroup("def").func("int1").set("funcname", "k_eff");
    model.component("comp1").material("mat5").propertyGroup("def").func("int1").setIndex("table", 0, 0, 0);
    model.component("comp1").material("mat5").propertyGroup("def").func("int1").setIndex("table", 0.11, 0, 1);
    model.component("comp1").material("mat5").propertyGroup("def").func("int1").setIndex("table", 0.97, 1, 0);
    model.component("comp1").material("mat5").propertyGroup("def").func("int1").setIndex("table", 0.14, 1, 1);
    model.component("comp1").material("mat5").propertyGroup("def").func("int1").setIndex("table", 1, 2, 0);
    model.component("comp1").material("mat5").propertyGroup("def").func("int1").setIndex("table", 0.6, 2, 1);
    model.component("comp1").material("mat5").propertyGroup("def").func("int1").set("interp", "piecewisecubic");
    model.component("comp1").material("mat5").propertyGroup("def").func("int1").set("extrap", "linear");
    model.component("comp1").material("mat5").propertyGroup("def").func("int1").setIndex("argunit", 1, 0);
    model.component("comp1").material("mat5").propertyGroup("def").func("int1").setIndex("fununit", "W/(m*K)", 0);
    model.component("comp1").material("mat5").propertyGroup("def").func().create("int2", "Interpolation");
    model.component("comp1").material("mat5").propertyGroup("def").func("int2").label("\u63d2\u503c\uff1aDw");
    model.component("comp1").material("mat5").propertyGroup("def").func("int2").set("funcname", "Dw");
    model.component("comp1").material("mat5").propertyGroup("def").func("int2").setIndex("table", 0, 0, 0);
    model.component("comp1").material("mat5").propertyGroup("def").func("int2").setIndex("table", "2.93e-12", 0, 1);
    model.component("comp1").material("mat5").propertyGroup("def").func("int2").setIndex("table", 0.97, 1, 0);
    model.component("comp1").material("mat5").propertyGroup("def").func("int2").setIndex("table", "2.93e-12", 1, 1);
    model.component("comp1").material("mat5").propertyGroup("def").func("int2").setIndex("table", 1, 2, 0);
    model.component("comp1").material("mat5").propertyGroup("def").func("int2").setIndex("table", "6.52e-10", 2, 1);
    model.component("comp1").material("mat5").propertyGroup("def").func("int2").set("interp", "piecewisecubic");
    model.component("comp1").material("mat5").propertyGroup("def").func("int2").set("extrap", "linear");
    model.component("comp1").material("mat5").propertyGroup("def").func("int2").setIndex("argunit", 1, 0);
    model.component("comp1").material("mat5").propertyGroup("def").func("int2").setIndex("fununit", "m^2/s", 0);
    model.component("comp1").material("mat5").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat5").propertyGroup("def").func("an1").label("\u89e3\u6790\uff1awc");
    model.component("comp1").material("mat5").propertyGroup("def").func("an1").set("funcname", "wc");
    model.component("comp1").material("mat5").propertyGroup("def").func("an1")
         .set("expr", "202.68*x^2 - 24.813*x + 6.1962");
    model.component("comp1").material("mat5").propertyGroup("def").func("an1").setIndex("argunit", 1, 0);
    model.component("comp1").material("mat5").propertyGroup("def").func("an1").set("fununit", "kg/m^3");
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k_eff(mt.phi)"});
    model.component("comp1").material("mat5").propertyGroup("def").set("density", new String[]{"646"});
    model.component("comp1").material("mat5").propertyGroup("def").set("heatcapacity", new String[]{"1500"});
    model.component("comp1").material("mat5").propertyGroup("def").set("diffusion", new String[]{"Dw(mt.phi)"});
    model.component("comp1").material("mat5").propertyGroup("def").set("watercontent", new String[]{"wc(mt.phi)"});
    model.component("comp1").material("mat5").propertyGroup("def").set("vaporresistancefactor", new String[]{"162"});

    model.component("comp1").mesh("mesh1").autoMeshSize(1);

    model.study("std1").label("\u7814\u7a76 1\uff08\u7a33\u6001\uff0c\u4e0d\u542b\u9632\u6f6e\u5c42\uff09");
    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat").set("disabledphysics", new String[]{"mt/tmb1"});
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s1").feature("fc1").set("dtech", "hnlin");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u6e29\u5ea6 (ht)");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("solutionparams", "parent");
    model.result("pg1").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u76f8\u5bf9\u6e7f\u5ea6 (mt)");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "mt.phi");
    model.result("pg2").feature("surf1").set("colortable", "Kyanite");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg1").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"temperature", "\u6e29\u5ea6", "K", "K"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "\u00b0C", 0, 3);
    model.result().configuration("prfu1").apply();
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").create("ann1", "Annotation");
    model.result("pg2").create("ann2", "Annotation");
    model.result("pg2").create("ann3", "Annotation");
    model.result("pg2").create("ann4", "Annotation");
    model.result("pg2").create("ann5", "Annotation");
    model.result("pg2").create("ann6", "Annotation");
    model.result("pg2").create("ann7", "Annotation");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg2").run();
    model.result("pg2").feature("ann1").set("text", "\u5916\u90e8");
    model.result("pg2").feature("ann1").set("posxexpr", "L/2");
    model.result("pg2").feature("ann1").set("posyexpr", "t_il+3*t_i/2+t_b");
    model.result("pg2").feature("ann1").set("showpoint", false);
    model.result("pg2").feature("ann1").set("anchorpoint", "center");
    model.result("pg2").run();
    model.result("pg2").feature("ann2").set("text", "\u5185\u90e8");
    model.result("pg2").feature("ann2").set("posxexpr", "L/2");
    model.result("pg2").feature("ann2").set("posyexpr", "-t_i/2");
    model.result("pg2").feature("ann2").set("showpoint", false);
    model.result("pg2").feature("ann2").set("anchorpoint", "center");
    model.result("pg2").run();
    model.result("pg2").feature("ann3").set("text", "\u5185\u90e8\u9762\u677f\uff08\u77f3\u818f\uff09");
    model.result("pg2").feature("ann3").set("posxexpr", "L/2");
    model.result("pg2").feature("ann3").set("posyexpr", "t_il/2");
    model.result("pg2").feature("ann3").set("showpoint", false);
    model.result("pg2").feature("ann3").set("color", "black");
    model.result("pg2").feature("ann3").set("anchorpoint", "center");
    model.result("pg2").run();
    model.result("pg2").feature("ann4").set("text", "\u9694\u677f\uff08\u7ea4\u7ef4\u677f\uff09");
    model.result("pg2").feature("ann4").set("posxexpr", "L/2");
    model.result("pg2").feature("ann4").set("posyexpr", "t_il+t_i/2");
    model.result("pg2").feature("ann4").set("showpoint", false);
    model.result("pg2").feature("ann4").set("color", "black");
    model.result("pg2").feature("ann4").set("anchorpoint", "center");
    model.result("pg2").run();
    model.result("pg2").feature("ann5").set("text", "\u5916\u90e8\u9762\u677f (OSB)");
    model.result("pg2").feature("ann5").set("posxexpr", "L/2");
    model.result("pg2").feature("ann5").set("posyexpr", "t_il+t_i+t_b/2");
    model.result("pg2").feature("ann5").set("showpoint", false);
    model.result("pg2").feature("ann5").set("color", "black");
    model.result("pg2").feature("ann5").set("anchorpoint", "center");
    model.result("pg2").run();
    model.result("pg2").feature("ann6").set("text", "\u6728\u8d28\u6846\u67b6");
    model.result("pg2").feature("ann6").set("posxexpr", "L/4");
    model.result("pg2").feature("ann6").set("posyexpr", "t_il+t_i/2");
    model.result("pg2").feature("ann6").set("showpoint", false);
    model.result("pg2").feature("ann6").set("color", "black");
    model.result("pg2").feature("ann6").set("anchorpoint", "center");
    model.result("pg2").run();
    model.result("pg2").feature("ann7").set("text", "\u6728\u8d28\u6846\u67b6");
    model.result("pg2").feature("ann7").set("posxexpr", "3*L/4");
    model.result("pg2").feature("ann7").set("posyexpr", "t_il+t_i/2");
    model.result("pg2").feature("ann7").set("showpoint", false);
    model.result("pg2").feature("ann7").set("color", "black");
    model.result("pg2").feature("ann7").set("anchorpoint", "center");
    model.result("pg2").run();

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std2").feature("stat").setSolveFor("/physics/mt", true);
    model.study("std2").feature("stat").setSolveFor("/multiphysics/ham1", true);
    model.study("std2").label("\u7814\u7a76 2\uff08\u7a33\u6001\uff0c\u542b\u9632\u6f6e\u5c42\uff09");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u6e29\u5ea6 (ht) 1");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("solutionparams", "parent");
    model.result("pg3").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u76f8\u5bf9\u6e7f\u5ea6 (mt) 1");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "mt.phi");
    model.result("pg4").feature("surf1").set("colortable", "Kyanite");
    model.result("pg4").feature("surf1").set("smooth", "internal");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result("pg3").run();
    model.result().dataset().create("cln1", "CutLine2D");
    model.result().dataset("cln1").label("\u901a\u8fc7\u6728\u7acb\u67f1\u7684\u622a\u7ebf\uff08\u89e3 1\uff09");
    model.result().dataset("cln1").setIndex("genpoints", "L/4", 0, 0);
    model.result().dataset("cln1").setIndex("genpoints", "L/4", 1, 0);
    model.result().dataset("cln1").setIndex("genpoints", 0.15, 0, 1);
    model.result().dataset().duplicate("cln2", "cln1");
    model.result().dataset("cln2").label("\u901a\u8fc7\u7ea4\u7ef4\u677f\u7684\u622a\u7ebf\uff08\u89e3 1\uff09");
    model.result().dataset("cln2").setIndex("genpoints", "L/2", 0, 0);
    model.result().dataset("cln2").setIndex("genpoints", "L/2", 1, 0);
    model.result().dataset().duplicate("cln3", "cln1");
    model.result().dataset("cln3").label("\u901a\u8fc7\u6728\u7acb\u67f1\u7684\u622a\u7ebf\uff08\u89e3 2\uff09");
    model.result().dataset("cln3").set("data", "dset2");
    model.result().dataset().duplicate("cln4", "cln2");
    model.result().dataset("cln4").label("\u901a\u8fc7\u7ea4\u7ef4\u677f\u7684\u622a\u7ebf\uff08\u89e3 2\uff09");
    model.result().dataset("cln4").set("data", "dset2");
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u5899\u58c1\u6e29\u5ea6\uff08\u6bd4\u8f83\uff09");
    model.result("pg5").set("data", "none");
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("xlabel", "\u4e0e\u5916\u90e8\u7684\u8ddd\u79bb (m)");
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg5").feature("lngr1").set("linewidth", "preference");
    model.result("pg5").feature("lngr1").set("data", "cln1");
    model.result("pg5").feature("lngr1").label("\u6728\u7acb\u67f1\uff08\u4e0d\u542b\u9632\u6f6e\u5c42\uff09");
    model.result("pg5").feature("lngr1").set("linecolor", "red");
    model.result("pg5").feature("lngr1").set("linewidth", 2);
    model.result("pg5").feature("lngr1").set("legend", true);
    model.result("pg5").feature("lngr1").set("autoplotlabel", true);
    model.result("pg5").feature("lngr1").set("autosolution", false);
    model.result("pg5").run();
    model.result("pg5").feature().duplicate("lngr2", "lngr1");
    model.result("pg5").run();
    model.result("pg5").feature("lngr2").label("\u7ea4\u7ef4\u677f\uff08\u4e0d\u542b\u9632\u6f6e\u5c42\uff09");
    model.result("pg5").feature("lngr2").set("data", "cln2");
    model.result("pg5").feature("lngr2").set("linecolor", "blue");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature().duplicate("lngr3", "lngr1");
    model.result("pg5").run();
    model.result("pg5").feature("lngr3").label("\u6728\u7acb\u67f1\uff08\u542b\u9632\u6f6e\u5c42\uff09");
    model.result("pg5").feature("lngr3").set("data", "cln3");
    model.result("pg5").feature("lngr3").set("linestyle", "dashed");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature().duplicate("lngr4", "lngr2");
    model.result("pg5").run();
    model.result("pg5").feature("lngr4").label("\u7ea4\u7ef4\u677f\uff08\u542b\u9632\u6f6e\u5c42\uff09");
    model.result("pg5").feature("lngr4").set("data", "cln4");
    model.result("pg5").feature("lngr4").set("linestyle", "dashed");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").set("legendpos", "lowerright");
    model.result("pg5").run();
    model.result("pg5").feature("lngr1").set("titletype", "none");
    model.result("pg5").run();
    model.result("pg5").feature("lngr2").set("titletype", "none");
    model.result("pg5").run();
    model.result("pg5").feature("lngr3").set("titletype", "none");
    model.result("pg5").run();
    model.result("pg5").feature("lngr4").set("titletype", "none");
    model.result("pg5").run();
    model.result("pg5").set("titletype", "manual");
    model.result("pg5").set("title", "\u5899\u58c1\u6e29\u5ea6");
    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result("pg6").label("\u5899\u58c1\u76f8\u5bf9\u6e7f\u5ea6\uff08\u6bd4\u8f83\uff09");
    model.result("pg6").run();
    model.result("pg6").feature("lngr1").set("expr", "mt.phi");
    model.result("pg6").run();
    model.result("pg6").feature("lngr2").set("expr", "mt.phi");
    model.result("pg6").run();
    model.result("pg6").feature("lngr3").set("expr", "mt.phi");
    model.result("pg6").feature("lngr3").set("smooth", "everywhere");
    model.result("pg6").run();
    model.result("pg6").feature("lngr4").set("expr", "mt.phi");
    model.result("pg6").feature("lngr4").set("smooth", "everywhere");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").set("legendpos", "lowerleft");
    model.result("pg6").set("title", "\u5899\u58c1\u76f8\u5bf9\u6e7f\u5ea6");
    model.result("pg6").run();

    model.component("comp1").physics().create("ht2", "HeatTransferInBuildingMaterials", "geom1");

    model.study("std1").feature("stat").setSolveFor("/physics/ht2", true);
    model.study("std2").feature("stat").setSolveFor("/physics/ht2", true);

    model.component("comp1").physics().create("mt2", "MoistureTransportInBuildingMaterials", "geom1");

    model.study("std1").feature("stat").setSolveFor("/physics/mt2", true);
    model.study("std2").feature("stat").setSolveFor("/physics/mt2", true);

    model.component("comp1").multiphysics().create("ham2", "HeatAndMoisture", 2);

    model.study("std1").feature("stat").setSolveFor("/multiphysics/ham2", true);
    model.study("std2").feature("stat").setSolveFor("/multiphysics/ham2", true);

    model.component("comp1").multiphysics("ham2").set("Heat_physics", "ht2");
    model.component("comp1").multiphysics("ham2").set("Moist_physics", "mt2");
    model.component("comp1").multiphysics("ham2").selection().all();
    model.component("comp1").multiphysics("ham2").active(false);

    model.component("comp1").physics("ht2").feature("bm1")
         .setIndex("minput_relativehumidity_src", "root.comp1.mt2.phi", 0);
    model.component("comp1").physics("ht2").feature("bm1").set("delta_p_mat", "userdef");
    model.component("comp1").physics("ht2").feature("bm1").set("delta_p", new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0});
    model.component("comp1").physics("ht2").create("hf1", "HeatFluxBoundary", 1);
    model.component("comp1").physics("ht2").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht2").feature("hf1").set("h", "h_ext");
    model.component("comp1").physics("ht2").feature("hf1").set("Text", "T_ext");
    model.component("comp1").physics("ht2").feature("hf1").selection().set(7);
    model.component("comp1").physics("ht2").create("hf2", "HeatFluxBoundary", 1);
    model.component("comp1").physics("ht2").feature("hf2").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht2").feature("hf2").set("h", "h_int");
    model.component("comp1").physics("ht2").feature("hf2").set("Text", "T_int");
    model.component("comp1").physics("ht2").feature("hf2").selection().set(2);
    model.component("comp1").physics("ht2").feature("init1").set("Tinit", "T_int");
    model.component("comp1").physics("mt2").feature("bm1").set("minput_temperature_src", "root.comp1.T2");
    model.component("comp1").physics("mt2").feature("bm1").set("minput_pressure", "ht2.pA");
    model.component("comp1").physics("mt2").feature("bm1").set("Dwmoist_mat", "userdef");
    model.component("comp1").physics("mt2").feature("bm1").set("MaterialType", "VaporResistanceFactor");
    model.component("comp1").physics("mt2").create("mf1", "MoistureFlux", 1);
    model.component("comp1").physics("mt2").feature("mf1")
         .set("MoistureFluxType", "ConvectiveMoistureFluxPressures");
    model.component("comp1").physics("mt2").feature("mf1").set("betaext", "beta_ext");
    model.component("comp1").physics("mt2").feature("mf1").set("TExtPressures", "T_ext");
    model.component("comp1").physics("mt2").feature("mf1").set("phiExtPressures", "phi_ext");
    model.component("comp1").physics("mt2").feature("mf1").selection().set(7);
    model.component("comp1").physics("mt2").create("mf2", "MoistureFlux", 1);
    model.component("comp1").physics("mt2").feature("mf2")
         .set("MoistureFluxType", "ConvectiveMoistureFluxPressures");
    model.component("comp1").physics("mt2").feature("mf2").set("betaext", "beta_int");
    model.component("comp1").physics("mt2").feature("mf2").set("TExtPressures", "T_int");
    model.component("comp1").physics("mt2").feature("mf2").set("phiExtPressures", "phi_int");
    model.component("comp1").physics("mt2").feature("mf2").selection().set(2);
    model.component("comp1").physics("mt2").feature("init1").set("phi_init", "phi_int");

    model.study().create("std3");
    model.study("std3").create("stat", "Stationary");
    model.study("std3").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std3").feature("stat").setSolveFor("/physics/mt", true);
    model.study("std3").feature("stat").setSolveFor("/physics/ht2", true);
    model.study("std3").feature("stat").setSolveFor("/physics/mt2", true);
    model.study("std3").feature("stat").setSolveFor("/multiphysics/ham1", true);
    model.study("std3").feature("stat").setSolveFor("/multiphysics/ham2", true);
    model.study("std3").feature("stat").setSolveFor("/physics/ht", false);
    model.study("std3").feature("stat").setSolveFor("/physics/mt", false);
    model.study("std3").feature("stat").setSolveFor("/multiphysics/ham1", false);
    model.study("std3").label("\u7814\u7a76 3\uff08\u7a33\u6001\uff0cGlaser \u65b9\u6cd5\uff09");
    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").label("\u6e29\u5ea6 (ht2)");
    model.result("pg7").set("data", "dset3");
    model.result("pg7").feature().create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("showsolutionparams", "on");
    model.result("pg7").feature("surf1").set("solutionparams", "parent");
    model.result("pg7").feature("surf1").set("expr", "T2");
    model.result("pg7").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg7").feature("surf1").set("showsolutionparams", "on");
    model.result("pg7").feature("surf1").set("data", "parent");
    model.result().create("pg8", "PlotGroup2D");
    model.result("pg8").label("\u76f8\u5bf9\u6e7f\u5ea6 (mt2)");
    model.result("pg8").set("data", "dset3");
    model.result("pg8").feature().create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("expr", "mt2.phi");
    model.result("pg8").feature("surf1").set("colortable", "Kyanite");
    model.result("pg8").feature("surf1").set("smooth", "internal");
    model.result("pg8").feature("surf1").set("data", "parent");
    model.result("pg7").run();
    model.result().dataset().duplicate("cln5", "cln3");
    model.result().dataset().duplicate("cln6", "cln4");
    model.result().dataset("cln5").set("data", "dset3");
    model.result().dataset("cln5").label("\u901a\u8fc7\u6728\u7acb\u67f1\u7684\u622a\u7ebf\uff08\u89e3 3\uff09");
    model.result().dataset("cln6").set("data", "dset3");
    model.result().dataset("cln6").label("\u901a\u8fc7\u7ea4\u7ef4\u677f\u7684\u622a\u7ebf\uff08\u89e3 3\uff09");
    model.result("pg5").run();
    model.result("pg5").feature("lngr3").active(false);
    model.result("pg5").run();
    model.result("pg5").feature("lngr4").active(false);
    model.result("pg5").run();
    model.result("pg5").feature().duplicate("lngr5", "lngr1");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature().duplicate("lngr6", "lngr2");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("lngr5").label("\u6728\u7acb\u67f1\uff08Glaser \u65b9\u6cd5\uff09");
    model.result("pg5").feature("lngr5").set("data", "cln5");
    model.result("pg5").feature("lngr5").set("expr", "T2");
    model.result("pg5").feature("lngr5").set("linestyle", "dotted");
    model.result("pg5").run();
    model.result("pg5").feature("lngr6").label("\u7ea4\u7ef4\u677f\uff08Glaser \u65b9\u6cd5\uff09");
    model.result("pg5").feature("lngr6").set("data", "cln6");
    model.result("pg5").feature("lngr6").set("expr", "T2");
    model.result("pg5").feature("lngr6").set("linestyle", "dotted");
    model.result("pg5").run();
    model.result("pg6").run();
    model.result("pg6").feature("lngr3").active(false);
    model.result("pg6").run();
    model.result("pg6").feature("lngr4").active(false);
    model.result("pg6").run();
    model.result("pg6").feature().duplicate("lngr5", "lngr1");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").feature().duplicate("lngr6", "lngr2");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").feature("lngr5").label("\u6728\u7acb\u67f1\uff08Glaser \u65b9\u6cd5\uff09");
    model.result("pg6").feature("lngr5").set("data", "cln5");
    model.result("pg6").feature("lngr5").set("expr", "mt2.phi");
    model.result("pg6").feature("lngr5").set("linestyle", "dotted");
    model.result("pg6").run();
    model.result("pg6").feature("lngr6").label("\u7ea4\u7ef4\u677f\uff08Glaser \u65b9\u6cd5\uff09");
    model.result("pg6").feature("lngr6").set("data", "cln6");
    model.result("pg6").feature("lngr6").set("expr", "mt2.phi");
    model.result("pg6").feature("lngr6").set("linestyle", "dotted");
    model.result("pg6").run();

    model.component("comp1").common("ampr1").set("AmbientData", "MeteorologicalData2021");
    model.component("comp1").common("ampr1").set("ashrae2021WeatherStation", "AroundLocation");
    model.component("comp1").common("ampr1").set("ashrae2021LatitudeToNorth", "53.349");
    model.component("comp1").common("ampr1").set("ashrae2021LongitudeToEast", "-6.260");
    model.component("comp1").common("ampr1").setIndex("ashrae2021ShortDate", 15, 0);
    model.component("comp1").common("ampr1").setIndex("ashrae2021ShortDate", "04", 1);
    model.component("comp1").common("ampr1").setIndex("ashrae2021LocalTime", 0, 0);

    model.study().create("std4");
    model.study("std4").create("time", "Transient");
    model.study("std4").feature("time").setSolveFor("/physics/ht", true);
    model.study("std4").feature("time").setSolveFor("/physics/mt", true);
    model.study("std4").feature("time").setSolveFor("/physics/ht2", true);
    model.study("std4").feature("time").setSolveFor("/physics/mt2", true);
    model.study("std4").feature("time").setSolveFor("/multiphysics/ham1", true);
    model.study("std4").feature("time").setSolveFor("/multiphysics/ham2", true);
    model.study("std4").label("\u7814\u7a76 4\uff08\u77ac\u6001\uff0c\u542b\u9632\u6f6e\u5c42\uff09");
    model.study("std4").feature("time").setSolveFor("/physics/ht2", false);
    model.study("std4").feature("time").setSolveFor("/physics/mt2", false);
    model.study("std4").feature("time").set("tunit", "h");
    model.study("std4").feature("time").set("tlist", "range(0,1,48)");
    model.study("std4").showAutoSequences("all");

    model.sol("sol4").feature("t1").set("consistent", false);
    model.sol("sol4").runAll();

    model.result().create("pg9", "PlotGroup2D");
    model.result("pg9").label("\u6e29\u5ea6 (ht) 2");
    model.result("pg9").set("data", "dset4");
    model.result("pg9").setIndex("looplevel", 49, 0);
    model.result("pg9").feature().create("surf1", "Surface");
    model.result("pg9").feature("surf1").set("showsolutionparams", "on");
    model.result("pg9").feature("surf1").set("solutionparams", "parent");
    model.result("pg9").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg9").feature("surf1").set("showsolutionparams", "on");
    model.result("pg9").feature("surf1").set("data", "parent");
    model.result().create("pg10", "PlotGroup2D");
    model.result("pg10").label("\u76f8\u5bf9\u6e7f\u5ea6 (mt) 2");
    model.result("pg10").set("data", "dset4");
    model.result("pg10").setIndex("looplevel", 49, 0);
    model.result("pg10").feature().create("surf1", "Surface");
    model.result("pg10").feature("surf1").set("expr", "mt.phi");
    model.result("pg10").feature("surf1").set("colortable", "Kyanite");
    model.result("pg10").feature("surf1").set("smooth", "internal");
    model.result("pg10").feature("surf1").set("data", "parent");
    model.result("pg9").run();
    model.result().create("pg11", "PlotGroup1D");
    model.result("pg11").run();
    model.result("pg11").label("\u73af\u5883\u6570\u636e");
    model.result("pg11").set("data", "dset4");
    model.result("pg11").create("ptgr1", "PointGraph");
    model.result("pg11").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg11").feature("ptgr1").set("linewidth", "preference");
    model.result("pg11").feature("ptgr1").selection().set(4);
    model.result("pg11").feature("ptgr1").set("expr", "ampr1.T_amb");
    model.result("pg11").feature("ptgr1").set("legend", true);
    model.result("pg11").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg11").feature("ptgr1").setIndex("legends", "\u6e29\u5ea6", 0);
    model.result("pg11").run();
    model.result("pg11").create("ptgr2", "PointGraph");
    model.result("pg11").feature("ptgr2").set("markerpos", "datapoints");
    model.result("pg11").feature("ptgr2").set("linewidth", "preference");
    model.result("pg11").feature("ptgr2").selection().set(4);
    model.result("pg11").feature("ptgr2").set("expr", "ampr1.phi_amb");
    model.result("pg11").feature("ptgr2").set("legend", true);
    model.result("pg11").feature("ptgr2").set("legendmethod", "manual");
    model.result("pg11").feature("ptgr2").setIndex("legends", "\u76f8\u5bf9\u6e7f\u5ea6", 0);
    model.result("pg11").run();
    model.result("pg11").set("twoyaxes", true);
    model.result("pg11").setIndex("plotonsecyaxis", true, 1, 1);
    model.result("pg11").set("titletype", "manual");
    model.result("pg11").set("title", "\u4e24\u5929\u5185\u7684\u73af\u5883\u6570\u636e");
    model.result("pg11").run();

    model.component("comp1").probe().create("pdom1", "DomainPoint");
    model.component("comp1").probe("pdom1")
         .label("\u57df\u70b9\u63a2\u9488\uff1a\u76f8\u5bf9\u6e7f\u5ea6\uff08\u652f\u67b6\uff09");
    model.component("comp1").probe("pdom1").setIndex("coords2", "L/2", 0);
    model.component("comp1").probe("pdom1").setIndex("coords2", "t_il+t_i+t_b*0.95", 1);
    model.component("comp1").probe("pdom1").feature("ppb1").set("expr", "mt.phi");
    model.component("comp1").probe("pdom1").feature("ppb1").genResult("sol4");

    model.result().numerical("pev1").set("table", "tbl1");
    model.result().numerical("pev1").set("innerinput", "all");
    model.result().numerical("pev1").set("outerinput", "all");
    model.result().numerical("pev1").setResult();
    model.result("pg12").feature("tblp1").set("plotcolumns", new int[]{2});
    model.result("pg12").feature("tblp1").set("xaxisdata", "auto");
    model.result("pg12").run();

    model.component("comp1").probe().duplicate("pdom2", "pdom1");
    model.component("comp1").probe("pdom2")
         .label("\u57df\u70b9\u63a2\u9488\uff1a\u76f8\u5bf9\u6e7f\u5ea6\uff08\u9694\u677f\uff09");
    model.component("comp1").probe("pdom2").setIndex("coords2", "t_il+t_i*0.95", 1);
    model.component("comp1").probe("pdom2").feature("ppb2").set("table", "new");
    model.component("comp1").probe("pdom2").feature("ppb2").genResult("sol4");

    model.result().numerical("pev2").set("table", "tbl2");
    model.result().numerical("pev2").set("innerinput", "all");
    model.result().numerical("pev2").set("outerinput", "all");
    model.result().numerical("pev2").setResult();
    model.result("pg12").feature("tblp2").set("plotcolumns", new int[]{2});
    model.result("pg12").feature("tblp2").set("xaxisdata", "auto");
    model.result("pg12").run();
    model.result("pg12").set("window", "window1");
    model.result("pg12").run();
    model.result("pg12").label("\u4e24\u5929\u5185\u7684\u76f8\u5bf9\u6e7f\u5ea6");
    model.result("pg12").set("titletype", "manual");
    model.result("pg12").set("title", "\u4e24\u5929\u5185\u7684\u76f8\u5bf9\u6e7f\u5ea6");
    model.result("pg12").set("ylabelactive", true);
    model.result("pg12").set("ylabel", "\u76f8\u5bf9\u6e7f\u5ea6 (1)");
    model.result("pg12").set("window", "window1");
    model.result("pg12").run();
    model.result("pg12").feature("tblp1").set("legendmethod", "manual");
    model.result("pg12").feature("tblp1").setIndex("legends", "\u70b9\uff1a(0.4, 0.14675)", 0);
    model.result("pg12").set("window", "window1");
    model.result("pg12").run();
    model.result("pg12").feature("tblp2").set("legendmethod", "manual");
    model.result("pg12").feature("tblp2").setIndex("legends", "\u70b9\uff1a(0.4, 0.1265)", 0);
    model.result("pg12").set("window", "window1");
    model.result("pg12").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").label("\u8d28\u91cf\u5e73\u8861");
    model.result().numerical("gev1").set("data", "dset4");
    model.result().numerical("gev1").set("expr", new String[]{"mt.massBalance"});
    model.result().numerical("gev1").set("descr", new String[]{"\u8d28\u91cf\u5e73\u8861"});
    model.result().numerical("gev1").set("unit", new String[]{"kg/s"});
    model.result().numerical("gev1").set("expr", new String[]{"mt.massBalance", "mt.dwcInt"});
    model.result().numerical("gev1")
         .set("descr", new String[]{"\u8d28\u91cf\u5e73\u8861", "\u603b\u7d2f\u79ef\u5438\u6e7f\u7387"});
    model.result().numerical("gev1").set("expr", new String[]{"mt.massBalance", "mt.dwcInt", "mt.ntfluxInt"});
    model.result().numerical("gev1")
         .set("descr", new String[]{"\u8d28\u91cf\u5e73\u8861", "\u603b\u7d2f\u79ef\u5438\u6e7f\u7387", "\u603b\u51c0\u5438\u6e7f\u7387"});
    model.result().numerical("gev1")
         .set("expr", new String[]{"mt.massBalance", "mt.dwcInt", "mt.ntfluxInt", "mt.GInt"});
    model.result().numerical("gev1")
         .set("descr", new String[]{"\u8d28\u91cf\u5e73\u8861", "\u603b\u7d2f\u79ef\u5438\u6e7f\u7387", "\u603b\u51c0\u5438\u6e7f\u7387", "\u603b\u8d28\u91cf\u6e90"});
    model.result().table().create("tbl3", "Table");
    model.result().table("tbl3").comments("\u8d28\u91cf\u5e73\u8861");
    model.result().numerical("gev1").set("table", "tbl3");
    model.result().numerical("gev1").setResult();
    model.result().create("pg13", "PlotGroup1D");
    model.result("pg13").set("data", "none");
    model.result("pg13").create("tblp1", "Table");
    model.result("pg13").feature("tblp1").set("source", "table");
    model.result("pg13").feature("tblp1").set("table", "tbl3");
    model.result("pg13").feature("tblp1").set("linewidth", "preference");
    model.result("pg13").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg13").run();
    model.result("pg13").run();
    model.result("pg13").label("\u8d28\u91cf\u5e73\u8861");
    model.result("pg13").run();
    model.result("pg13").feature("tblp1").set("legend", true);
    model.result("pg13").feature("tblp1").set("linewidth", 2);
    model.result().dataset().create("extr1", "Extrude2D");
    model.result().dataset("extr1").set("data", "dset2");
    model.result().dataset("extr1").set("zmax", "0.2");
    model.result().create("pg14", "PlotGroup3D");
    model.result("pg14").run();
    model.result("pg14").label("\u4e09\u7ef4\u76f8\u5bf9\u6e7f\u5ea6\u56fe");
    model.result("pg14").create("surf1", "Surface");
    model.result("pg14").feature("surf1").label("\u76f8\u5bf9\u6e7f\u5ea6");
    model.result("pg14").feature("surf1").set("expr", "mt.phi");
    model.result("pg14").feature("surf1").set("colortable", "JupiterAuroraBorealis");
    model.result("pg14").feature("surf1").set("colortabletrans", "reverse");
    model.result("pg14").run();
    model.result("pg14").run();

    model.title("\u6728\u8d28\u6846\u67b6\u5899\u7684\u51b7\u51dd\u98ce\u9669");

    model
         .description("\u672c\u6559\u7a0b\u4ecb\u7ecd\u5982\u4f55\u6a21\u62df\u4e8c\u7ef4\u6728\u8d28\u6846\u67b6\u5899\u4e2d\u7684\u8026\u5408\u70ed\u6e7f\u4f20\u9012\uff0c\u4ee5\u8bc4\u4f30\u5899\u5185\u7684\u51b7\u51dd\u98ce\u9669\u3002\u5728\u5ba4\u5916\u73af\u5883\u6761\u4ef6\u4fdd\u6301\u7a33\u5b9a\u7684\u524d\u63d0\u4e0b\u6bd4\u8f83\u4e86\u4e0d\u540c\u7684\u8bbe\u8ba1\u548c\u5efa\u6a21\u65b9\u6cd5\u3002\u6b64\u5916\uff0c\u8fd8\u8ba1\u7b97\u5ba4\u5916\u6e7f\u5ea6\u7684\u65e5\u53d8\u5316\u5bf9\u5899\u58c1\u6e7f\u5ea6\u5206\u5e03\u7684\u5f71\u54cd\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();

    model.label("wood_frame_wall.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
