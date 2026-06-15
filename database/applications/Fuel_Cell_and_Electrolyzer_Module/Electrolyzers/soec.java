/*
 * soec.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:06 by COMSOL 6.3.0.290. */
public class soec {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Fuel_Cell_and_Electrolyzer_Module\\Electrolyzers");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("we", "WaterElectrolyzer", "geom1");
    model.component("comp1").physics("we").prop("H2GasMixture").set("H2O", "1");
    model.component("comp1").physics("we").prop("H2GasMixture").set("GasPhaseDiffusion", "1");
    model.component("comp1").physics("we").prop("DefaultElectrodeReactionSettings")
         .set("ChargeCarryingIon", "Oxide");
    model.component("comp1").physics("we").prop("DefaultElectrodeReactionSettings")
         .set("OperationMode", "Electrolyzer");
    model.component("comp1").physics("we").prop("DefaultElectrodeReactionSettings").set("TRHE", "700[degC]");

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
    model.study("std1").feature("cdi").setSolveFor("/physics/we", true);
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").set("ftplistmethod", "manual");
    model.study("std1").feature("stat").set("solnum", "auto");
    model.study("std1").feature("stat").set("notsolnum", "auto");
    model.study("std1").feature("stat").set("outputmap", new String[]{});
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").setSolveFor("/physics/we", true);

    model.component("comp1").geom("geom1").insertFile("soec_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("difsel1");

    model.param().label("\u51e0\u4f55\u53c2\u6570");
    model.param().create("par2");
    model.param("par2").label("\u7269\u7406\u573a\u53c2\u6570");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("kappa", "1e-10[m^2]", "\u7535\u6781\u6e17\u900f\u7387");
    model.param("par2").set("por", "0.4", "\u6c14\u4f53\u5b54\u9699\u4f53\u79ef\u5206\u6570");
    model.param("par2").set("por_l", "0.4", "\u7535\u89e3\u8d28\u4f53\u79ef\u5206\u6570");
    model.param("par2").set("I_avg", "10^4[A/m^2]", "\u5e73\u5747\u7535\u6d41\u5bc6\u5ea6");
    model.param("par2").set("stoich", "1.2", "\u5165\u53e3\u84b8\u6c7d\u5316\u5b66\u8ba1\u91cf");
    model.param("par2").set("A_cell", "W_cell*D_cell", "\u7535\u89e3\u69fd\u9762\u79ef");
    model.param("par2")
         .set("Mflux_in", "stoich*18[g/mol]*I_avg*A_cell/(2*F_const)", "\u603b\u5165\u53e3\uff08\u84b8\u6c7d\uff09\u8d28\u91cf\u901a\u91cf");
    model.param("par2").set("T", "800[degC]", "\u7535\u89e3\u69fd\u6e29\u5ea6");
    model.param("par2").set("sigmaeff_s", "465[S/m]", "\u7535\u6781\u6709\u6548\u7535\u5bfc\u7387");
    model.param("par2")
         .set("i0_H2", "0.1[A/m^2]", "\u53c2\u8003\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u6c22\u53cd\u5e94");
    model.param("par2")
         .set("i0_O2", "0.1[A/m^2]", "\u53c2\u8003\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u6c27\u53cd\u5e94");
    model.param("par2").set("S", "1e9[m^2/m^3]", "\u7535\u6781\u6bd4\u8868\u9762\u79ef");
    model.param("par2")
         .set("Rc", "1e-7[ohm*m^2]", "\u7535\u6781\u4e0e\u96c6\u6d41\u4f53\u7684\u63a5\u89e6\u7535\u963b");
    model.param("par2")
         .set("d_pore", "1e-6[m]", "\u6c14\u4f53\u6269\u6563\u7535\u6781\u7684\u5b54\u9699\u76f4\u5f84");

    model.component("comp1").physics("we").selection().set(1, 2, 3);
    model.component("comp1").physics("we").prop("H2GasMixture").set("GasPhaseDiffusion", false);
    model.component("comp1").physics("we").create("mem1", "Membrane", 3);
    model.component("comp1").physics("we").feature("mem1").selection().set(2);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ElectrolyteConductivity", "ElectrolyteConductivity", "Electrolyte conductivity");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat1").label("Yttria-Stabilized Zirconia, 8YSZ, (ZrO2)0.92-(Y2O3)0.08");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity")
         .label("Electrolyte conductivity");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("funcname", "log_sigmaT_vs_invT");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("table", new String[][]{{"0.8202666666666667", "2.2431906614786"}, 
         {"0.8526222222222222", "2.0972762645914402"}, 
         {"0.8912", "1.922178988326849"}, 
         {"0.9335111111111112", "1.7373540856031133"}, 
         {"0.9820444444444445", "1.5233463035019446"}, 
         {"1.0268444444444444", "1.319066147859922"}, 
         {"1.0766222222222224", "1.0856031128404666"}, 
         {"1.1463111111111113", "0.745136186770428"}, 
         {"1.2160000000000002", "0.3754863813229572"}, 
         {"1.296888888888889", "-0.03307392996108938"}, 
         {"1.384", "-0.5097276264591439"}, 
         {"1.4860444444444445", "-1.073929961089494"}, 
         {"1.6042666666666667", "-1.735408560311284"}, 
         {"1.7424000000000002", "-2.5136186770428006"}});
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("filecolumns", 2);
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("columnKeys", new String[]{"col1", "col2"});
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("argunit", new String[]{"1/K"});
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity")
         .set("sigmal", new String[]{"(10^(log_sigmaT_vs_invT(1000/T_reg))[S/cm*K])/T_reg", "0", "0", "0", "(10^(log_sigmaT_vs_invT(1000/T_reg))[S/cm*K])/T_reg", "0", "0", "0", "(10^(log_sigmaT_vs_invT(1000/T_reg))[S/cm*K])/T_reg"});
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity")
         .setPropertyInfo("sigmal", "Electrolytes for solid oxide fuel cells, J. Fergus, Journal of Power Sources 162 (2006) 30\u201340.\n\nConductivity values averaged from Figure 2.\n");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity")
         .set("T_reg", "max(min(T,1/0.8203e-3),1/1.74e-3)");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity")
         .descr("T_reg", "Temperature (regularized to valid range)");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").addInput("temperature");

    model.component("comp1").physics("we").create("h2gde1", "H2GasDiffusionElectrode", 3);
    model.component("comp1").physics("we").feature("h2gde1").selection().named("geom1_blk3_dom");
    model.component("comp1").physics("we").feature("h2gde1")
         .set("sigmas", new String[]{"sigmaeff_s", "0", "0", "0", "sigmaeff_s", "0", "0", "0", "sigmaeff_s"});
    model.component("comp1").physics("we").feature("h2gde1").set("epsl", "por_l");
    model.component("comp1").physics("we").feature("h2gde1").feature("h2gder1").set("i0_ref", "i0_H2");
    model.component("comp1").physics("we").feature("h2gde1").feature("h2gder1").set("Av", "S");
    model.component("comp1").physics("we").create("o2gde1", "O2GasDiffusionElectrode", 3);
    model.component("comp1").physics("we").feature("o2gde1").selection().named("geom1_blk1_dom");
    model.component("comp1").physics("we").feature("o2gde1")
         .set("sigmas", new String[]{"sigmaeff_s", "0", "0", "0", "sigmaeff_s", "0", "0", "0", "sigmaeff_s"});
    model.component("comp1").physics("we").feature("o2gde1").set("epsl", "por_l");
    model.component("comp1").physics("we").feature("o2gde1").feature("o2gder1").set("i0_ref", "i0_O2");
    model.component("comp1").physics("we").feature("o2gde1").feature("o2gder1").set("Av", "S");
    model.component("comp1").physics("we").feature("ecph1").create("egnd1", "ElectricGround", 2);
    model.component("comp1").physics("we").feature("ecph1").feature("egnd1").selection().named("geom1_sel3");
    model.component("comp1").physics("we").feature("ecph1").feature("egnd1").set("IncludeContactResistance", true);
    model.component("comp1").physics("we").feature("ecph1").feature("egnd1").set("Rc", "Rc");
    model.component("comp1").physics("we").feature("ecph1").create("ec1", "ElectrodeCurrent", 2);
    model.component("comp1").physics("we").feature("ecph1").feature("ec1")
         .set("ElectronicCurrentType", "AverageCurrentDensity");
    model.component("comp1").physics("we").feature("ecph1").feature("ec1").selection().named("geom1_sel4");
    model.component("comp1").physics("we").feature("ecph1").feature("ec1").set("Ias", "I_avg");
    model.component("comp1").physics("we").feature("ecph1").feature("ec1").set("IncludeContactResistance", true);
    model.component("comp1").physics("we").feature("ecph1").feature("ec1").set("Rc", "Rc");

    model.common("cminpt").set("modified", new String[][]{{"temperature", "T"}});

    model.component("comp1").mesh("mesh1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmax", "H_ch*0.8");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().set(5, 6, 7);
    model.component("comp1").mesh("mesh1").run("swe1");
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().set(4, 8);
    model.component("comp1").mesh("mesh1").run("ftet1");
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom(3);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().allGeom();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().named("geom1_ext1_dom");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().named("geom1_difsel1");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 2);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blstretch", 1.3);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("inittype", "blhmin");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blhmin", "H_ch/10");
    model.component("comp1").mesh("mesh1").run("bl1");
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().named("geom1_sel3");
    model.component("comp1").mesh("mesh1").run("ftri1");
    model.component("comp1").mesh("mesh1").create("swe2", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe2").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis1").selection().set(3);
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis1").set("elemratio", 2);
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis1").set("reverse", true);
    model.component("comp1").mesh("mesh1").feature("swe2").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis2").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis2").set("numelem", 2);
    model.component("comp1").mesh("mesh1").feature("swe2").feature().duplicate("dis3", "dis1");
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis3").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis3").set("reverse", false);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").label("\u5bf9\u5730\u7535\u6781\u7535\u4f4d (we)");
    model.result("pg1").create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").set("expr", new String[]{"we.phis"});
    model.result("pg1").create("arwv1", "ArrowVolume");
    model.result("pg1").feature("arwv1").set("expr", new String[]{"we.Isx", "we.Isy", "we.Isz"});
    model.result("pg1").feature("arwv1").set("arrowbase", "center");
    model.result("pg1").feature("arwv1").set("color", "gray");
    model.result("pg1").feature("arwv1").create("filt1", "Filter");
    model.result("pg1").feature("arwv1").feature("filt1").set("expr", "isdefined(root.comp1.we.phis)");
    model.result("pg1").feature("arwv1").feature("filt1").set("nodespec", "all");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").label("\u7535\u89e3\u8d28\u7535\u4f4d (we)");
    model.result("pg2").create("mslc1", "Multislice");
    model.result("pg2").feature("mslc1").set("expr", new String[]{"we.phil"});
    model.result("pg2").create("arwv1", "ArrowVolume");
    model.result("pg2").feature("arwv1").set("expr", new String[]{"we.Ilx", "we.Ily", "we.Ilz"});
    model.result("pg2").feature("arwv1").set("arrowbase", "center");
    model.result("pg2").feature("arwv1").set("color", "gray");
    model.result("pg2").feature("arwv1").create("filt1", "Filter");
    model.result("pg2").feature("arwv1").feature("filt1").set("expr", "isdefined(we.phil)");
    model.result("pg2").feature("arwv1").feature("filt1").set("nodespec", "all");
    model.result("pg1").run();
    model.result("pg1").run();

    model.component("comp1").physics().create("fp", "FreeAndPorousMediaFlow", "geom1");

    model.study("std1").feature("cdi").setSolveFor("/physics/fp", true);
    model.study("std1").feature("stat").setSolveFor("/physics/fp", true);

    model.component("comp1").physics("fp").selection().set(3, 4, 5, 6, 7, 8);

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(3);
    model.component("comp1").selection("sel1").label("\u6c14\u4f53\u57df");
    model.component("comp1").selection("sel1").set(3, 4, 5, 6, 7, 8);

    model.component("comp1").physics("fp").selection().named("sel1");
    model.component("comp1").physics("fp").prop("PhysicalModelProperty")
         .set("Compressibility", "CompressibleMALT03");
    model.component("comp1").physics("fp").create("porous1", "PorousMedium", 3);
    model.component("comp1").physics("fp").feature("porous1").selection().named("geom1_blk3_dom");
    model.component("comp1").physics("fp").feature("porous1").feature("pm1").set("epsilon_p_mat", "userdef");
    model.component("comp1").physics("fp").feature("porous1").feature("pm1").set("epsilon_p", "por");
    model.component("comp1").physics("fp").feature("porous1").feature("pm1").set("kappa_mat", "userdef");
    model.component("comp1").physics("fp").feature("porous1").feature("pm1")
         .set("kappa", new String[]{"kappa", "0", "0", "0", "kappa", "0", "0", "0", "kappa"});
    model.component("comp1").physics("fp").create("inl1", "InletBoundary", 2);
    model.component("comp1").physics("fp").feature("inl1").selection().named("geom1_sel1");
    model.component("comp1").physics("fp").feature("inl1").set("BoundaryCondition", "MassFlow");
    model.component("comp1").physics("fp").feature("inl1").set("mfr", "Mflux_in");
    model.component("comp1").physics("fp").create("out1", "OutletBoundary", 2);
    model.component("comp1").physics("fp").feature("out1").selection().named("geom1_sel2");
    model.component("comp1").physics("fp").feature("out1").set("NormalFlow", true);

    model.component("comp1").multiphysics().create("rfh1", "ReactingFlowH2GasPhase", 3);

    model.component("comp1").physics("we").selection().all();
    model.component("comp1").physics("we").prop("H2GasMixture").set("GasPhaseDiffusion", true);
    model.component("comp1").physics("we").create("h2fch1", "H2FlowChannel", 3);
    model.component("comp1").physics("we").feature("h2fch1").selection().named("geom1_ext1_dom");
    model.component("comp1").physics("we").feature("h2gde1").set("epsg", "por");
    model.component("comp1").physics("we").feature("h2gde1").set("IncludePoreWallInteractions", true);
    model.component("comp1").physics("we").feature("h2gde1").set("d_pore", "d_pore");
    model.component("comp1").physics("we").feature("h2gasph1").create("h2in1", "H2Inlet", 2);
    model.component("comp1").physics("we").feature("h2gasph1").feature("h2in1").selection().named("geom1_sel1");
    model.component("comp1").physics("we").feature("h2gasph1").feature("h2in1")
         .set("MixtureSpecification", "MassFlowRates");
    model.component("comp1").physics("we").feature("h2gasph1").feature("h2in1").set("J0H2O", "Mflux_in");
    model.component("comp1").physics("we").feature("h2gasph1").create("h2out1", "H2Outlet", 2);
    model.component("comp1").physics("we").feature("h2gasph1").feature("h2out1").selection().named("geom1_sel2");
    model.component("comp1").physics("we").feature("h2gasph1").feature("init1")
         .set("x0H2O", "0.95*(1-x/(W_cell*stoich))");

    model.study("std1").feature("cdi").setSolveFor("/multiphysics/rfh1", false);
    model.study("std1").feature("stat").setSolveFor("/physics/we", false);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/rfh1", false);
    model.study("std1").create("stat2", "Stationary");

    model.sol().remove("sol1");
    model.sol().remove("sol2");

    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s3").create("fc1", "FullyCoupled");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").label("\u5bf9\u5730\u7535\u6781\u7535\u4f4d (we)");
    model.result("pg1").create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").set("expr", new String[]{"we.phis"});
    model.result("pg1").create("arwv1", "ArrowVolume");
    model.result("pg1").feature("arwv1").set("expr", new String[]{"we.Isx", "we.Isy", "we.Isz"});
    model.result("pg1").feature("arwv1").set("arrowbase", "center");
    model.result("pg1").feature("arwv1").set("color", "gray");
    model.result("pg1").feature("arwv1").create("filt1", "Filter");
    model.result("pg1").feature("arwv1").feature("filt1").set("expr", "isdefined(root.comp1.we.phis)");
    model.result("pg1").feature("arwv1").feature("filt1").set("nodespec", "all");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").label("\u7535\u89e3\u8d28\u7535\u4f4d (we)");
    model.result("pg2").create("mslc1", "Multislice");
    model.result("pg2").feature("mslc1").set("expr", new String[]{"we.phil"});
    model.result("pg2").create("arwv1", "ArrowVolume");
    model.result("pg2").feature("arwv1").set("expr", new String[]{"we.Ilx", "we.Ily", "we.Ilz"});
    model.result("pg2").feature("arwv1").set("arrowbase", "center");
    model.result("pg2").feature("arwv1").set("color", "gray");
    model.result("pg2").feature("arwv1").create("filt1", "Filter");
    model.result("pg2").feature("arwv1").feature("filt1").set("expr", "isdefined(we.phil)");
    model.result("pg2").feature("arwv1").feature("filt1").set("nodespec", "all");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").label("\u6469\u5c14\u5206\u6570, H2, \u6d41\u7ebf (we)");
    model.result("pg3").set("titletype", "custom");
    model.result("pg3").set("typeintitle", true);
    model.result("pg3").set("prefixintitle", "\u7269\u8d28 H2:");
    model.result("pg3").create("str1", "Streamline");
    model.result("pg3").feature("str1").set("expr", new String[]{"we.tfluxH2x", "we.tfluxH2y", "we.tfluxH2z"});
    model.result("pg3").feature("str1").set("posmethod", "start");
    model.result("pg3").feature("str1").set("pointtype", "arrow");
    model.result("pg3").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg3").feature("str1").set("color", "gray");
    model.result("pg3").feature("str1").create("col", "Color");
    model.result("pg3").feature("str1").feature("col").set("expr", "we.xH2");
    model.result("pg3").feature("str1").feature("col").set("colortable", "Rainbow");
    model.result("pg3").feature("str1").feature("col").set("titletype", "custom");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").label("\u6469\u5c14\u5206\u6570, H2, \u8868\u9762 (we)");
    model.result("pg4").set("titletype", "custom");
    model.result("pg4").set("prefixintitle", "\u7269\u8d28 H2:");
    model.result("pg4").set("expressionintitle", false);
    model.result("pg4").set("typeintitle", false);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"we.xH2"});
    model.result("pg4").feature("surf1").set("colortable", "Rainbow");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "dset1");
    model.result("pg5").label("\u6469\u5c14\u5206\u6570, H2O, \u6d41\u7ebf (we)");
    model.result("pg5").set("titletype", "custom");
    model.result("pg5").set("typeintitle", true);
    model.result("pg5").set("prefixintitle", "\u7269\u8d28 H2O:");
    model.result("pg5").create("str1", "Streamline");
    model.result("pg5").feature("str1").set("expr", new String[]{"we.tfluxH2Ox", "we.tfluxH2Oy", "we.tfluxH2Oz"});
    model.result("pg5").feature("str1").set("posmethod", "start");
    model.result("pg5").feature("str1").set("pointtype", "arrow");
    model.result("pg5").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg5").feature("str1").set("color", "gray");
    model.result("pg5").feature("str1").create("col", "Color");
    model.result("pg5").feature("str1").feature("col").set("expr", "we.xH2O");
    model.result("pg5").feature("str1").feature("col").set("colortable", "Rainbow");
    model.result("pg5").feature("str1").feature("col").set("titletype", "custom");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").set("data", "dset1");
    model.result("pg6").label("\u6469\u5c14\u5206\u6570, H2O, \u8868\u9762 (we)");
    model.result("pg6").set("titletype", "custom");
    model.result("pg6").set("prefixintitle", "\u7269\u8d28 H2O:");
    model.result("pg6").set("expressionintitle", false);
    model.result("pg6").set("typeintitle", false);
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", new String[]{"we.xH2O"});
    model.result("pg6").feature("surf1").set("colortable", "Rainbow");
    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").label("\u901f\u5ea6 (fp)");
    model.result("pg7").set("frametype", "spatial");
    model.result("pg7").feature().create("slc1", "Slice");
    model.result("pg7").feature("slc1").label("\u5207\u9762");
    model.result("pg7").feature("slc1").set("showsolutionparams", "on");
    model.result("pg7").feature("slc1").set("expr", "fp.U");
    model.result("pg7").feature("slc1").set("smooth", "internal");
    model.result("pg7").feature("slc1").set("showsolutionparams", "on");
    model.result("pg7").feature("slc1").set("data", "parent");
    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").label("\u5916\u58c1");
    model.result().dataset("surf1").set("data", "dset1");
    model.result().dataset("surf1").selection().geom("geom1", 2);
    model.result().dataset("surf1").selection()
         .set(7, 8, 9, 10, 13, 14, 15, 17, 18, 21, 23, 24, 25, 26, 28, 30, 31, 32, 33, 35, 37, 38, 39, 40, 41, 43, 44, 47, 49, 51, 52, 55);
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").label("\u538b\u529b (fp)");
    model.result("pg8").set("data", "surf1");
    model.result("pg8").set("frametype", "spatial");
    model.result("pg8").feature().create("surf1", "Surface");
    model.result("pg8").feature("surf1").label("\u8868\u9762");
    model.result("pg8").feature("surf1").set("showsolutionparams", "on");
    model.result("pg8").feature("surf1").set("expr", "p");
    model.result("pg8").feature("surf1").set("colortable", "Dipole");
    model.result("pg8").feature("surf1").set("smooth", "internal");
    model.result("pg8").feature("surf1").set("showsolutionparams", "on");
    model.result("pg8").feature("surf1").set("data", "parent");
    model.result("pg8").feature("surf1").feature().create("tran1", "Transparency");
    model.result("pg1").run();
    model.result("pg7").run();
    model.result("pg7").feature("slc1").set("quickplane", "xy");
    model.result("pg7").feature("slc1").set("quickzmethod", "coord");
    model.result("pg7").feature("slc1").set("quickz", "H_cell-H_ch/2");
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").create("str1", "Streamline");
    model.result("pg7").feature("str1").set("expr", new String[]{"u", "v", "w"});
    model.result("pg7").feature("str1").set("descr", "\u901f\u5ea6\u573a");
    model.result("pg7").feature("str1").selection().named("geom1_sel1");
    model.result("pg7").feature("str1").set("pointtype", "arrow");
    model.result("pg7").feature("str1").set("arrowdistr", "equalinvtime");
    model.result("pg7").feature("str1").set("color", "black");
    model.result("pg7").run();
    model.result().create("pg9", "PlotGroup3D");
    model.result("pg9").run();
    model.result("pg9").label("\u5bc6\u5ea6");
    model.result("pg9").create("surf1", "Surface");
    model.result("pg9").feature("surf1").set("expr", "fp.rho");
    model.result("pg9").feature("surf1").set("descr", "\u5bc6\u5ea6");
    model.result("pg9").run();
    model.result().create("pg10", "PlotGroup3D");
    model.result("pg10").run();
    model.result("pg10").label("\u9ecf\u5ea6");
    model.result("pg10").create("surf1", "Surface");
    model.result("pg10").feature("surf1").set("expr", "fp.mu");
    model.result("pg10").feature("surf1").set("descr", "\u52a8\u529b\u9ecf\u5ea6");
    model.result("pg10").run();
    model.result("pg4").run();
    model.result("pg6").run();
    model.result().create("pg11", "PlotGroup3D");
    model.result("pg11").run();
    model.result("pg11").label("\u6469\u5c14\u5206\u6570\u548c\u901a\u91cf\uff0cH2");
    model.result("pg11").set("edges", false);
    model.result("pg11").create("str1", "Streamline");
    model.result("pg11").feature("str1").set("expr", new String[]{"we.tfluxH2x", "we.tfluxH2y", "we.tfluxH2z"});
    model.result("pg11").feature("str1").set("descr", "\u603b\u901a\u91cf");
    model.result("pg11").feature("str1").set("selnumber", 30);
    model.result("pg11").feature("str1").selection().named("geom1_sel2");
    model.result("pg11").feature("str1").set("pointtype", "arrow");
    model.result("pg11").feature("str1").set("arrowdistr", "equalinvtime");
    model.result("pg11").feature("str1").set("color", "black");
    model.result("pg11").feature("str1").create("sel1", "Selection");
    model.result("pg11").feature("str1").feature("sel1").selection().named("geom1_ext1_dom");
    model.result("pg11").run();
    model.result("pg11").create("vol1", "Volume");
    model.result("pg11").feature("vol1").set("expr", "we.xH2");
    model.result("pg11").feature("vol1").set("descr", "\u6469\u5c14\u5206\u6570");
    model.result("pg11").feature("vol1").create("sel1", "Selection");
    model.result("pg11").feature("vol1").feature("sel1").selection().named("geom1_blk3_dom");

    model.component("comp1").view("view1").set("showgrid", false);

    model.result("pg11").run();
    model.result().create("pg12", "PlotGroup3D");
    model.result("pg12").run();
    model.result("pg12").label("\u6a2a\u622a\u9762\u4e0a\u7684\u7535\u89e3\u8d28\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg12").set("edges", false);
    model.result("pg12").create("slc1", "Slice");
    model.result("pg12").feature("slc1").set("expr", "we.Ilz");
    model.result("pg12").feature("slc1")
         .set("descr", "\u7535\u89e3\u8d28\u7535\u6d41\u5bc6\u5ea6\u77e2\u91cf\uff0cz \u5206\u91cf");
    model.result("pg12").feature("slc1").set("quickplane", "xy");
    model.result("pg12").feature("slc1").set("quickzmethod", "coord");
    model.result("pg12").feature("slc1").set("quickz", "H_gde+H_el/2");
    model.result("pg12").run();

    model.title("\u56fa\u4f53\u6c27\u5316\u7269\u7535\u89e3\u69fd");

    model
         .description("\u672c\u4f8b\u5bf9\u56fa\u4f53\u6c27\u5316\u7269\u7535\u89e3\u6c60\u8fdb\u884c\u5efa\u6a21\uff0c\u5176\u4e2d\u6c34\u84b8\u6c14\u5728\u9634\u6781\u8fd8\u539f\u6210\u6c22\u6c14\uff0c\u9633\u6781\u4ea7\u751f\u4e86\u6c27\u6c14\u3002\u7535\u6c60\u4e2d\u7684\u7535\u6d41\u5206\u5e03\u4e0e\u9634\u6781\u4e0a\u6c22\u548c\u6c34\u7684\u8d28\u91cf\u4f20\u9012\u548c\u52a8\u91cf\u4f20\u9012\u76f8\u8026\u5408\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("soec.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
