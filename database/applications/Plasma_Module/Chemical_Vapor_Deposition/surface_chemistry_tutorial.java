/*
 * surface_chemistry_tutorial.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:01 by COMSOL 6.3.0.290. */
public class surface_chemistry_tutorial {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Plasma_Module\\Chemical_Vapor_Deposition");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("hs", "HeavySpeciesTransport", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/hs", true);

    model.component("comp1").geom("geom1").create("sq1", "Square");
    model.component("comp1").geom("geom1").feature("sq1").set("size", 0.1);
    model.component("comp1").geom("geom1").run("sq1");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.03, 0, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.07, 1, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 1, 1);

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("mass_domain", "at(0,intop1(hs.rho))-intop1(hs.rho)", "\u57df\u4e2d\u7684\u8d28\u91cf\u53d8\u5316\u91cf");
    model.component("comp1").variable("var1")
         .set("mass_bulk", "intop2(Hk_Si_bulk*2329[kg/m^3])", "\u672c\u4f53\u7269\u8d28\u603b\u8d28\u91cf");
    model.component("comp1").variable("var1")
         .set("mass_Si_surf", "intop2(Zk_Si_surf*hs.Gammat/hs.sigmak_Si_surf*0.028[kg/mol])", "Si \u8868\u9762\u7269\u8d28\u603b\u8d28\u91cf");
    model.component("comp1").variable("var1")
         .set("mass_SiH_surf", "intop2(Zk_SiH_surf*hs.Gammat/hs.sigmak_SiH_surf*0.029[kg/mol])", "SiH \u8868\u9762\u7269\u8d28\u603b\u8d28\u91cf");
    model.component("comp1").variable("var1")
         .set("dmass_Si_surf", "mass_Si_surf-at(0,mass_Si_surf)", "Si \u8868\u9762\u7269\u8d28\u7684\u603b\u8d28\u91cf\u51cf\u53bb\u521d\u59cb\u8d28\u91cf");
    model.component("comp1").variable("var1")
         .set("dmass_SiH_surf", "mass_SiH_surf-at(0,mass_SiH_surf)", "SiH \u8868\u9762\u7269\u8d28\u7684\u603b\u8d28\u91cf\u51cf\u53bb\u521d\u59cb\u8d28\u91cf");
    model.component("comp1").variable("var1")
         .set("mass_surf", "dmass_Si_surf+dmass_SiH_surf", "\u6240\u6709\u8868\u9762\u7269\u8d28\u7684\u603b\u8d28\u91cf\u51cf\u53bb\u521d\u59cb\u8d28\u91cf");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().set(1);
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop2").selection().set(4);

    model.component("comp1").physics("hs").prop("DiffusionModel").set("DiffusionModel", "GlobalModel");
    model.component("comp1").physics("hs").create("sr1", "SurfaceReaction", 1);
    model.component("comp1").physics("hs").feature("sr1").set("formula", "SiH4+2Si(s)=>Si(b)+2SiH(s)+H2");
    model.component("comp1").physics("hs").feature("sr1").selection().set(4);
    model.component("comp1").physics("hs").feature("sr1").set("gammaf", "2e-4");
    model.component("comp1").physics("hs").create("sr2", "SurfaceReaction", 1);
    model.component("comp1").physics("hs").feature("sr2").set("formula", "SiH(s)=>Si(s)+0.5H2");
    model.component("comp1").physics("hs").feature("sr2").selection().set(4);
    model.component("comp1").physics("hs").feature("sr2").set("SpecifyReactionUsing", "UseRate");
    model.component("comp1").physics("hs").feature("sr2").set("ksf", 3);
    model.component("comp1").physics("hs").feature("SiH4").set("FromMassConstraint", true);
    model.component("comp1").physics("hs").feature("SiH4").set("PresetSpeciesData", "SiH4");
    model.component("comp1").physics("hs").feature("H2").set("PresetSpeciesData", "H2");
    model.component("comp1").physics("hs").feature("H2").set("x0", "1E-3");
    model.component("comp1").physics("hs").feature("Si_surf").set("PresetSpeciesData", "Si");
    model.component("comp1").physics("hs").feature("Si_surf").set("Zk0", 0.995);
    model.component("comp1").physics("hs").feature("Si_bulk").set("PresetSpeciesData", "Si");
    model.component("comp1").physics("hs").feature("SiH_surf").set("PresetSpeciesData", "SiH");
    model.component("comp1").physics("hs").feature("SiH_surf").set("Zk0", 0.005);
    model.component("comp1").physics("hs").feature("cdm1").set("p0", 13.3);

    model.study("std1").label("\u5168\u5c40\u6a21\u578b");
    model.study("std1").setGenPlots(false);
    model.study("std1").setGenConv(false);
    model.study("std1").feature("time").set("tlist", "range(0,5,300)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").label("\u5e73\u5747\u538b\u529b\u9664\u4ee5\u5e73\u5747\u521d\u59cb\u538b\u529b");
    model.result("pg1").set("titletype", "label");
    model.result("pg1").set("legendpos", "lowerright");
    model.result("pg1").create("glob1", "Global");
    model.result("pg1").feature("glob1").set("markerpos", "datapoints");
    model.result("pg1").feature("glob1").set("linewidth", "preference");
    model.result("pg1").feature("glob1").label("\u5168\u5c40\u6a21\u578b");
    model.result("pg1").feature("glob1").set("data", "dset1");
    model.result("pg1").feature("glob1").setIndex("expr", "hs.pA/at(0,hs.pA)", 0);
    model.result("pg1").feature("glob1").set("legendmethod", "manual");
    model.result("pg1").feature("glob1").setIndex("legends", "\u5168\u5c40\u6a21\u578b", 0);
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u5bc6\u5ea6\u6bd4");
    model.result("pg2").set("titletype", "label");
    model.result("pg2").set("legendpos", "lowerright");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("linewidth", "preference");
    model.result("pg2").feature("glob1").label("\u5168\u5c40\u6a21\u578b");
    model.result("pg2").feature("glob1").set("data", "dset1");
    model.result("pg2").feature("glob1").setIndex("expr", "at(0,hs.rho)/hs.rho", 0);
    model.result("pg2").feature("glob1").set("legendmethod", "manual");
    model.result("pg2").feature("glob1").setIndex("legends", "\u5168\u5c40\u6a21\u578b", 0);
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u53cd\u5e94\u5668\u603b\u8d28\u91cf");
    model.result("pg3").set("titletype", "label");
    model.result("pg3").set("legendpos", "lowerright");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").label("\u5168\u5c40\u6a21\u578b");
    model.result("pg3").feature("glob1").set("data", "dset1");
    model.result("pg3").feature("glob1").setIndex("expr", "mass_domain", 0);
    model.result("pg3").feature("glob1").setIndex("expr", "mass_surf+mass_bulk", 1);
    model.result("pg3").feature("glob1").set("legendmethod", "manual");
    model.result("pg3").feature("glob1").setIndex("legends", "mass_domain\uff1a\u5168\u5c40\u6a21\u578b", 0);
    model.result("pg3").feature("glob1").setIndex("legends", "mass_surf+mass_bulk\uff1a\u5168\u5c40\u6a21\u578b", 1);
    model.result("pg3").run();
    model.result().dataset().create("edg1", "Edge2D");
    model.result().dataset("edg1").selection().set(4);
    model.result().dataset().create("par1", "Parametric1D");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").run();
    model.result("pg4").label("\u4e00\u7ef4\u53c2\u6570\u5316\u62c9\u4f38 1 - \u5168\u5c40\u6a21\u578b");
    model.result("pg4").set("data", "par1");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "Hk_Si_bulk");
    model.result("pg4").feature("surf1").set("unit", "\u00c5");
    model.result("pg4").feature("surf1").create("hght1", "Height");
    model.result("pg4").run();

    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");

    model.study("std1").feature("time").setSolveFor("/physics/spf", false);

    model.component("comp1").physics().create("ht", "HeatTransferInFluids", "geom1");

    model.study("std1").feature("time").setSolveFor("/physics/ht", false);

    model.component("comp1").physics("hs").prop("DiffusionModel").set("DiffusionModel", "MixtureAveraged");
    model.component("comp1").physics("hs").prop("TransportSettings").set("calcThermo", true);
    model.component("comp1").physics("hs").prop("TransportSettings").set("Migration", false);
    model.component("comp1").physics("hs").prop("TransportSettings").set("Convection", true);
    model.component("comp1").physics("hs").feature("cdm1").set("T_src", "root.comp1.T");
    model.component("comp1").physics("hs").feature("cdm1").set("pA_src", "root.comp1.spf.pA");
    model.component("comp1").physics("hs").feature("cdm1").set("u_src", "root.comp1.u");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty")
         .set("Compressibility", "CompressibleMALT03");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("pref", "13.3[Pa]");
    model.component("comp1").physics("spf").prop("ConsistentStabilization").set("CrosswindDiffusion", false);
    model.component("comp1").physics("spf").prop("ShapeProperty").set("order_fluid", 2);
    model.component("comp1").physics("spf").feature("fp1").set("rho_mat", "root.comp1.hs.rho");
    model.component("comp1").physics("spf").feature("fp1").set("mu_mat", "root.comp1.hs.eta");
    model.component("comp1").physics("spf").create("wallbc2", "WallBC", 1);
    model.component("comp1").physics("spf").feature("wallbc2").selection().set(4);
    model.component("comp1").physics("spf").feature("wallbc2").set("TranslationalVelocityOption", "Manual");
    model.component("comp1").physics("spf").feature("wallbc2").set("utr", new String[]{"0", "hs.mfin/spf.rho", "0"});
    model.component("comp1").physics("spf").feature("wallbc2").set("constraintOptions", "nitscheConstraints");
    model.component("comp1").physics("ht").feature("fluid1").set("u_src", "root.comp1.u");
    model.component("comp1").physics("ht").feature("fluid1").set("k_mat", "root.comp1.hs.kxx");
    model.component("comp1").physics("ht").feature("fluid1").set("fluidType", "idealGas");
    model.component("comp1").physics("ht").feature("fluid1").set("gasConstantType", "numberAve");
    model.component("comp1").physics("ht").feature("fluid1").set("molarmass_mat", "root.comp1.hs.Mn");
    model.component("comp1").physics("ht").feature("fluid1").set("heatcapacity_mat", "root.comp1.hs.Cptot");
    model.component("comp1").physics("ht").create("temp1", "TemperatureBoundary", 1);
    model.component("comp1").physics("ht").feature("temp1").set("T0", 300);
    model.component("comp1").physics("ht").feature("temp1").selection().set(1, 3, 6);
    model.component("comp1").physics("ht").feature("init1").set("Tinit", 300);
    model.component("comp1").physics("ht").create("bhs1", "BoundaryHeatSource", 1);
    model.component("comp1").physics("ht").feature("bhs1").selection().set(4);
    model.component("comp1").physics("ht").feature("bhs1").set("Qb_input_src", "root.comp1.hs.Qst");

    model.component("comp1").mesh("mesh1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("size1").selection().geom("geom1", 0);
    model.component("comp1").mesh("mesh1").feature("size1").selection().set(3, 4);
    model.component("comp1").mesh("mesh1").feature("size1").set("hauto", 1);
    model.component("comp1").mesh("mesh1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmax", "0.0002");
    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().set(4);
    model.component("comp1").mesh("mesh1").feature("edg1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hauto", 1);
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").run();

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/hs", true);
    model.study("std2").feature("time").setSolveFor("/physics/spf", true);
    model.study("std2").feature("time").setSolveFor("/physics/ht", true);
    model.study("std2").feature("time").set("tlist", "range(0,5,300)");
    model.study("std2").label("\u7a7a\u95f4\u76f8\u5173\u6a21\u578b");
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("t1").set("atolglobalmethod", "unscaled");
    model.sol("sol2").feature("t1").set("initialstepbdf", 0.001);
    model.sol("sol2").runAll();

    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").setIndex("looplevel", 61, 0);
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"hs.x_wSiH4"});
    model.result("pg5").feature("surf1").set("colortable", "Prism");
    model.result("pg5").label("\u6469\u5c14\u5206\u6570 (hs)");
    model.result().dataset("dset2").set("geom", "geom1");
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").label("\u901f\u5ea6 (spf)");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").setIndex("looplevel", 61, 0);
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").feature().create("surf1", "Surface");
    model.result("pg6").feature("surf1").label("\u8868\u9762");
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("expr", "spf.U");
    model.result("pg6").feature("surf1").set("smooth", "internal");
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("data", "parent");
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").label("\u538b\u529b (spf)");
    model.result("pg7").set("data", "dset2");
    model.result("pg7").setIndex("looplevel", 61, 0);
    model.result("pg7").set("frametype", "spatial");
    model.result("pg7").feature().create("con1", "Contour");
    model.result("pg7").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg7").feature("con1").set("showsolutionparams", "on");
    model.result("pg7").feature("con1").set("expr", "p");
    model.result("pg7").feature("con1").set("number", 40);
    model.result("pg7").feature("con1").set("levelrounding", false);
    model.result("pg7").feature("con1").set("smooth", "internal");
    model.result("pg7").feature("con1").set("showsolutionparams", "on");
    model.result("pg7").feature("con1").set("data", "parent");
    model.result().create("pg8", "PlotGroup2D");
    model.result("pg8").label("\u6e29\u5ea6 (ht)");
    model.result("pg8").set("data", "dset2");
    model.result("pg8").setIndex("looplevel", 61, 0);
    model.result("pg8").feature().create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("showsolutionparams", "on");
    model.result("pg8").feature("surf1").set("solutionparams", "parent");
    model.result("pg8").feature("surf1").set("expr", "T");
    model.result("pg8").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg8").feature("surf1").set("showsolutionparams", "on");
    model.result("pg8").feature("surf1").set("data", "parent");
    model.result("pg5").run();
    model.result("pg6").run();
    model.result("pg6").setIndex("looplevel", 2, 0);
    model.result("pg6").run();
    model.result("pg6").feature("surf1").set("expr", "v");
    model.result("pg6").run();
    model.result("pg8").run();
    model.result("pg8").setIndex("looplevel", 2, 0);
    model.result("pg8").run();
    model.result("pg8").feature("surf1").set("expr", "T-300[K]");
    model.result("pg8").run();
    model.result().create("pg9", "PlotGroup2D");
    model.result("pg9").run();
    model.result("pg9").label("\u6269\u6563\u901f\u5ea6\uff0c\u6c22");
    model.result("pg9").setIndex("looplevel", 2, 0);
    model.result("pg9").set("data", "dset2");
    model.result("pg9").create("surf1", "Surface");
    model.result("pg9").feature("surf1").set("expr", "hs.Vdy_wH2");
    model.result("pg9").run();
    model.result("pg1").run();
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "\u5e73\u5747\u538b\u529b\u9664\u4ee5\u5e73\u5747\u521d\u59cb\u538b\u529b");
    model.result("pg1").create("glob2", "Global");
    model.result("pg1").feature("glob2").set("markerpos", "datapoints");
    model.result("pg1").feature("glob2").set("linewidth", "preference");
    model.result("pg1").feature("glob2").label("\u7a7a\u95f4\u76f8\u5173\u6a21\u578b");
    model.result("pg1").feature("glob2").set("data", "dset2");
    model.result("pg1").feature("glob2").setIndex("expr", "intop1(spf.pA)/at(0,intop1(spf.pA))", 0);
    model.result("pg1").feature("glob2").set("legendmethod", "manual");
    model.result("pg1").feature("glob2").setIndex("legends", "\u7a7a\u95f4\u76f8\u5173\u6a21\u578b", 0);
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2")
         .set("ylabel", "\u521d\u59cb\u8d28\u91cf\u9664\u4ee5\u53cd\u5e94\u5668\u4e2d\u7684\u8d28\u91cf");
    model.result("pg2").create("glob2", "Global");
    model.result("pg2").feature("glob2").set("markerpos", "datapoints");
    model.result("pg2").feature("glob2").set("linewidth", "preference");
    model.result("pg2").feature("glob2").label("\u7a7a\u95f4\u76f8\u5173\u6a21\u578b");
    model.result("pg2").feature("glob2").set("data", "dset2");
    model.result("pg2").feature("glob2").setIndex("expr", "at(0,intop1(hs.rho))/intop1(hs.rho)", 0);
    model.result("pg2").feature("glob2").set("legendmethod", "manual");
    model.result("pg2").feature("glob2").setIndex("legends", "\u7a7a\u95f4\u76f8\u5173\u6a21\u578b", 0);
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "\u8d28\u91cf\u5bc6\u5ea6 (kg/m)");
    model.result("pg3").create("glob2", "Global");
    model.result("pg3").feature("glob2").set("markerpos", "datapoints");
    model.result("pg3").feature("glob2").set("linewidth", "preference");
    model.result("pg3").feature("glob2").label("\u7a7a\u95f4\u76f8\u5173\u6a21\u578b");
    model.result("pg3").feature("glob2").set("data", "dset2");
    model.result("pg3").feature("glob2").setIndex("expr", "mass_domain", 0);
    model.result("pg3").feature("glob2").setIndex("expr", "mass_surf+mass_bulk", 1);
    model.result("pg3").feature("glob2").set("legendmethod", "manual");
    model.result("pg3").feature("glob2")
         .setIndex("legends", "mass_domain\uff1a\u7a7a\u95f4\u76f8\u5173\u6a21\u578b", 0);
    model.result("pg3").feature("glob2")
         .setIndex("legends", "mass_surf+mass_bulk\uff1a\u7a7a\u95f4\u76f8\u5173\u6a21\u578b", 1);
    model.result("pg3").run();
    model.result().dataset().create("edg2", "Edge2D");
    model.result().dataset("edg2").set("data", "dset2");
    model.result().dataset("edg2").selection().set(4);
    model.result().dataset().create("par2", "Parametric1D");
    model.result().dataset("par2").set("data", "edg2");
    model.result("pg4").run();
    model.result().duplicate("pg10", "pg4");
    model.result("pg10").run();
    model.result("pg10")
         .label("\u4e00\u7ef4\u53c2\u6570\u5316\u62c9\u4f38 1 - \u7a7a\u95f4\u76f8\u5173\u6a21\u578b");
    model.result("pg10").set("data", "par2");
    model.result("pg10").run();

    model
         .title("\u4f7f\u7528\u7b49\u79bb\u5b50\u4f53\u6a21\u5757\u7684\u8868\u9762\u5316\u5b66\u53cd\u5e94\u6559\u7a0b");

    model
         .description("\u8868\u9762\u5316\u5b66\u5e38\u5e38\u662f\u53cd\u5e94\u6d41\u5efa\u6a21\u4e2d\u88ab\u5ffd\u89c6\u7684\u4e00\u4e2a\u65b9\u9762\u3002\u8be5\u6559\u5b66\u6a21\u578b\u6f14\u793a\u5982\u4f55\u5c06\u8868\u9762\u53cd\u5e94\u548c\u7269\u8d28\u6dfb\u52a0\u5230\u5316\u5b66\u6c14\u76f8\u6c89\u79ef (CVD) \u7b49\u7814\u7a76\u8fc7\u7a0b\u4e2d\uff0c\u5e76\u5bf9\u7845\u5728\u6676\u7247\u4e0a\u7684\u751f\u957f\u8fdb\u884c\u5efa\u6a21\u3002\n\n\u6700\u521d\uff0c\u672c\u4f8b\u4f7f\u7528\u5168\u5c40\u6a21\u578b\u6765\u7814\u7a76\u5305\u542b\u590d\u6742\u5316\u5b66\u7269\u8d28\u7684\u5e7f\u6cdb\u53c2\u6570\u533a\u57df\u3002\u7136\u540e\uff0c\u5efa\u7acb\u5e76\u8fd0\u884c\u7a7a\u95f4\u76f8\u5173\u6a21\u578b\u3002\u4ed4\u7ec6\u5206\u6790\u7cfb\u7edf\u4e2d\u7684\u603b\u4f53\u8d28\u91cf\u5e73\u8861\uff0c\u540c\u65f6\u7814\u7a76\u8d28\u91cf\u5e73\u5747\u901f\u5ea6\u4e0e\u6269\u6563\u901f\u5ea6\u4e4b\u95f4\u7684\u5dee\u5f02\u3002\u6a21\u578b\u6f14\u793a\u7cfb\u7edf\u4e2d\u7684\u603b\u8d28\u91cf\u548c\u6469\u5c14\u6d53\u5ea6\u5b88\u6052\u3002\u6700\u540e\uff0c\u7814\u7a76\u4e86\u6c89\u79ef\u7845\u9ad8\u5ea6\u968f\u65f6\u95f4\u53d8\u5316\u7684\u60c5\u51b5\u3002");

    model.label("surface_chemistry_tutorial.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
