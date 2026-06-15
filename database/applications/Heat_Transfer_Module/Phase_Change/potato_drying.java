/*
 * potato_drying.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:25 by COMSOL 6.3.0.290. */
public class potato_drying {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Heat_Transfer_Module\\Phase_Change");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");
    model.component("comp1").physics("spf").prop("AdvancedSettingProperty").set("UsePseudoTime", "1");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty")
         .set("Compressibility", "WeaklyCompressible");
    model.component("comp1").physics().create("mt", "MoistureTransportInAir", "geom1");
    model.component("comp1").physics("mt").prop("PhysicalModelProperty").set("dz", "1[m]");
    model.component("comp1").physics("mt").prop("ShapeProperty").set("order_relativehumidity_disc", "1");
    model.component("comp1").physics().create("ht", "HeatTransferInMoistAir", "geom1");

    model.component("comp1").multiphysics().create("mf1", "MoistureFlow", 2);
    model.component("comp1").multiphysics("mf1").set("Fluid_physics", "spf");
    model.component("comp1").multiphysics("mf1").set("Transport_physics", "mt");
    model.component("comp1").multiphysics().create("ham1", "HeatAndMoisture", 2);
    model.component("comp1").multiphysics("ham1").set("Heat_physics", "ht");
    model.component("comp1").multiphysics("ham1").set("Moist_physics", "mt");
    model.component("comp1").multiphysics("ham1").selection().all();
    model.component("comp1").multiphysics().create("nitf1", "NonIsothermalFlow", 2);
    model.component("comp1").multiphysics("nitf1").set("Fluid_physics", "spf");
    model.component("comp1").multiphysics("nitf1").set("Heat_physics", "ht");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/spf", true);
    model.study("std1").feature("time").setSolveFor("/physics/mt", true);
    model.study("std1").feature("time").setSolveFor("/physics/ht", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/mf1", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/ham1", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/nitf1", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("T0", "293.15[K]", "\u73af\u5883\u6e29\u5ea6");
    model.param().set("u0", "0.1[m/s]", "\u81ea\u7531\u6d41\u901f\u5ea6");
    model.param().set("phi_0", "0.1", "\u73af\u5883\u76f8\u5bf9\u6e7f\u5ea6");
    model.param().set("phi_1", "0.985", "\u591a\u5b54\u4ecb\u8d28\u76f8\u5bf9\u6e7f\u5ea6");
    model.param().set("S_il", "0.1", "\u4e0d\u53ef\u7ea6\u6db2\u76f8\u9971\u548c\u5ea6");
    model.param().set("por", "0.8", "\u5b54\u9699\u7387");
    model.param().set("kappa", "1e-14[m^2]", "\u6e17\u900f\u7387");
    model.param()
         .set("k_s", "0.21[W/(m*K)]", "\u591a\u5b54\u57fa\u4f53\u5bfc\u70ed\u7cfb\u6570\uff08\u9a6c\u94c3\u85af\u6750\u6599\u5c5e\u6027 [Hadler \u7b49\u4eba]\uff09");
    model.param()
         .set("cp_s", "1650[J/(kg*K)]", "\u591a\u5b54\u57fa\u4f53\u70ed\u5bb9\uff08\u9a6c\u94c3\u85af\u6750\u6599\u5c5e\u6027 [Hadler \u7b49\u4eba]\uff09");
    model.param()
         .set("rho_s", "1528[kg/m^3]", "\u591a\u5b54\u57fa\u4f53\u5bc6\u5ea6\uff08\u9a6c\u94c3\u85af\u6750\u6599\u5c5e\u6027 [Hadler \u7b49\u4eba]\uff09");

    model.component("comp1").func().create("pw1", "Piecewise");
    model.component("comp1").func("pw1").label("\u76f8\u5bf9\u6e17\u900f\u7387\uff0c\u6e7f\u7a7a\u6c14");
    model.component("comp1").func("pw1").set("funcname", "kappa_rma");
    model.component("comp1").func("pw1").set("arg", "S_l");
    model.component("comp1").func("pw1").setIndex("pieces", 0, 0, 0);
    model.component("comp1").func("pw1").setIndex("pieces", "1/1.1", 0, 1);
    model.component("comp1").func("pw1").setIndex("pieces", "1-1.1*S_l", 0, 2);
    model.component("comp1").func("pw1").setIndex("pieces", "1/1.1", 1, 0);
    model.component("comp1").func("pw1").setIndex("pieces", 1, 1, 1);
    model.component("comp1").func("pw1").setIndex("pieces", "eps", 1, 2);
    model.component("comp1").func().create("pw2", "Piecewise");
    model.component("comp1").func("pw2").label("\u76f8\u5bf9\u6e17\u900f\u7387\uff0c\u6db2\u76f8");
    model.component("comp1").func("pw2").set("funcname", "kappa_rl");
    model.component("comp1").func("pw2").set("arg", "S_l");
    model.component("comp1").func("pw2").setIndex("pieces", 0, 0, 0);
    model.component("comp1").func("pw2").setIndex("pieces", "S_il", 0, 1);
    model.component("comp1").func("pw2").setIndex("pieces", "eps", 0, 2);
    model.component("comp1").func("pw2").setIndex("pieces", "S_il", 1, 0);
    model.component("comp1").func("pw2").setIndex("pieces", 1, 1, 1);
    model.component("comp1").func("pw2").setIndex("pieces", "((S_l-S_il)/(1-S_il))^3", 1, 2);
    model.component("comp1").func().create("int1", "Interpolation");
    model.component("comp1").func("int1").label("\u5438\u9644\u7b49\u6e29\u7ebf");
    model.component("comp1").func("int1").set("funcname", "wc_int");
    model.component("comp1").func("int1")
         .set("table", new String[][]{{"0.003393653", "12"}, 
         {"0.029277859", "16"}, 
         {"0.087160155", "20"}, 
         {"0.164632661", "24"}, 
         {"0.247214032", "28"}, 
         {"0.326229692", "32"}, 
         {"0.397902657", "36"}, 
         {"0.461214875", "40"}, 
         {"0.516448722", "44"}, 
         {"0.564402552", "48"}, 
         {"0.606011795", "52"}, 
         {"0.642181463", "56"}, 
         {"0.673721743", "60"}, 
         {"0.701331381", "64"}, 
         {"0.725601769", "68"}, 
         {"0.747028867", "72"}, 
         {"0.766027058", "76"}, 
         {"0.782942386", "80"}, 
         {"0.798064241", "84"}, 
         {"0.811635275", "88"}, 
         {"0.823859627", "92"}, 
         {"0.834909687", "96"}, 
         {"0.844931611", "100"}, 
         {"0.854049824", "104"}, 
         {"0.86237068", "108"}, 
         {"0.86998546", "112"}, 
         {"0.876972814", "116"}, 
         {"0.883400773", "120"}, 
         {"0.889328402", "124"}, 
         {"0.894807168", "128"}, 
         {"0.899882072", "132"}, 
         {"0.904592595", "136"}, 
         {"0.908973484", "140"}, 
         {"0.913055418", "144"}, 
         {"0.916865561", "148"}, 
         {"0.920428035", "152"}, 
         {"0.923764321", "156"}, 
         {"0.926893596", "160"}, 
         {"0.929833024", "164"}, 
         {"0.932598007", "168"}, 
         {"0.935202394", "172"}, 
         {"0.937658669", "176"}, 
         {"0.939978109", "180"}, 
         {"0.94217092", "184"}, 
         {"0.944246362", "188"}, 
         {"0.946212848", "192"}, 
         {"0.948078037", "196"}, 
         {"0.949848916", "200"}, 
         {"0.951531869", "204"}, 
         {"0.953132737", "208"}, 
         {"0.954656877", "212"}, 
         {"0.956109204", "216"}, 
         {"0.957494241", "220"}, 
         {"0.958816152", "224"}, 
         {"0.960078774", "228"}, 
         {"0.961285656", "232"}, 
         {"0.962440074", "236"}, 
         {"0.963545065", "240"}, 
         {"0.964603443", "244"}, 
         {"0.965617819", "248"}, 
         {"0.966590623", "252"}, 
         {"0.96752411", "256"}, 
         {"0.968420386", "260"}, 
         {"0.969281411", "264"}, 
         {"0.970109016", "268"}, 
         {"0.970904911", "272"}, 
         {"0.971670696", "276"}, 
         {"0.972407867", "280"}, 
         {"0.97311783", "284"}, 
         {"0.973801899", "288"}, 
         {"0.974461311", "292"}, 
         {"0.975097226", "296"}, 
         {"0.975710738", "300"}, 
         {"0.976302872", "304"}, 
         {"0.976874598", "308"}, 
         {"0.977426827", "312"}, 
         {"0.977960422", "316"}, 
         {"0.978476194", "320"}, 
         {"0.978974912", "324"}, 
         {"0.979457303", "328"}, 
         {"0.979924053", "332"}, 
         {"0.980375814", "336"}, 
         {"0.980813201", "340"}, 
         {"0.981236799", "344"}, 
         {"0.981647164", "348"}, 
         {"0.982044821", "352"}, 
         {"0.982430271", "356"}, 
         {"0.982803988", "360"}, 
         {"0.983166426", "364"}, 
         {"0.983518014", "368"}, 
         {"0.983859162", "372"}, 
         {"0.984190261", "376"}, 
         {"0.984511682", "380"}, 
         {"0.98482378", "384"}, 
         {"0.985126893", "388"}, 
         {"0.985421346", "392"}, 
         {"0.985707445", "396"}, 
         {"0.985985488", "400"}, 
         {"", ""}, 
         {"", ""}, 
         {"", ""}, 
         {"", ""}, 
         {"", ""}, 
         {"", ""}, 
         {"", ""}, 
         {"", ""}, 
         {"", ""}});
    model.component("comp1").func("int1").setIndex("argunit", 1, 0);
    model.component("comp1").func("int1").setIndex("fununit", "kg/m^3", 0);

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new double[]{0.15, 0.05});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new double[]{0.04, 0.005});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new double[]{0.04, 0});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("fil1").selection("pointinsketch").set("r2", 3, 4);
    model.component("comp1").geom("geom1").feature("fil1").set("radius", "2e-3");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").common().create("ampr1", "AmbientProperties");
    model.component("comp1").common("ampr1").set("T_amb", "T0");
    model.component("comp1").common("ampr1").set("phi_amb", "phi_0");

    model.component("comp1").physics("ht").feature("init1").set("Tinit_src", "root.comp1.ampr1.T_amb");
    model.component("comp1").physics("ht").create("mporous1", "MoistPorousMediumHeatTransferModel", 2);
    model.component("comp1").physics("ht").feature("mporous1").selection().set(2);
    model.component("comp1").physics("ht").feature("mporous1").feature("pm1")
         .set("porousMatrixPropertiesType", "solidPhaseProperties");
    model.component("comp1").physics("ht").create("ifl1", "Inflow", 1);
    model.component("comp1").physics("ht").feature("ifl1").selection().set(1);
    model.component("comp1").physics("ht").feature("ifl1").set("Tustr_src", "root.comp1.ampr1.T_amb");
    model.component("comp1").physics("ht").create("ofl1", "ConvectiveOutflow", 1);
    model.component("comp1").physics("ht").feature("ofl1").selection().set(9);
    model.component("comp1").physics("spf").prop("PhysicalModelProperty")
         .set("Compressibility", "CompressibleMALT03");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("EnablePorousMediaDomains", true);
    model.component("comp1").physics("spf").create("porous1", "PorousMedium", 2);
    model.component("comp1").physics("spf").feature("porous1").selection().set(2);
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("spf").feature("inl1").selection().set(1);
    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("spf").feature("inl1").set("Uavfdf", "u0");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 1);
    model.component("comp1").physics("spf").feature("out1").selection().set(9);
    model.component("comp1").physics("mt").feature("init1").set("phi_init_src", "root.comp1.ampr1.phi_amb");
    model.component("comp1").physics("mt").create("hporous1", "HygroscopicPorousMediumModel", 2);
    model.component("comp1").physics("mt").feature("hporous1").selection().set(2);
    model.component("comp1").physics("mt").feature("hporous1").feature("lw1")
         .set("capillaryConductionModel", "diffusion");
    model.component("comp1").physics("mt").feature("hporous1").feature("lw1").set("kappa_rl", "kappa_rl(mt.sl)");
    model.component("comp1").physics("mt").feature("hporous1").feature("ma1").set("kappa_rg", "kappa_rma(mt.sl)");
    model.component("comp1").physics("mt").create("init2", "InitialValues", 2);
    model.component("comp1").physics("mt").feature("init2").selection().set(2);
    model.component("comp1").physics("mt").feature("init2").set("phi_init", "phi_1");
    model.component("comp1").physics("mt").create("ifl1", "Inflow", 1);
    model.component("comp1").physics("mt").feature("ifl1").selection().set(1);
    model.component("comp1").physics("mt").feature("ifl1").set("Tustr_src", "root.comp1.ampr1.T_amb");
    model.component("comp1").physics("mt").feature("ifl1").set("phiustr_src", "root.comp1.ampr1.phi_amb");
    model.component("comp1").physics("mt").create("ofl1", "Outflow", 1);
    model.component("comp1").physics("mt").feature("ofl1").selection().set(9);
    model.component("comp1").physics("mt").create("pi1", "PorousInterface", 1);
    model.component("comp1").physics("mt").feature("pi1").selection().set(4, 6, 7, 10, 11);

    model.component("comp1").material().create("pmat1", "PorousMedia");
    model.component("comp1").material("pmat1").label("\u9a6c\u94c3\u85af");
    model.component("comp1").material("pmat1").selection().set(2);
    model.component("comp1").material("pmat1").feature().create("solid1", "Solid", "comp1");
    model.component("comp1").material("pmat1").feature("solid1").set("vfrac", "1-por");
    model.component("comp1").material("pmat1").feature("solid1").propertyGroup("def")
         .set("density", new String[]{"rho_s"});
    model.component("comp1").material("pmat1").feature("solid1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k_s"});
    model.component("comp1").material("pmat1").feature("solid1").propertyGroup("def")
         .set("heatcapacity", new String[]{"cp_s"});
    model.component("comp1").material("pmat1").propertyGroup("def")
         .set("diffusion", new String[]{"1e-8[m^2/s]*exp(-2.8+2*w_c/rho_s/(1-por))"});
    model.component("comp1").material("pmat1").propertyGroup("def").set("watercontent", new String[]{"wc_int(phi)"});
    model.component("comp1").material("pmat1").propertyGroup("def")
         .set("hydraulicpermeability", new String[]{"kappa"});
    model.component("comp1").material("pmat1").propertyGroup("def").addInput("relativehumidity");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().set(2);
    model.component("comp1").cpl("intop1").label("\u591a\u5b54\u4ecb\u8d28\u79ef\u5206");
    model.component("comp1").cpl("intop1").set("opname", "intopPorous");

    model.component("comp1").mesh("mesh1").autoMeshSize(2);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature().move("stat", 0);
    model.study("std1").feature("stat").setSolveFor("/physics/mt", false);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", false);
    model.study("std1").feature("time").set("tlist", "range(0,10,90) range(100,100,900) range(1000,1000,40000)");
    model.study("std1").feature("time").set("usertol", true);
    model.study("std1").feature("time").set("rtol", 0.001);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u901f\u5ea6 (spf)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u538b\u529b (spf)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("con1", "Contour");
    model.result("pg2").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("expr", "p");
    model.result("pg2").feature("con1").set("number", 40);
    model.result("pg2").feature("con1").set("levelrounding", false);
    model.result("pg2").feature("con1").set("smooth", "internal");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("data", "parent");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u76f8\u5bf9\u6e7f\u5ea6 (mt)");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "mt.phi");
    model.result("pg3").feature("surf1").set("colortable", "Kyanite");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u6e29\u5ea6 (ht)");
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("solutionparams", "parent");
    model.result("pg4").feature("surf1").set("expr", "T");
    model.result("pg4").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").label("\u6e29\u5ea6\u548c\u6d41\u4f53\u6d41\u52a8 (nitf1)");
    model.result("pg5").set("showlegendsunit", true);
    model.result("pg5").feature().create("surf1", "Surface");
    model.result("pg5").feature("surf1").label("\u6d41\u4f53\u6e29\u5ea6");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("solutionparams", "parent");
    model.result("pg5").feature("surf1").set("expr", "nitf1.T");
    model.result("pg5").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg5").feature("surf1").set("smooth", "internal");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("data", "parent");
    model.result("pg5").feature("surf1").feature().create("sel1", "Selection");
    model.result("pg5").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg5").feature("surf1").feature("sel1").selection().set(1, 2);
    model.result("pg5").feature().create("arws1", "ArrowSurface");
    model.result("pg5").feature("arws1").label("\u6d41\u4f53\u6d41\u52a8");
    model.result("pg5").feature("arws1").set("showsolutionparams", "on");
    model.result("pg5").feature("arws1").set("solutionparams", "parent");
    model.result("pg5").feature("arws1").set("expr", new String[]{"nitf1.ux", "nitf1.uy"});
    model.result("pg5").feature("arws1").set("xnumber", 30);
    model.result("pg5").feature("arws1").set("ynumber", 30);
    model.result("pg5").feature("arws1").set("arrowtype", "cone");
    model.result("pg5").feature("arws1").set("arrowlength", "logarithmic");
    model.result("pg5").feature("arws1").set("showsolutionparams", "on");
    model.result("pg5").feature("arws1").set("data", "parent");
    model.result("pg5").feature("arws1").feature().create("col1", "Color");
    model.result("pg5").feature("arws1").feature("col1").set("showcolordata", "off");
    model.result("pg5").feature("arws1").feature().create("filt1", "Filter");
    model.result("pg5").feature("arws1").feature("filt1").set("expr", "spf.U>nitf1.Uave");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 24, 0);
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 24, 0);
    model.result("pg4").run();
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").label("\u9971\u548c (mt)");
    model.result("pg6").feature().create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("solutionparams", "parent");
    model.result("pg6").feature("surf1").set("expr", "mt.sl");
    model.result("pg6").feature("surf1").set("colortable", "Kyanite");
    model.result("pg6").feature("surf1").set("smooth", "internal");
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("data", "parent");
    model.result("pg6").label("\u9971\u548c (mt)");
    model.result("pg6").run();
    model.result("pg6").setIndex("looplevel", 24, 0);
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u591a\u5b54\u4ecb\u8d28\u4e2d\u6e7f\u5ea6\u968f\u65f6\u95f4\u7684\u53d8\u5316");
    model.result("pg7").set("titletype", "manual");
    model.result("pg7").set("title", "\u9a6c\u94c3\u85af\u4e2d\u7684\u603b\u6e7f\u5ea6");
    model.result("pg7").set("ylabelactive", true);
    model.result("pg7").set("ylabel", "\u9a6c\u94c3\u85af\u4e2d\u7684\u603b\u6e7f\u5ea6 (kg/m)");
    model.result("pg7").create("glob1", "Global");
    model.result("pg7").feature("glob1").set("markerpos", "datapoints");
    model.result("pg7").feature("glob1").set("linewidth", "preference");
    model.result("pg7").feature("glob1").setIndex("expr", "intopPorous(mt.wcVar)", 0);
    model.result("pg7").feature("glob1").set("linewidth", 2);
    model.result("pg7").feature("glob1").set("legend", false);
    model.result("pg7").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").label("\u8d28\u91cf\u5e73\u8861");
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
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u8d28\u91cf\u5e73\u8861");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").set("data", "none");
    model.result("pg8").create("tblp1", "Table");
    model.result("pg8").feature("tblp1").set("source", "table");
    model.result("pg8").feature("tblp1").set("table", "tbl1");
    model.result("pg8").feature("tblp1").set("linewidth", "preference");
    model.result("pg8").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg8").run();
    model.result("pg8").run();
    model.result("pg8").label("\u8d28\u91cf\u5e73\u8861");
    model.result("pg8").run();
    model.result("pg8").feature("tblp1").set("legend", true);
    model.result("pg8").feature("tblp1").set("linewidth", 2);
    model.result("pg3").run();

    model.title("\u9a6c\u94c3\u85af\u6837\u54c1\u7684\u5e72\u71e5");

    model
         .description("\u672c\u6559\u7a0b\u63cf\u8ff0\u4e00\u79cd\u70d8\u5e72\u7bb1\uff0c\u5176\u4e2d\u6709\u5c42\u6d41\u6c14\u6d41\u901a\u8fc7\u975e\u9971\u548c\u591a\u5b54\u4ecb\u8d28\u3002\u7a7a\u6c14\u5728\u5165\u53e3\u5904\u662f\u5e72\u71e5\u7684\uff0c\u5728\u6d41\u7ecf\u591a\u5b54\u4ecb\u8d28\u65f6\uff0c\u5176\u6e7f\u5ea6\u4f1a\u4e0a\u5347\u3002\u5176\u4e2d\u5047\u8bbe\u6c34\u53ea\u4ee5\u84b8\u6c7d\u7684\u5f62\u5f0f\u79bb\u5f00\u9a6c\u94c3\u85af\u6837\u54c1\uff0c\u8ba1\u7b97\u591a\u5b54\u4ecb\u8d28\u4e2d\u7684\u542b\u6c34\u9971\u548c\u5ea6\u968f\u65f6\u95f4\u7684\u53d8\u5316\u60c5\u51b5\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("potato_drying.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
