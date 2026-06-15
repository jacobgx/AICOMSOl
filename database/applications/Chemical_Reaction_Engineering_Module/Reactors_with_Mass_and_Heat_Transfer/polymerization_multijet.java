/*
 * polymerization_multijet.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:23 by COMSOL 6.3.0.290. */
public class polymerization_multijet {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Chemical_Reaction_Engineering_Module\\Reactors_with_Mass_and_Heat_Transfer");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("chem", "Chemistry", "geom1");
    model.component("comp1").physics("chem").prop("mixture").set("ConcentrationType", "MassFraction");
    model.component("comp1").physics().create("tcs", "ConcentratedSpecies", "geom1");
    model.component("comp1").physics("tcs").prop("AdvancedSettings").set("UsePseudoTime", "1");
    model.component("comp1").physics("tcs").field("massfraction").field("wA");
    model.component("comp1").physics("tcs").field("massfraction")
         .component(new String[]{"wA", "wB", "wS", "wL", "wSC", "wAC"});
    model.component("comp1").physics().create("spf", "TurbulentFlowkeps", "geom1");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty")
         .set("Compressibility", "WeaklyCompressible");
    model.component("comp1").physics().create("ht", "HeatTransferInFluids", "geom1");
    model.component("comp1").physics("ht").prop("ShapeProperty").set("order_temperature", "1");

    model.component("comp1").multiphysics().create("nirf1", "NonIsothermalReactingFlow", 3);
    model.component("comp1").multiphysics("nirf1").set("Fluid_physics", "spf");
    model.component("comp1").multiphysics("nirf1").set("Species_physics", "tcs");
    model.component("comp1").multiphysics("nirf1").set("Chemistry_physics", "chem");
    model.component("comp1").multiphysics("nirf1").set("Heat_physics", "ht");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/chem", true);
    model.study("std1").feature("stat").setSolveFor("/physics/tcs", true);
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/nirf1", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("D", "1e-8[m^2/s]", "\u6269\u6563\u7cfb\u6570");
    model.param().set("rho_S", "2640[kg/m^3]", "\u50ac\u5316\u5242 (TiCl3) \u5bc6\u5ea6");
    model.param().set("Cp_S", "2550[J/kg/K]", "\u6bd4\u70ed\u5bb9\uff0c\u50ac\u5316\u5242");
    model.param().set("Uin", "5[m/s]", "\u5165\u53e3\u901f\u5ea6");
    model.param().set("tau", "0.4[m]/0.96[m/s]", "\u505c\u7559\u65f6\u95f4");
    model.param().set("T_in", "440[K]", "\u5165\u53e3\u6e29\u5ea6");
    model.param().set("MwA", "48[g/mol]", "\u6469\u5c14\u8d28\u91cf\uff0c\u5355\u4f53 A");
    model.param().set("MwB", "104[g/mol]", "\u6469\u5c14\u8d28\u91cf\uff0c\u5355\u4f53 B");
    model.param().set("MwC", "18[g/mol]", "\u6469\u5c14\u8d28\u91cf\uff0c\u8d77\u7edc\u5408\u4f5c\u7528\u7684 H2O");
    model.param().set("MwL", "164[g/mol]", "\u6469\u5c14\u8d28\u91cf\uff0c\u805a\u5408\u7269 L");
    model.param().set("MwS", "154[g/mol]", "\u6469\u5c14\u8d28\u91cf\uff0c\u50ac\u5316\u5242 S");
    model.param().set("MwSC", "172[g/mol]", "\u6469\u5c14\u8d28\u91cf\uff0c\u50ac\u5316\u590d\u5408\u7269\u8d28 SC");
    model.param().set("MwAC", "66[g/mol]", "\u6469\u5c14\u8d28\u91cf\uff0c\u590d\u5408\u7269\u8d28 AC");
    model.param().set("Af1", "25.6", "\u6b63\u53cd\u5e94\u9891\u7387\u56e0\u5b50\uff0c\u53cd\u5e94 1");
    model.param().set("Ef1", "61.3e3[J/mol]", "\u6b63\u53cd\u5e94\u6d3b\u5316\u80fd\uff0c\u53cd\u5e94 1");
    model.param().set("H1", "100e3[J/mol]", "\u53cd\u5e94\u70ed\uff0c\u53cd\u5e94 1");
    model.param().set("H2", "40e3[J/mol]", "\u53cd\u5e94\u70ed\uff0c\u53cd\u5e94 2");
    model.param().set("Af2", "3.9e3", "\u6b63\u53cd\u5e94\u9891\u7387\u56e0\u5b50\uff0c\u53cd\u5e94 2");
    model.param().set("Ef2", "56.8e3[J/mol]", "\u6b63\u53cd\u5e94\u6d3b\u5316\u80fd\uff0c\u53cd\u5e94 2");
    model.param().set("Ar2", "4.7e3", "\u9006\u53cd\u5e94\u9891\u7387\u56e0\u5b50\uff0c\u53cd\u5e94 2");
    model.param().set("Er2", "102e3[J/mol]", "\u9006\u53cd\u5e94\u6d3b\u5316\u80fd\uff0c\u53cd\u5e94 2");
    model.param().set("Cp_S_m", "Cp_S*MwS", "\u6469\u5c14\u6bd4\u70ed\u5bb9\uff0c\u50ac\u5316\u5242");
    model.param().set("F_mass", "19.8[kg/s]", "\u603b\u8d28\u91cf\u6d41");
    model.param().set("F_vol", "F_mass/rho_S", "\u603b\u4f53\u79ef\u6d41\u91cf");
    model.param().set("C_A_axial", "1200[mol/m^3]", "\u8f74\u5411\u5165\u53e3\u5904 A \u7684\u6d53\u5ea6");
    model.param().set("C_B_radial", "1000[mol/m^3]", "\u5f84\u5411\u5165\u53e3\u5904 B \u7684\u6d53\u5ea6");
    model.param().set("F_A_feed", "C_A_axial*F_vol/2", "A \u7684\u6469\u5c14\u8fdb\u7ed9\u7387");
    model.param().set("F_B_feed", "C_B_radial*F_vol/2", "B \u7684\u6469\u5c14\u8fdb\u7ed9\u7387");
    model.param().set("r_r", "0.05[m]", "\u53cd\u5e94\u5668\u534a\u5f84");
    model.param().set("L_r", "0.3[m]", "\u53cd\u5e94\u5668\u957f\u5ea6");
    model.param().set("V_r", "pi*r_r^2*L_r", "\u53cd\u5e94\u5668\u4f53\u79ef");

    model.component("comp1").geom("geom1").insertFile("polymerization_multijet_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("mcd1", "MeshControlDomains");
    model.component("comp1").geom("geom1").feature("mcd1").selection("input").set("fin", 4);
    model.component("comp1").geom("geom1").run("mcd1");
    model.component("comp1").geom("geom1").create("mcf1", "MeshControlFaces");
    model.component("comp1").geom("geom1").feature("mcf1").selection("input").set("mcd1", 11);
    model.component("comp1").geom("geom1").runPre("mcd1");
    model.component("comp1").geom("geom1").run("mcf1");

    model.component("comp1").physics("spf").feature("init1").set("k_init", "7e-8");
    model.component("comp1").physics("spf").feature("init1").set("ep_init", "1e-11");
    model.component("comp1").physics("spf").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("spf").feature("sym1").selection().set(4, 8);
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 2);
    model.component("comp1").physics("spf").feature("inl1").selection().set(1, 5);
    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("spf").feature("inl1").set("Uavfdf", 5);
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 2);
    model.component("comp1").physics("spf").feature("out1").selection().set(11);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter").set("MassTransfer", "tcs");
    model.component("comp1").physics("chem").create("rch1", "ReactionChem", 3);
    model.component("comp1").physics("chem").feature("rch1").set("formula", "2cA + cB + cS => cL + 2cSC");
    model.component("comp1").physics("chem").feature("rch1").set("useArrhenius", true);
    model.component("comp1").physics("chem").feature("rch1").set("Af", 25.6);
    model.component("comp1").physics("chem").feature("rch1").set("Ef", "61.3e3");
    model.component("comp1").physics("chem").feature("rch1").set("TurbulentReactionModel", "EddyDissipation");
    model.component("comp1").physics("chem").create("rch2", "ReactionChem", 3);
    model.component("comp1").physics("chem").feature("rch2").set("formula", "cA + cSC <=> cS + cAC");
    model.component("comp1").physics("chem").feature("rch2").set("useArrhenius", true);
    model.component("comp1").physics("chem").feature("rch2").set("Af", "3.9e3");
    model.component("comp1").physics("chem").feature("rch2").set("Ef", "56.8e3");
    model.component("comp1").physics("chem").feature("rch2").set("Ar", "4.7e3");
    model.component("comp1").physics("chem").feature("rch2").set("Er", "102e3");
    model.component("comp1").physics("chem").feature("rch2").set("TurbulentReactionModel", "EddyDissipation");
    model.component("comp1").physics("chem").feature("cA").set("M", "MwA");
    model.component("comp1").physics("chem").feature("cA").set("speciesEnthalpy", "UserDefined");
    model.component("comp1").physics("chem").feature("cB").set("M", "MwB");
    model.component("comp1").physics("chem").feature("cB").set("speciesEnthalpy", "UserDefined");
    model.component("comp1").physics("chem").feature("cS").set("M", "MwS");
    model.component("comp1").physics("chem").feature("cS").set("speciesEnthalpy", "UserDefined");
    model.component("comp1").physics("chem").feature("cL").set("M", "MwL");
    model.component("comp1").physics("chem").feature("cL").set("speciesEnthalpy", "UserDefined");
    model.component("comp1").physics("chem").feature("cSC").set("M", "MwSC");
    model.component("comp1").physics("chem").feature("cSC").set("speciesEnthalpy", "UserDefined");
    model.component("comp1").physics("chem").feature("cAC").set("M", "MwAC");
    model.component("comp1").physics("chem").feature("cAC").set("speciesEnthalpy", "UserDefined");
    model.component("comp1").physics("chem").prop("mixture").set("mixture", "liquid");
    model.component("comp1").physics("chem").prop("mixture").set("liquidDensitySel", "UserDefined");
    model.component("comp1").physics("chem").prop("mixture").set("rhoLiquid", "rho_S");
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationInput", "wA", 0, 0);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationInput", "wAC", 1, 0);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationInput", "wB", 2, 0);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationInput", "wL", 3, 0);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationInput", "wS", 4, 0);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationInput", "wSC", 5, 0);
    model.component("comp1").physics("chem").prop("calcTransport").set("heatCapacitySel", "UserDefined");
    model.component("comp1").physics("chem").prop("calcTransport").set("Cptot", "Cp_S");
    model.component("comp1").physics("chem").prop("calcTransport").set("thermalConductivitySel", "UserDefined");
    model.component("comp1").physics("chem").prop("calcTransport").set("k", "0.21+Cp_S*spf.muT/0.72");
    model.component("comp1").physics("chem").prop("calcTransport")
         .set("eta", "0.001*(1.17817558982837+(-298[K]+T)/223[K])^(-3.758)[Pa*s]");
    model.component("comp1").physics("tcs").prop("TransportMechanism").set("DiffusionModel", "FicksLaw");
    model.component("comp1").physics("tcs").prop("SpeciesProperties").set("FromMassConstraint", 3);
    model.component("comp1").physics("tcs").feature("cdm1").set("rho_src", "userdef");
    model.component("comp1").physics("tcs").feature("cdm1").set("rho", "rho_S");
    model.component("comp1").physics("tcs").feature("cdm1")
         .setIndex("Df", new String[]{"D", "0", "0", "0", "D", "0", "0", "0", "D"}, 0);
    model.component("comp1").physics("tcs").feature("cdm1")
         .setIndex("Df", new String[]{"D", "0", "0", "0", "D", "0", "0", "0", "D"}, 1);
    model.component("comp1").physics("tcs").feature("cdm1")
         .setIndex("Df", new String[]{"D", "0", "0", "0", "D", "0", "0", "0", "D"}, 2);
    model.component("comp1").physics("tcs").feature("cdm1")
         .setIndex("Df", new String[]{"D", "0", "0", "0", "D", "0", "0", "0", "D"}, 3);
    model.component("comp1").physics("tcs").feature("cdm1")
         .setIndex("Df", new String[]{"D", "0", "0", "0", "D", "0", "0", "0", "D"}, 4);
    model.component("comp1").physics("tcs").feature("cdm1")
         .setIndex("Df", new String[]{"D", "0", "0", "0", "D", "0", "0", "0", "D"}, 5);
    model.component("comp1").physics("tcs").feature("init1").setIndex("w0", "1e-6", 0);
    model.component("comp1").physics("tcs").feature("init1").setIndex("w0", "1e-6", 1);
    model.component("comp1").physics("tcs").feature("init1").setIndex("w0", "1e-6", 3);
    model.component("comp1").physics("tcs").feature("init1").setIndex("w0", "1e-6", 4);
    model.component("comp1").physics("tcs").feature("init1").setIndex("w0", "1e-6", 5);
    model.component("comp1").physics("tcs").create("in1", "Inflow", 2);
    model.component("comp1").physics("tcs").feature("in1").selection().set(1);
    model.component("comp1").physics("tcs").feature("in1").set("MixtureSpecification", "MolarConcentrations");
    model.component("comp1").physics("tcs").feature("in1").setIndex("c0", "1200[mol/m^3]", 0);
    model.component("comp1").physics("tcs").feature("in1").setIndex("c0", "1e-3[mol/m^3]", 1);
    model.component("comp1").physics("tcs").feature("in1").setIndex("c0", "1e-3[mol/m^3]", 3);
    model.component("comp1").physics("tcs").feature("in1").setIndex("c0", "1e-3[mol/m^3]", 4);
    model.component("comp1").physics("tcs").feature("in1").setIndex("c0", "1e-3[mol/m^3]", 5);
    model.component("comp1").physics("tcs").create("in2", "Inflow", 2);
    model.component("comp1").physics("tcs").feature("in2").selection().set(5);
    model.component("comp1").physics("tcs").feature("in2").set("MixtureSpecification", "MolarConcentrations");
    model.component("comp1").physics("tcs").feature("in2").setIndex("c0", "1e-3[mol/m^3]", 0);
    model.component("comp1").physics("tcs").feature("in2").setIndex("c0", "1000[mol/m^3]", 1);
    model.component("comp1").physics("tcs").feature("in2").setIndex("c0", "1e-3[mol/m^3]", 3);
    model.component("comp1").physics("tcs").feature("in2").setIndex("c0", "1e-3[mol/m^3]", 4);
    model.component("comp1").physics("tcs").feature("in2").setIndex("c0", "1e-3[mol/m^3]", 5);
    model.component("comp1").physics("tcs").create("out1", "Outflow", 2);
    model.component("comp1").physics("tcs").feature("out1").selection().set(11);
    model.component("comp1").physics("tcs").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("tcs").feature("sym1").selection().set(4, 8);
    model.component("comp1").physics("tcs").create("reac1", "ReactionSources", 3);
    model.component("comp1").physics("tcs").feature("reac1").selection().set(1);
    model.component("comp1").physics("tcs").feature("reac1").setIndex("R_wA_src", "root.comp1.chem.Rw_cA", 0);
    model.component("comp1").physics("tcs").feature("reac1").setIndex("R_wB_src", "root.comp1.chem.Rw_cB", 0);
    model.component("comp1").physics("tcs").feature("reac1").setIndex("R_wL_src", "root.comp1.chem.Rw_cL", 0);
    model.component("comp1").physics("tcs").feature("reac1").setIndex("R_wSC_src", "root.comp1.chem.Rw_cSC", 0);
    model.component("comp1").physics("tcs").feature("reac1").setIndex("R_wAC_src", "root.comp1.chem.Rw_cAC", 0);
    model.component("comp1").physics("ht").feature("init1").set("Tinit", "440[K]");
    model.component("comp1").physics("ht").create("temp1", "TemperatureBoundary", 2);
    model.component("comp1").physics("ht").feature("temp1").selection().set(1, 5);
    model.component("comp1").physics("ht").feature("temp1").set("T0", "440[K]");
    model.component("comp1").physics("ht").create("ofl1", "ConvectiveOutflow", 2);
    model.component("comp1").physics("ht").feature("ofl1").selection().set(11);
    model.component("comp1").physics("ht").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("ht").feature("sym1").selection().set(4, 8);
    model.component("comp1").physics("ht").create("hs1", "HeatSource", 3);
    model.component("comp1").physics("ht").feature("hs1").selection().set(1);
    model.component("comp1").physics("ht").feature("hs1").set("Q0", "-100[kJ/mol]*chem.r_1-40[kJ/mol]*chem.r_2");

    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("size").set("table", "cfd");
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().set(1, 3, 4, 5);
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection()
         .set(13, 14, 22, 23, 31, 33, 34, 35, 38, 40, 42, 43);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("table", "cfd");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hauto", 2);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("elemcount", 40);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("elemratio", 4);
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom(3);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().allGeom();
    model.component("comp1").mesh("mesh1").feature("bl1").set("splitdivangle", 50);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection()
         .set(2, 3, 6, 7, 9, 10, 14, 19, 22, 23, 24);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 6);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blhminfact", 2.4);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").setSolveFor("/physics/tcs", false);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", false);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/nirf1", false);
    model.study("std1").create("stat2", "Stationary");
    model.study("std1").feature("stat2").setSolveFor("/physics/spf", false);
    model.study("std1").feature("stat2").setSolveFor("/physics/ht", false);
    model.study("std1").create("stat3", "Stationary");
    model.study("std1").feature("stat3").setSolveFor("/physics/spf", false);
    model.study("std1").feature("stat3").setSolveFor("/physics/tcs", false);
    model.study("std1").feature("stat3").setSolveFor("/multiphysics/nirf1", false);
    model.study("std1").create("stat4", "Stationary");
    model.study("std1").showAutoSequences("all");
    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("sec1", "Sector3D");
    model.result().dataset("sec1").set("data", "dset4");
    model.result().dataset("sec1").setIndex("genpoints", 1, 1, 0);
    model.result().dataset("sec1").setIndex("genpoints", 0, 1, 2);
    model.result().dataset("sec1").set("sectors", 20);
    model.result().dataset("sec1").set("trans", "rotrefl");
    model.result().dataset("sec1").set("reflaxis", new int[]{0, 0, 1});
    model.result().dataset().duplicate("sec2", "sec1");
    model.result().dataset("sec2").set("include", "manual");
    model.result().dataset("sec2").set("startsector", 5);
    model.result().dataset("sec2").set("sectorsinclude", 12);
    model.result().dataset().create("cpl1", "CutPlane");
    model.result().dataset("cpl1").set("data", "sec1");
    model.result().dataset("cpl1").set("quickplane", "xy");
    model.result().dataset().create("cln1", "CutLine3D");
    model.result().dataset("cln1").set("data", "sec1");
    model.result().dataset("cln1").setIndex("genpoints", 0.4, 1, 0);
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").label("\u901f\u5ea6\uff0cxy \u5e73\u9762");
    model.result("pg1").set("data", "sec1");
    model.result("pg1").set("edges", false);
    model.result("pg1").create("slc1", "Slice");
    model.result("pg1").feature("slc1").set("quickplane", "xy");
    model.result("pg1").feature("slc1").set("quickznumber", 1);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("data", "sec2");
    model.result("pg1").feature("surf1").create("sel1", "Selection");
    model.result("pg1").feature("surf1").feature("sel1").selection().set(2, 3, 6, 7, 9, 10);
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("titletype", "none");
    model.result("pg1").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").feature("mtrl1").set("appearance", "custom");
    model.result("pg1").feature("surf1").feature("mtrl1").set("family", "steelanodized");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").label("\u6d53\u5ea6\uff0cA");
    model.result("pg2").run();
    model.result("pg2").feature("slc1").set("expr", "tcs.c_wA");
    model.result("pg2").feature("slc1").set("descr", "\u6469\u5c14\u6d53\u5ea6");
    model.result("pg2").feature().duplicate("slc2", "slc1");
    model.result("pg2").run();
    model.result("pg2").feature("slc2").set("titletype", "none");
    model.result("pg2").feature("slc2").set("quickplane", "zx");
    model.result("pg2").feature("slc2").set("quickynumber", 1);
    model.result("pg2").feature("slc2").set("inheritplot", "slc1");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result().duplicate("pg3", "pg2");
    model.result("pg3").run();
    model.result("pg3").label("\u6d53\u5ea6\uff0cL");
    model.result("pg3").run();
    model.result("pg3").feature("slc1").set("expr", "tcs.c_wL");
    model.result("pg3").feature("slc1").set("descr", "\u6469\u5c14\u6d53\u5ea6");
    model.result("pg3").run();
    model.result("pg3").feature("slc2").active(false);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg2").run();
    model.result().duplicate("pg4", "pg2");
    model.result("pg4").run();
    model.result("pg4").label("\u6e29\u5ea6");
    model.result("pg4").run();
    model.result("pg4").feature("slc1").set("expr", "T");
    model.result("pg4").feature("slc1").set("descr", "\u6e29\u5ea6");
    model.result("pg4").feature("slc1").set("colortable", "HeatCameraLight");
    model.result("pg4").run();
    model.result("pg4").feature("slc2").set("expr", "T");
    model.result("pg4").feature("slc2").set("descr", "\u6e29\u5ea6");
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u6d53\u5ea6\uff0cL\uff08\u8f74\u5411\uff09");
    model.result("pg5").set("data", "cln1");
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg5").feature("lngr1").set("linewidth", "preference");
    model.result("pg5").feature("lngr1").set("expr", "tcs.c_wL");
    model.result("pg5").feature("lngr1").set("descr", "\u6469\u5c14\u6d53\u5ea6");
    model.result("pg5").feature("lngr1").set("linewidth", 2);
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").run();
    model.result("pg6").label("\u901f\u5ea6\u6d41\u7ebf");
    model.result("pg6").set("data", "sec1");
    model.result("pg6").set("titletype", "none");
    model.result("pg6").set("edges", false);
    model.result("pg6").set("showlegendsunit", true);
    model.result("pg6").create("str1", "Streamline");
    model.result("pg6").feature("str1").set("number", 100);
    model.result("pg6").feature("str1").set("linetype", "tube");
    model.result("pg6").feature("str1").set("radiusexpr", "sqrt(tcs.c_wA)+sqrt(tcs.c_wB)");
    model.result("pg6").feature("str1").create("col1", "Color");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("data", "sec2");
    model.result("pg6").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg6").run();
    model.result("pg6").feature("surf1").feature("mtrl1").set("appearance", "custom");
    model.result("pg6").feature("surf1").feature("mtrl1").set("family", "steelanodized");
    model.result("pg6").run();
    model.result("pg6").feature("surf1").create("sel1", "Selection");
    model.result("pg6").feature("surf1").feature("sel1").selection().set(9);
    model.result("pg6").run();
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").run();
    model.result("pg7").label("\u6d53\u5ea6\uff0cL\uff08\u7b49\u503c\u9762\uff09");
    model.result("pg7").set("data", "sec1");
    model.result("pg7").set("titletype", "none");
    model.result("pg7").set("showlegendsunit", true);
    model.result("pg7").create("iso1", "Isosurface");
    model.result("pg7").feature("iso1").set("expr", "tcs.c_wL");
    model.result("pg7").feature("iso1").set("descr", "\u6469\u5c14\u6d53\u5ea6");
    model.result("pg7").feature("iso1").set("number", 8);
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").set("view", "new");
    model.result("pg7").set("edges", false);
    model.result("pg7").run();

    model.view("view5").set("clippingactive", true);
    model.view("view5").clip().create("plane1", "ClipPlane");
    model.view("view5").clip("plane1").set("orientationaxes", new double[][]{{-1, 0, 0}, {0, -1, 0}, {0, 0, 1}});
    model.view("view5").clip("plane1").set("position", new double[]{0.12386861154664075, 0, 0});
    model.view("view5").clip("plane1").set("translationamount", 0.010495741587510566);
    model.view("view5").set("ssao", true);
    model.view("view5").set("clipshowgizmos", true);

    model.result("pg7").run();
    model.result("pg7").feature("iso1").create("tran1", "Transparency");
    model.result("pg7").run();
    model.result("pg7").feature("iso1").feature("tran1").set("transparency", 0.1);
    model.result("pg7").run();
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", "1");
    model.result("pg7").feature("surf1").set("coloring", "uniform");
    model.result("pg7").feature("surf1").set("color", "gray");
    model.result("pg7").feature("surf1").create("tran1", "Transparency");
    model.result("pg7").run();
    model.result("pg7").feature("surf1").feature("tran1").set("transparency", 0.3);
    model.result("pg7").run();
    model.result("pg7").feature("surf1").create("filt1", "Filter");
    model.result("pg7").run();
    model.result("pg7").feature("surf1").feature("filt1").set("expr", "(sqrt(y^2+z^2)>r_r*0.995)||(x<r_r*0.005)");
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").feature("iso1").feature().copy("filt1", "pg7/surf1/filt1");
    model.result("pg7").run();
    model.result("pg7").feature("iso1").feature("filt1").set("expr", "(sqrt(y^2+z^2)<r_r*0.995)&&(x>r_r*0.005)");
    model.result("pg7").run();

    model.view("view5").set("cliphighlightintersection", false);
    model.view("view5").set("clipshowframes", false);

    model.result("pg7").run();
    model.result("pg7").set("legendpos", "left");

    model.view("view5").set("cliphighlightintersection", false);
    model.view("view5").set("clipshowframes", true);

    model.title("\u591a\u55b7\u5634\u7ba1\u5f0f\u53cd\u5e94\u5668\u4e2d\u7684\u805a\u5408\u53cd\u5e94");

    model
         .description("\u5728\u805a\u916f\u7ea4\u7ef4\u5236\u9020\u6240\u7528\u7684\u805a\u5408\u53cd\u5e94\u5668\u4e2d\uff0c\u53cd\u5e94\u7269\u7684\u6df7\u5408\u662f\u901a\u8fc7\u6e4d\u6d41\u55b7\u5c04\u5b9e\u73b0\u7684\u3002\u6e4d\u6d41\u4f1a\u5f71\u54cd\u805a\u5408\u7269\u7ed3\u5408\u7684\u53cd\u5e94\u52a8\u529b\u5b66\u548c\u8d28\u91cf\u3002\u672c\u4f8b\u4f7f\u7528\u201c\u53cd\u5e94\u6d41\u201d\u63a5\u53e3\u548c\u201c\u6d41\u4f53\u4f20\u70ed\u201d\u63a5\u53e3\u5bf9\u975e\u7b49\u6e29\u6761\u4ef6\u4e0b\u7684\u805a\u5408\u53cd\u5e94\u5668\u8fdb\u884c\u5efa\u6a21\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("polymerization_multijet.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
