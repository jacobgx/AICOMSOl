/*
 * ht_pem_flow_field.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:08 by COMSOL 6.3.0.290. */
public class ht_pem_flow_field {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Fuel_Cell_and_Electrolyzer_Module\\Fuel_Cells");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("fc", "HydrogenFuelCell", "geom1");
    model.component("comp1").physics("fc").prop("H2GasMixture").set("H2O", "1");
    model.component("comp1").physics("fc").prop("H2GasMixture").set("GasPhaseDiffusion", "1");
    model.component("comp1").physics("fc").prop("O2GasMixture").set("H2O", "1");
    model.component("comp1").physics("fc").prop("O2GasMixture").set("N2", "1");
    model.component("comp1").physics("fc").prop("O2GasMixture").set("GasPhaseDiffusion", "1");
    model.component("comp1").physics("fc").prop("DefaultElectrodeReactionSettings")
         .set("ChargeCarryingIon", "Proton");
    model.component("comp1").physics("fc").prop("DefaultElectrodeReactionSettings").set("OperationMode", "FuelCell");
    model.component("comp1").physics("fc").prop("DefaultElectrodeReactionSettings").set("TRHE", "50[degC]");
    model.component("comp1").physics().create("fp", "FreeAndPorousMediaFlow", "geom1");

    model.study().create("std1");
    model.study("std1").create("cdi", "CurrentDistributionInitialization");
    model.study("std1").feature("cdi").set("ftplistmethod", "manual");
    model.study("std1").feature("cdi").set("solnum", "auto");
    model.study("std1").feature("cdi").set("notsolnum", "auto");
    model.study("std1").feature("cdi").set("outputmap", new String[]{});
    model.study("std1").feature("cdi").set("ngenAUX", "1");
    model.study("std1").feature("cdi").set("goalngenAUX", "1");
    model.study("std1").feature("cdi").set("ngenAUX", "1");
    model.study("std1").feature("cdi").set("goalngenAUX", "1");
    model.study("std1").feature("cdi").setSolveFor("/physics/fc", true);
    model.study("std1").feature("cdi").setSolveFor("/physics/fp", true);
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").set("ftplistmethod", "manual");
    model.study("std1").feature("stat").set("solnum", "auto");
    model.study("std1").feature("stat").set("notsolnum", "auto");
    model.study("std1").feature("stat").set("outputmap", new String[]{});
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").setSolveFor("/physics/fc", true);
    model.study("std1").feature("stat").setSolveFor("/physics/fp", true);

    model.component("comp1").geom("geom1").insertFile("ht_pem_flow_field_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("difsel6");

    model.param().label("\u51e0\u4f55\u53c2\u6570");
    model.param().create("par2");
    model.param("par2").label("\u7269\u7406\u573a\u53c2\u6570");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("T", "180+273.15[K]", "\u7535\u6c60\u6e29\u5ea6");
    model.param("par2").set("eps_gdl", "0.4", "GDL \u5b54\u9699\u7387");
    model.param("par2").set("kappa_gdl", "1.18e-11[m^2]", "GDL \u6e17\u900f\u7387");
    model.param("par2").set("U_in", "2[m/s]", "\u5165\u53e3\u901f\u5ea6");
    model.param("par2").set("sigma_l", "9.825[S/m]", "\u7535\u89e3\u8d28\u7535\u5bfc\u7387");
    model.param("par2").set("sigma_gdl", "222[S/m]", "GDL \u7535\u5b50\u7535\u5bfc\u7387");
    model.param("par2").set("T_hum", "28[degC]", "\u8fdb\u6c14\u7684\u52a0\u6e7f\u6e29\u5ea6");
    model.param("par2").set("H_electrode", "50[um]", "\u7535\u6781\u539a\u5ea6");
    model.param("par2")
         .set("i0_H2_ref", "1e2[A/m^2]", "\u53c2\u8003\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u6c22\u6c27\u5316");
    model.param("par2")
         .set("i0_O2_ref", "1e-3[A/m^2]", "\u53c2\u8003\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u6c27\u8fd8\u539f");
    model.param("par2").set("Av", "1e7[m^2/m^3]", "\u6bd4\u8868\u9762\u79ef");
    model.param("par2").set("alpha_a_O2", "3", "\u9633\u6781\u4f20\u9012\u7cfb\u6570\uff0c\u6c27\u8fd8\u539f");
    model.param("par2").set("alpha_a_H2", "0.5", "\u9633\u6781\u4f20\u9012\u7cfb\u6570\uff0c\u6c22\u6c27\u5316");
    model.param("par2")
         .set("E_cell", "1[V]", "\u7535\u6c60\u7535\u538b\uff08\u968f\u8f85\u52a9\u626b\u63cf\u53d8\u5316\uff09");

    model.component("comp1").multiphysics().create("rfo1", "ReactingFlowO2GasPhase", 3);

    model.component("comp1").physics("fc").prop("H2GasMixture").set("H2O", false);
    model.component("comp1").physics("fc").create("mem1", "Membrane", 3);
    model.component("comp1").physics("fc").feature("mem1").selection().named("geom1_ext2_dom");
    model.component("comp1").physics("fc").create("h2gdl1", "H2GasDiffusionLayer", 3);
    model.component("comp1").physics("fc").feature("h2gdl1").selection().named("geom1_intsel3");
    model.component("comp1").physics("fc").feature("h2gdl1")
         .set("sigmas", new String[]{"sigma_gdl", "0", "0", "0", "sigma_gdl", "0", "0", "0", "sigma_gdl"});
    model.component("comp1").physics("fc").create("o2gdl1", "O2GasDiffusionLayer", 3);
    model.component("comp1").physics("fc").feature("o2gdl1").selection().named("geom1_intsel4");
    model.component("comp1").physics("fc").feature("o2gdl1")
         .set("sigmas", new String[]{"sigma_gdl", "0", "0", "0", "sigma_gdl", "0", "0", "0", "sigma_gdl"});
    model.component("comp1").physics("fc").feature("o2gdl1").set("epsg", "eps_gdl");
    model.component("comp1").physics("fc").create("th2gde1", "ThinH2GasDiffusionElectrode", 2);
    model.component("comp1").physics("fc").feature("th2gde1").selection().named("geom1_boxsel8");
    model.component("comp1").physics("fc").feature("th2gde1").set("d_gde", "H_electrode");
    model.component("comp1").physics("fc").feature("th2gde1").feature("th2gder1").set("i0_ref", "i0_H2_ref");
    model.component("comp1").physics("fc").feature("th2gde1").feature("th2gder1").set("alphaa", "alpha_a_H2");
    model.component("comp1").physics("fc").feature("th2gde1").feature("th2gder1").set("Av", "Av");
    model.component("comp1").physics("fc").create("to2gde1", "ThinO2GasDiffusionElectrode", 2);
    model.component("comp1").physics("fc").feature("to2gde1").selection().named("geom1_boxsel7");
    model.component("comp1").physics("fc").feature("to2gde1").set("d_gde", "H_electrode");
    model.component("comp1").physics("fc").feature("to2gde1").feature("to2gder1").set("i0_ref", "i0_O2_ref");
    model.component("comp1").physics("fc").feature("to2gde1").feature("to2gder1").set("alphaa", "alpha_a_O2");
    model.component("comp1").physics("fc").feature("to2gde1").feature("to2gder1").set("Av", "Av");
    model.component("comp1").physics("fc").create("o2fch1", "O2FlowChannel", 3);
    model.component("comp1").physics("fc").feature("o2fch1").selection().named("geom1_intsel2");
    model.component("comp1").physics("fc").feature("icph1").set("sigmal_mat", "userdef");
    model.component("comp1").physics("fc").feature("icph1")
         .set("sigmal", new String[]{"sigma_l", "0", "0", "0", "sigma_l", "0", "0", "0", "sigma_l"});
    model.component("comp1").physics("fc").feature("ecph1").create("egnd1", "ElectricGround", 2);
    model.component("comp1").physics("fc").feature("ecph1").feature("egnd1").selection().named("geom1_difsel5");
    model.component("comp1").physics("fc").feature("ecph1").create("pot1", "ElectricPotential", 2);
    model.component("comp1").physics("fc").feature("ecph1").feature("pot1").selection().named("geom1_difsel2");
    model.component("comp1").physics("fc").feature("ecph1").feature("pot1").set("phisbnd", "E_cell");
    model.component("comp1").physics("fc").feature("o2gasph1").create("o2in1", "O2Inlet", 2);
    model.component("comp1").physics("fc").feature("o2gasph1").feature("o2in1").selection().named("geom1_boxsel4");
    model.component("comp1").physics("fc").feature("o2gasph1").create("o2out1", "O2Outlet", 2);
    model.component("comp1").physics("fc").feature("o2gasph1").feature("o2out1").selection().named("geom1_boxsel5");
    model.component("comp1").physics("fc").feature("o2gasph1").feature("init1")
         .set("MixtureSpecification", "HumidifiedAir");
    model.component("comp1").physics("fc").feature("o2gasph1").feature("init1").set("T_hum", "T_hum");
    model.component("comp1").physics("fp").selection().named("geom1_unisel1");
    model.component("comp1").physics("fp").prop("PhysicalModelProperty")
         .set("Compressibility", "WeaklyCompressible");
    model.component("comp1").physics("fp").create("porous1", "PorousMedium", 3);
    model.component("comp1").physics("fp").feature("porous1").selection().named("geom1_intsel4");
    model.component("comp1").physics("fp").feature("porous1").feature("pm1").set("epsilon_p_mat", "userdef");
    model.component("comp1").physics("fp").feature("porous1").feature("pm1").set("epsilon_p", "eps_gdl");
    model.component("comp1").physics("fp").feature("porous1").feature("pm1").set("kappa_mat", "userdef");
    model.component("comp1").physics("fp").feature("porous1").feature("pm1")
         .set("kappa", new String[]{"kappa_gdl", "0", "0", "0", "kappa_gdl", "0", "0", "0", "kappa_gdl"});
    model.component("comp1").physics("fp").create("inl1", "InletBoundary", 2);
    model.component("comp1").physics("fp").feature("inl1").selection().named("geom1_boxsel4");
    model.component("comp1").physics("fp").feature("inl1").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("fp").feature("inl1").set("multipleInlets", false);
    model.component("comp1").physics("fp").feature("inl1").set("Uavfdf", "U_in");
    model.component("comp1").physics("fp").create("out1", "OutletBoundary", 2);
    model.component("comp1").physics("fp").feature("out1").selection().named("geom1_boxsel5");
    model.component("comp1").physics("fp").create("wallbc2", "WallBC", 2);
    model.component("comp1").physics("fp").feature("wallbc2").selection().named("geom1_difsel3");
    model.component("comp1").physics("fp").feature("wallbc2").set("BoundaryCondition", "Slip");

    model.common("cminpt").set("modified", new String[][]{{"temperature", "T"}});

    model.component("comp1").mesh("mesh1").autoMeshSize(3);
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().named("geom1_intsel5");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().all();
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 4);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().named("geom1_boxsel1");
    model.component("comp1").mesh("mesh1").feature("swe1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("hmax", "W_ch/1.1");
    model.component("comp1").mesh("mesh1").run("swe1");
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().named("geom1_difsel1");
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmax", "W_ch/2.1");
    model.component("comp1").mesh("mesh1").run("ftet1");
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom(3);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().allGeom();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().named("geom1_ext1_dom");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().named("geom1_difsel4");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 2);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blstretch", 1.5);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("inittype", "blhmin");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blhmin", "W_ch/10");
    model.component("comp1").mesh("mesh1").run("bl1");
    model.component("comp1").mesh("mesh1").create("map2", "Map");
    model.component("comp1").mesh("mesh1").feature("map2").selection().named("geom1_intsel6");
    model.component("comp1").mesh("mesh1").run("map2");
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().named("geom1_difsel6");
    model.component("comp1").mesh("mesh1").run("ftri1");
    model.component("comp1").mesh("mesh1").create("bl2", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl2").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl2").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("bl2").selection().named("geom1_difsel2");
    model.component("comp1").mesh("mesh1").feature("bl2").feature("blp").selection().named("geom1_ext1_edg");
    model.component("comp1").mesh("mesh1").feature("bl2").feature("blp").set("blnlayers", 2);
    model.component("comp1").mesh("mesh1").feature("bl2").feature("blp").set("inittype", "blhmin");
    model.component("comp1").mesh("mesh1").feature("bl2").feature("blp").set("blhmin", "W_rib/10");
    model.component("comp1").mesh("mesh1").create("ftri2", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri2").selection().named("geom1_boxsel9");
    model.component("comp1").mesh("mesh1").feature("ftri2").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size1").set("hmax", "W_rib/1.2");
    model.component("comp1").mesh("mesh1").run("ftri2");
    model.component("comp1").mesh("mesh1").create("swe2", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe2").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis1").selection().named("geom1_intsel4");
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis1").set("numelem", 8);
    model.component("comp1").mesh("mesh1").feature("swe2").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis2").selection().named("geom1_ext2_dom");
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis2").set("numelem", 1);
    model.component("comp1").mesh("mesh1").feature("swe2").create("dis3", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis3").selection().named("geom1_intsel3");
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis3").set("numelem", 3);
    model.component("comp1").mesh("mesh1").run("swe2");

    model.study("std1").feature("stat").setSolveFor("/physics/fc", false);
    model.study("std1").create("stat2", "Stationary");
    model.study("std1").feature("stat2").set("useparam", true);
    model.study("std1").feature("stat2").setIndex("pname", "alpha_a_H2", 0);
    model.study("std1").feature("stat2").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat2").setIndex("punit", "", 0);
    model.study("std1").feature("stat2").setIndex("pname", "alpha_a_H2", 0);
    model.study("std1").feature("stat2").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat2").setIndex("punit", "", 0);
    model.study("std1").feature("stat2").setIndex("pname", "E_cell", 0);
    model.study("std1").feature("stat2").setIndex("plistarr", "0.9 0.7 0.5", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 3, 0);
    model.result("pg1").label("\u5bf9\u5730\u7535\u6781\u7535\u4f4d (fc)");
    model.result("pg1").create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").set("expr", new String[]{"fc.phis"});
    model.result("pg1").create("arwv1", "ArrowVolume");
    model.result("pg1").feature("arwv1").set("expr", new String[]{"fc.Isx", "fc.Isy", "fc.Isz"});
    model.result("pg1").feature("arwv1").set("arrowbase", "center");
    model.result("pg1").feature("arwv1").set("color", "gray");
    model.result("pg1").feature("arwv1").create("filt1", "Filter");
    model.result("pg1").feature("arwv1").feature("filt1").set("expr", "isdefined(root.comp1.fc.phis)");
    model.result("pg1").feature("arwv1").feature("filt1").set("nodespec", "all");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 3, 0);
    model.result("pg2").label("\u7535\u89e3\u8d28\u7535\u4f4d (fc)");
    model.result("pg2").create("mslc1", "Multislice");
    model.result("pg2").feature("mslc1").set("expr", new String[]{"fc.phil"});
    model.result("pg2").create("arwv1", "ArrowVolume");
    model.result("pg2").feature("arwv1").set("expr", new String[]{"fc.Ilx", "fc.Ily", "fc.Ilz"});
    model.result("pg2").feature("arwv1").set("arrowbase", "center");
    model.result("pg2").feature("arwv1").set("color", "gray");
    model.result("pg2").feature("arwv1").create("filt1", "Filter");
    model.result("pg2").feature("arwv1").feature("filt1").set("expr", "isdefined(fc.phil)");
    model.result("pg2").feature("arwv1").feature("filt1").set("nodespec", "all");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").setIndex("looplevel", 3, 0);
    model.result("pg3").label("\u6469\u5c14\u5206\u6570, O2, \u6d41\u7ebf (fc)");
    model.result("pg3").set("titletype", "custom");
    model.result("pg3").set("typeintitle", true);
    model.result("pg3").set("prefixintitle", "\u7269\u8d28 O2:");
    model.result("pg3").create("str1", "Streamline");
    model.result("pg3").feature("str1").set("expr", new String[]{"fc.tfluxO2x", "fc.tfluxO2y", "fc.tfluxO2z"});
    model.result("pg3").feature("str1").set("posmethod", "start");
    model.result("pg3").feature("str1").set("pointtype", "arrow");
    model.result("pg3").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg3").feature("str1").set("color", "gray");
    model.result("pg3").feature("str1").create("col", "Color");
    model.result("pg3").feature("str1").feature("col").set("expr", "fc.xO2");
    model.result("pg3").feature("str1").feature("col").set("colortable", "Rainbow");
    model.result("pg3").feature("str1").feature("col").set("titletype", "custom");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").setIndex("looplevel", 3, 0);
    model.result("pg4").label("\u6469\u5c14\u5206\u6570, O2, \u8868\u9762 (fc)");
    model.result("pg4").set("titletype", "custom");
    model.result("pg4").set("prefixintitle", "\u7269\u8d28 O2:");
    model.result("pg4").set("expressionintitle", false);
    model.result("pg4").set("typeintitle", false);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"fc.xO2"});
    model.result("pg4").feature("surf1").set("colortable", "Rainbow");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "dset1");
    model.result("pg5").setIndex("looplevel", 3, 0);
    model.result("pg5").label("\u6469\u5c14\u5206\u6570, H2O, \u6d41\u7ebf (fc)");
    model.result("pg5").set("titletype", "custom");
    model.result("pg5").set("typeintitle", true);
    model.result("pg5").set("prefixintitle", "\u7269\u8d28 H2O:");
    model.result("pg5").create("str1", "Streamline");
    model.result("pg5").feature("str1").set("expr", new String[]{"fc.tfluxH2Ox", "fc.tfluxH2Oy", "fc.tfluxH2Oz"});
    model.result("pg5").feature("str1").set("posmethod", "start");
    model.result("pg5").feature("str1").set("pointtype", "arrow");
    model.result("pg5").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg5").feature("str1").set("color", "gray");
    model.result("pg5").feature("str1").create("col", "Color");
    model.result("pg5").feature("str1").feature("col").set("expr", "fc.xH2O");
    model.result("pg5").feature("str1").feature("col").set("colortable", "Rainbow");
    model.result("pg5").feature("str1").feature("col").set("titletype", "custom");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").set("data", "dset1");
    model.result("pg6").setIndex("looplevel", 3, 0);
    model.result("pg6").label("\u6469\u5c14\u5206\u6570, H2O, \u8868\u9762 (fc)");
    model.result("pg6").set("titletype", "custom");
    model.result("pg6").set("prefixintitle", "\u7269\u8d28 H2O:");
    model.result("pg6").set("expressionintitle", false);
    model.result("pg6").set("typeintitle", false);
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", new String[]{"fc.xH2O"});
    model.result("pg6").feature("surf1").set("colortable", "Rainbow");
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").set("data", "dset1");
    model.result("pg7").setIndex("looplevel", 3, 0);
    model.result("pg7").label("\u6469\u5c14\u5206\u6570, N2, \u6d41\u7ebf (fc)");
    model.result("pg7").set("titletype", "custom");
    model.result("pg7").set("typeintitle", true);
    model.result("pg7").set("prefixintitle", "\u7269\u8d28 N2:");
    model.result("pg7").create("str1", "Streamline");
    model.result("pg7").feature("str1").set("expr", new String[]{"fc.tfluxN2x", "fc.tfluxN2y", "fc.tfluxN2z"});
    model.result("pg7").feature("str1").set("posmethod", "start");
    model.result("pg7").feature("str1").set("pointtype", "arrow");
    model.result("pg7").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg7").feature("str1").set("color", "gray");
    model.result("pg7").feature("str1").create("col", "Color");
    model.result("pg7").feature("str1").feature("col").set("expr", "fc.xN2");
    model.result("pg7").feature("str1").feature("col").set("colortable", "Rainbow");
    model.result("pg7").feature("str1").feature("col").set("titletype", "custom");
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").set("data", "dset1");
    model.result("pg8").setIndex("looplevel", 3, 0);
    model.result("pg8").label("\u6469\u5c14\u5206\u6570, N2, \u8868\u9762 (fc)");
    model.result("pg8").set("titletype", "custom");
    model.result("pg8").set("prefixintitle", "\u7269\u8d28 N2:");
    model.result("pg8").set("expressionintitle", false);
    model.result("pg8").set("typeintitle", false);
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("expr", new String[]{"fc.xN2"});
    model.result("pg8").feature("surf1").set("colortable", "Rainbow");
    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg9", "PlotGroup3D");
    model.result("pg9").label("\u901f\u5ea6 (fp)");
    model.result("pg9").set("frametype", "spatial");
    model.result("pg9").feature().create("slc1", "Slice");
    model.result("pg9").feature("slc1").label("\u5207\u9762");
    model.result("pg9").feature("slc1").set("showsolutionparams", "on");
    model.result("pg9").feature("slc1").set("expr", "fp.U");
    model.result("pg9").feature("slc1").set("smooth", "internal");
    model.result("pg9").feature("slc1").set("showsolutionparams", "on");
    model.result("pg9").feature("slc1").set("data", "parent");
    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").label("\u5916\u58c1");
    model.result().dataset("surf1").set("data", "dset1");
    model.result().dataset("surf1").selection().geom("geom1", 2);
    model.result().dataset("surf1").selection()
         .set(33, 34, 35, 36, 38, 40, 41, 42, 44, 46, 47, 48, 50, 52, 53, 54, 55, 56, 58, 59, 60, 61, 62, 65, 66, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 85, 86, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 105, 106, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 124, 125, 128, 129, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 144, 145, 148, 149, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 164, 165, 168, 169, 171, 172, 173, 174, 175, 176, 177, 178, 180);
    model.result().create("pg10", "PlotGroup3D");
    model.result("pg10").label("\u538b\u529b (fp)");
    model.result("pg10").set("data", "surf1");
    model.result("pg10").setIndex("looplevel", 3, 0);
    model.result("pg10").set("frametype", "spatial");
    model.result("pg10").feature().create("surf1", "Surface");
    model.result("pg10").feature("surf1").label("\u8868\u9762");
    model.result("pg10").feature("surf1").set("showsolutionparams", "on");
    model.result("pg10").feature("surf1").set("expr", "p");
    model.result("pg10").feature("surf1").set("colortable", "Dipole");
    model.result("pg10").feature("surf1").set("smooth", "internal");
    model.result("pg10").feature("surf1").set("showsolutionparams", "on");
    model.result("pg10").feature("surf1").set("data", "parent");
    model.result("pg10").feature("surf1").feature().create("tran1", "Transparency");
    model.result("pg1").run();
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 2, 0);
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 3, 0);
    model.result("pg4").run();
    model.result("pg9").run();
    model.result("pg9").label("\u901a\u9053\u901f\u5ea6");
    model.result("pg9").run();
    model.result("pg9").feature("slc1").set("quickplane", "xy");
    model.result("pg9").feature("slc1").set("quickzmethod", "coord");
    model.result("pg9").feature("slc1").set("quickz", "H_mem/2+H_gdl+H_ch/2");
    model.result("pg9").run();
    model.result("pg9").run();
    model.result().duplicate("pg11", "pg9");
    model.result("pg11").run();
    model.result("pg11").label("GDL \u901f\u5ea6");
    model.result("pg11").run();
    model.result("pg11").feature("slc1").set("quickz", "H_mem/2+H_gdl/2");
    model.result("pg11").run();
    model.result().create("pg12", "PlotGroup3D");
    model.result("pg12").run();
    model.result("pg12").label("O2 GDE \u7535\u6d41\u5bc6\u5ea6");
    model.result("pg12").selection().geom("geom1", 2);
    model.result("pg12").selection().named("geom1_boxsel7");
    model.result("pg12").create("surf1", "Surface");
    model.result("pg12").feature("surf1").set("expr", "fc.nIl");
    model.result("pg12").feature("surf1").set("descr", "\u6cd5\u5411\u7535\u89e3\u8d28\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg12").feature("surf1").set("unit", "A/cm^2");
    model.result("pg12").run();
    model.result("pg12").setIndex("looplevel", 2, 0);
    model.result("pg12").set("edges", false);
    model.result("pg12").run();
    model.result("pg12").setIndex("looplevel", 3, 0);
    model.result("pg12").run();

    model
         .title("\u5177\u6709\u86c7\u5f62\u6d41\u573a\u7684\u9ad8\u6e29\u8d28\u5b50\u4ea4\u6362\u819c\u71c3\u6599\u7535\u6c60");

    model
         .description("\u672c\u4f8b\u6a21\u62df\u805a\u5408\u7269\u7535\u89e3\u8d28\u71c3\u6599\u7535\u6c60\u6c27\u4fa7\u7684\u901a\u9053\u548c\u6c14\u4f53\u6269\u6563\u5c42 (GDL) \u4e2d\u7684\u6d41\u52a8\u548c\u8d28\u91cf\u4f20\u9012\u3002\n\n\u6c27\u6c14\u6269\u6563\u7535\u6781 (GDE) \u7684\u9634\u6781\u7535\u6781\u53cd\u5e94\u4f5c\u4e3a\u5185\u90e8\u8fb9\u754c\u6761\u4ef6\u8fdb\u884c\u5efa\u6a21\uff0c\u5176\u4e2d\u7684\u5c40\u90e8\u7535\u6d41\u5bc6\u5ea6\u53d6\u51b3\u4e8e\u8fc7\u7535\u4f4d\u548c\u5c40\u90e8\u6c27\u6d53\u5ea6\u3002\n\n\u6b64\u5916\uff0c\u8be5\u6a21\u578b\u8fd8\u5305\u542b\u819c\u3001\u9633\u6781 GDE \u548c\u9633\u6781 GDL \u7535\u538b\u635f\u5931\u3002\n\n\u5176\u4e2d\u5047\u8bbe\u9633\u6781\u4fa7\u7684\u6c14\u6d41\u4e3a 100% \u7684\u6c22\u6c14\uff0c\u56e0\u6b64\u65e0\u9700\u5728\u7535\u6c60\u8fd9\u4e00\u4fa7\u5bf9\u8d28\u91cf\u548c\u52a8\u91cf\u4f20\u9012\u8fdb\u884c\u5efa\u6a21\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("ht_pem_flow_field.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
