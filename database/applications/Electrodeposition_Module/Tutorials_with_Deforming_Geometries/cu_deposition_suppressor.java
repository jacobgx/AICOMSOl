/*
 * cu_deposition_suppressor.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:59 by COMSOL 6.3.0.290. */
public class cu_deposition_suppressor {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Electrodeposition_Module\\Tutorials_with_Deforming_Geometries");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("tcd", "TertiaryCurrentDistributionNernstPlanck", "geom1");
    model.component("comp1").physics("tcd").prop("SpeciesProperties")
         .set("ChargeTransportModel", "SupportingElectrolyte");
    model.component("comp1").physics("tcd").field("concentration").field("cCu");
    model.component("comp1").physics("tcd").field("concentration").component(new String[]{"cCu", "cCl", "cP"});

    model.component("comp1").multiphysics().create("ndbdg1", "NonDeformingBoundaryDeformedGeometry", 1);
    model.component("comp1").multiphysics("ndbdg1").set("Echem_physics", "tcd");
    model.component("comp1").multiphysics("ndbdg1").selection().all();
    model.component("comp1").multiphysics().create("desdg1", "DeformingElectrodeSurfaceDeformedGeometry", 1);
    model.component("comp1").multiphysics("desdg1").set("Echem_physics", "tcd");
    model.component("comp1").multiphysics("desdg1").selection().all();

    model.component("comp1").common().create("free1", "DeformingDomainDeformedGeometry");
    model.component("comp1").common("free1").set("smoothingType", "hyperelastic");
    model.component("comp1").common("free1").selection().all();

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
    model.study("std1").feature("cdi").setSolveFor("/physics/tcd", true);
    model.study("std1").feature("cdi").setSolveFor("/multiphysics/ndbdg1", true);
    model.study("std1").feature("cdi").setSolveFor("/multiphysics/desdg1", true);
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("ftplistmethod", "manual");
    model.study("std1").feature("time").set("initialtime", "0");
    model.study("std1").feature("time").set("solnum", "auto");
    model.study("std1").feature("time").set("notsolnum", "auto");
    model.study("std1").feature("time").set("outputmap", new String[]{});
    model.study("std1").feature("time").setSolveFor("/physics/tcd", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/ndbdg1", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/desdg1", true);

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"15e-6", "25e-6"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"0", "20e-6"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"5e-6", "20e-6"});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("r1", "r2");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("fil1").selection("point").set("uni1", 5);
    model.component("comp1").geom("geom1").feature("fil1").set("radius", "1e-6");
    model.component("comp1").geom("geom1").run("fin");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("T0", "298[K]", "\u7cfb\u7edf\u6e29\u5ea6");
    model.param().set("Erev", "-0.38[V]", "\u53ef\u9006\u7535\u4f4d");
    model.param().set("i0_0", "20[A/m^2]", "\u672a\u6291\u5236\u7684\u94dc\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6");
    model.param().set("i0_1", "0.13[A/m^2]", "\u6291\u5236\u7684\u94dc\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6");
    model.param().set("phis_cathode", "-0.12[V]+Erev", "\u9634\u6781\u7535\u4f4d");
    model.param().set("alpha_a", "1.5", "\u9633\u6781\u4f20\u9012\u7cfb\u6570");
    model.param().set("z_Cu", "2[1]", "\u7535\u8377\uff0c\u7269\u8d28 Cu");
    model.param().set("z_Cl", "-1[1]", "\u7535\u8377\uff0c\u7269\u8d28 Cl");
    model.param().set("D_Cu", "2.65e-6[cm^2/s]", "\u6269\u6563\u7cfb\u6570\uff0c\u7269\u8d28 Cu");
    model.param().set("D_Cl", "9e-6[cm^2/s]", "\u6269\u6563\u7cfb\u6570\uff0c\u7269\u8d28 Cl");
    model.param().set("D_P", "1e-6[cm^2/s]", "\u6269\u6563\u7cfb\u6570\uff0c\u7269\u8d28\u805a\u919a");
    model.param().set("c0_Cu", "0.88[mol/l]", "\u521d\u59cb\u6d53\u5ea6\uff0c\u7269\u8d28 Cu");
    model.param().set("c0_Cl", "20e-6[mol/l]", "\u521d\u59cb\u6d53\u5ea6\uff0c\u7269\u8d28 Cl");
    model.param().set("c0_P", "40e-6[mol/l]", "\u521d\u59cb\u6d53\u5ea6\uff0c\u7269\u8d28\u805a\u919a");
    model.param().set("sigmal", "7.5[S/m]", "\u7535\u89e3\u8d28\u7535\u5bfc\u7387");
    model.param().set("Gamma_Cl", "1.62e-5[mol/m^2]", "\u9971\u548c\u6c2f\u5316\u7269\u8986\u76d6\u7387");
    model.param().set("Gamma_P", "9.2e-8[mol/m^2]", "\u9971\u548c\u6291\u5236\u5242\u8986\u76d6\u7387");
    model.param().set("kplus_Cl", "20[m^3/mol/s]", "\u6c2f\u5316\u7269\u5438\u9644\u52a8\u529b\u5b66");
    model.param().set("kminus_Cl", "1.5e7[1/m]", "\u6c2f\u5316\u7269\u5931\u6d3b\u52a8\u529b\u5b66");
    model.param().set("kplus_P", "2500[m^3/mol/s]", "\u6291\u5236\u5242\u5438\u9644\u52a8\u529b\u5b66");
    model.param().set("kminus_P", "1e7[1/m]", "\u6291\u5236\u5242\u5931\u6d3b\u52a8\u529b\u5b66");
    model.param().set("rho_Cu", "8960[kg/m^3]", "\u94dc\u7684\u5bc6\u5ea6");
    model.param().set("Mw_Cu", "0.06355[kg/mol]", "\u94dc\u7684\u6469\u5c14\u8d28\u91cf");

    model.component("comp1").variable().create("var1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("i0ref", "i0_0*(1-tcd.theta_es1_P)+i0_1*tcd.theta_es1_P", "\u53c2\u8003\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6");
    model.component("comp1").variable("var1")
         .set("R_Cl", "-Gamma_Cl*kplus_Cl*cCl*(1-tcd.theta_es1_Cl)", "Cl \u7684\u53cd\u5e94\u901f\u7387");
    model.component("comp1").variable("var1")
         .set("R_P", "-Gamma_P*kplus_P*cP*(tcd.theta_es1_Cl-tcd.theta_es1_P)", "P \u7684\u53cd\u5e94\u901f\u7387");
    model.component("comp1").variable("var1")
         .set("Rad_Cl", "Gamma_Cl*(kplus_Cl*cCl*(1-tcd.theta_es1_Cl)-kminus_Cl*tcd.theta_es1_Cl*tcd.vb_Cu)", "\u5438\u9644-\u89e3\u5438\u7269\u8d28\u7684\u53cd\u5e94\u901f\u7387\uff0cCl");
    model.component("comp1").variable("var1")
         .set("Rad_P", "Gamma_P*(kplus_P*cP*(tcd.theta_es1_Cl-tcd.theta_es1_P)-kminus_P*tcd.theta_es1_P*tcd.vb_Cu)", "\u5438\u9644-\u89e3\u5438\u7269\u8d28\u7684\u53cd\u5e94\u901f\u7387\uff0cP");

    model.component("comp1").physics("tcd").feature("sp1").setIndex("z", "z_Cu", 0);
    model.component("comp1").physics("tcd").feature("sp1").setIndex("z", "z_Cl", 1);
    model.component("comp1").physics("tcd").feature("ice1")
         .set("D_cCu", new String[]{"D_Cu", "0", "0", "0", "D_Cu", "0", "0", "0", "D_Cu"});
    model.component("comp1").physics("tcd").feature("ice1")
         .set("D_cCl", new String[]{"D_Cl", "0", "0", "0", "D_Cl", "0", "0", "0", "D_Cl"});
    model.component("comp1").physics("tcd").feature("ice1")
         .set("D_cP", new String[]{"D_P", "0", "0", "0", "D_P", "0", "0", "0", "D_P"});
    model.component("comp1").physics("tcd").feature("ice1").set("sigmal_mat", "userdef");
    model.component("comp1").physics("tcd").feature("ice1")
         .set("sigmal", new String[]{"sigmal", "0", "0", "0", "sigmal", "0", "0", "0", "sigmal"});
    model.component("comp1").physics("tcd").create("es1", "ElectrodeSurface", 1);
    model.component("comp1").physics("tcd").feature("es1").selection().set(5, 6, 8);

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(1);
    model.component("comp1").selection("sel1").label("\u9634\u6781");
    model.component("comp1").selection("sel1").set(5, 6, 8);

    model.component("comp1").physics("tcd").feature("es1").selection().named("sel1");
    model.component("comp1").physics("tcd").feature("es1").setIndex("Species", "s1", 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("rhos", 8960, 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Ms", 0.06355, 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Species", "s1", 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("rhos", 8960, 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Ms", 0.06355, 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Species", "Cu", 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("rhos", "rho_Cu", 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Ms", "Mw_Cu", 0, 0);
    model.component("comp1").physics("tcd").feature("es1").set("Gamma", "Gamma_Cl");
    model.component("comp1").physics("tcd").feature("es1").setIndex("AdsorbingDesorbingSpecies", "ads1", 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Sigma", 1, 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("AdsorbingDesorbingSpecies", "ads1", 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Sigma", 1, 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("AdsorbingDesorbingSpecies", "ads2", 1, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Sigma", 1, 1, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Sigma", 1, 1, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("AdsorbingDesorbingSpecies", "ads2", 1, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Sigma", 1, 1, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("AdsorbingDesorbingSpecies", "Cl", 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("AdsorbingDesorbingSpecies", "P", 1, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Sigma", "Gamma_Cl/Gamma_P", 1, 0);
    model.component("comp1").physics("tcd").feature("es1").set("phisext0", "phis_cathode");
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("nm", 2);
    model.component("comp1").physics("tcd").feature("es1").feature("er1").setIndex("Vi0", -1, 0);
    model.component("comp1").physics("tcd").feature("es1").feature("er1").setIndex("Vib", 1, 0, 0);
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("Eeq_ref", "Erev");
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("i0_ref", "i0ref");
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("alphaa", "alpha_a");
    model.component("comp1").physics("tcd").feature("es1").create("nfr1", "NonFaradaicReactions", 1);
    model.component("comp1").physics("tcd").feature("es1").feature("nfr1").setIndex("species", true, 1);
    model.component("comp1").physics("tcd").feature("es1").feature("nfr1").setIndex("J0", "R_Cl", 1);
    model.component("comp1").physics("tcd").feature("es1").feature("nfr1").setIndex("species", true, 2);
    model.component("comp1").physics("tcd").feature("es1").feature("nfr1").setIndex("J0", "R_P", 2);
    model.component("comp1").physics("tcd").feature("es1").feature("nfr1").setIndex("Rad", "Rad_Cl", 0, 0);
    model.component("comp1").physics("tcd").feature("es1").feature("nfr1").setIndex("Rad", "Rad_P", 1, 0);
    model.component("comp1").physics("tcd").create("eip1", "ElectrolytePotential", 1);
    model.component("comp1").physics("tcd").feature("eip1").selection().set(4);
    model.component("comp1").physics("tcd").create("conc1", "Concentration", 1);
    model.component("comp1").physics("tcd").feature("conc1").selection().set(4);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("species", true, 0);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("c0", "c0_Cu", 0);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("species", true, 1);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("c0", "c0_Cl", 1);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("species", true, 2);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("c0", "c0_P", 2);
    model.component("comp1").physics("tcd").create("sym1", "Symmetry", 1);
    model.component("comp1").physics("tcd").feature("sym1").selection().set(2);
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "c0_Cu", 0);
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "c0_Cl", 1);
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "c0_P", 2);

    model.component("comp1").multiphysics("ndbdg1").set("BoundaryCondition", "ZeroNormalDisplacement");

    model.common("cminpt").set("modified", new String[][]{{"temperature", "T0"}});

    model.component("comp1").mesh("mesh1").autoMeshSize(2);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("cdi").setSolveFor("/frame/material1", false);
    model.study("std1").feature("time").set("tunit", "min");
    model.study("std1").feature("time").set("tlist", "range(0,0.5,14.5)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("data", "dset1");
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("hasspacevars", true);
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 30, 0);
    model.result("pg1").label("\u7535\u89e3\u8d28\u7535\u4f4d (tcd)");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"phil"});
    model.result("pg1").create("str1", "Streamline");
    model.result("pg1").feature("str1").set("expr", new String[]{"tcd.Ilr", "tcd.Ilz"});
    model.result("pg1").feature("str1").set("posmethod", "uniform");
    model.result("pg1").feature("str1").set("recover", "pprint");
    model.result("pg1").feature("str1").set("pointtype", "arrow");
    model.result("pg1").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg1").feature("str1").set("color", "gray");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "rev1");
    model.result("pg2").setIndex("looplevel", 30, 0);
    model.result("pg2").label("\u7535\u89e3\u8d28\u7535\u4f4d, 3D (tcd)");
    model.result("pg2").create("mslc1", "Multislice");
    model.result("pg2").feature("mslc1").set("expr", new String[]{"phil"});
    model.result("pg2").create("str1", "Streamline");
    model.result("pg2").feature("str1").set("revcoordsys", "cylindrical");
    model.result("pg2").feature("str1").set("expr", new String[]{"tcd.Ilr", "tcd.Ilphi", "tcd.Ilz"});
    model.result("pg2").feature("str1").set("pointtype", "arrow");
    model.result("pg2").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg2").feature("str1").set("color", "gray");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").setIndex("looplevel", 30, 0);
    model.result("pg3").label("\u7535\u89e3\u8d28\u7535\u6d41\u5bc6\u5ea6 (tcd)");
    model.result("pg3").create("str1", "Streamline");
    model.result("pg3").feature("str1").set("expr", new String[]{"tcd.Ilr", "tcd.Ilz"});
    model.result("pg3").feature("str1").set("posmethod", "uniform");
    model.result("pg3").feature("str1").set("recover", "pprint");
    model.result("pg3").feature("str1").set("pointtype", "arrow");
    model.result("pg3").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg3").feature("str1").set("color", "gray");
    model.result("pg3").feature("str1").create("col1", "Color");
    model.result("pg3").feature("str1").feature("col1").set("expr", "root.comp1.tcd.IlMag");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "rev1");
    model.result("pg4").setIndex("looplevel", 30, 0);
    model.result("pg4").label("\u7535\u89e3\u8d28\u7535\u6d41\u5bc6\u5ea6, 3D (tcd)");
    model.result("pg4").create("str1", "Streamline");
    model.result("pg4").feature("str1").set("revcoordsys", "cylindrical");
    model.result("pg4").feature("str1").set("expr", new String[]{"tcd.Ilr", "tcd.Ilphi", "tcd.Ilz"});
    model.result("pg4").feature("str1").set("pointtype", "arrow");
    model.result("pg4").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg4").feature("str1").set("color", "gray");
    model.result("pg4").feature("str1").create("col1", "Color");
    model.result("pg4").feature("str1").feature("col1").set("expr", "root.comp1.tcd.IlMag");
    model.result("pg3").create("line1", "Line");
    model.result("pg3").feature("line1").set("expr", new String[]{"abs(tcd.itot)"});
    model.result("pg3").feature("line1").set("linetype", "tube");
    model.result("pg3").feature("line1").set("inherittubescale", false);
    model.result("pg3").feature("line1").set("inheritplot", "str1");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"abs(tcd.itot)"});
    model.result("pg4").feature("surf1").set("inheritplot", "str1");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").set("data", "dset1");
    model.result("pg5").setIndex("looplevel", 30, 0);
    model.result("pg5").label("\u5bf9\u5730\u7535\u6781\u7535\u4f4d (tcd)");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").set("data", "rev1");
    model.result("pg6").setIndex("looplevel", 30, 0);
    model.result("pg6").label("\u5bf9\u5730\u7535\u6781\u7535\u4f4d, 3D (tcd)");
    model.result("pg5").create("line1", "Line");
    model.result("pg5").feature("line1").set("expr", new String[]{"tcd.phisext"});
    model.result("pg5").feature("line1").set("linetype", "tube");
    model.result("pg5").feature("line1").set("inherittubescale", false);
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", new String[]{"tcd.phisext"});
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").set("data", "dset1");
    model.result("pg7").setIndex("looplevel", 30, 0);
    model.result("pg7").label("\u7535\u6781\u7535\u4f4d vs. \u76f8\u90bb\u53c2\u6bd4\u7535\u4f4d (tcd)");
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").set("data", "rev1");
    model.result("pg8").setIndex("looplevel", 30, 0);
    model.result("pg8").label("\u7535\u6781\u7535\u4f4d vs. \u76f8\u90bb\u53c2\u6bd4\u7535\u4f4d, 3D (tcd)");
    model.result("pg7").create("str1", "Streamline");
    model.result("pg7").feature("str1").set("expr", new String[]{"tcd.Ilr", "tcd.Ilz"});
    model.result("pg7").feature("str1").set("posmethod", "uniform");
    model.result("pg7").feature("str1").set("recover", "pprint");
    model.result("pg7").feature("str1").set("pointtype", "arrow");
    model.result("pg7").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg7").feature("str1").set("color", "gray");
    model.result("pg8").create("str1", "Streamline");
    model.result("pg8").feature("str1").set("revcoordsys", "cylindrical");
    model.result("pg8").feature("str1").set("expr", new String[]{"tcd.Ilr", "tcd.Ilphi", "tcd.Ilz"});
    model.result("pg8").feature("str1").set("pointtype", "arrow");
    model.result("pg8").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg8").feature("str1").set("color", "gray");
    model.result("pg7").create("line1", "Line");
    model.result("pg7").feature("line1").set("expr", new String[]{"tcd.Evsref"});
    model.result("pg7").feature("line1").set("linetype", "tube");
    model.result("pg7").feature("line1").set("inherittubescale", false);
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("expr", new String[]{"tcd.Evsref"});
    model.result().create("pg9", "PlotGroup2D");
    model.result("pg9").set("data", "dset1");
    model.result("pg9").setIndex("looplevel", 30, 0);
    model.result("pg9").label("\u7535\u6781\u603b\u539a\u5ea6\u53d8\u5316 (tcd)");
    model.result().create("pg10", "PlotGroup3D");
    model.result("pg10").set("data", "rev1");
    model.result("pg10").setIndex("looplevel", 30, 0);
    model.result("pg10").label("\u7535\u6781\u603b\u539a\u5ea6\u53d8\u5316, 3D (tcd)");
    model.result("pg9").create("line1", "Line");
    model.result("pg9").feature("line1").set("expr", new String[]{"tcd.sbtot"});
    model.result("pg9").feature("line1").set("linetype", "tube");
    model.result("pg9").feature("line1").set("inherittubescale", false);
    model.result("pg9").feature("line1").set("unit", "\u00b5m");
    model.result("pg10").create("surf1", "Surface");
    model.result("pg10").feature("surf1").set("expr", new String[]{"tcd.sbtot"});
    model.result("pg10").feature("surf1").set("unit", "\u00b5m");
    model.result().create("pg11", "PlotGroup2D");
    model.result("pg11").set("data", "dset1");
    model.result("pg11").setIndex("looplevel", 30, 0);
    model.result("pg11").label("\u6d53\u5ea6, Cu (tcd)");
    model.result("pg11").set("titletype", "custom");
    model.result("pg11").set("prefixintitle", "");
    model.result("pg11").set("expressionintitle", false);
    model.result("pg11").set("typeintitle", false);
    model.result("pg11").create("surf1", "Surface");
    model.result("pg11").feature("surf1").set("expr", new String[]{"cCu"});
    model.result("pg11").feature("surf1").set("colortable", "Rainbow");
    model.result("pg11").set("typeintitle", true);
    model.result("pg11").create("str1", "Streamline");
    model.result("pg11").feature("str1").set("expr", new String[]{"tcd.tflux_cCur", "tcd.tflux_cCuz"});
    model.result("pg11").feature("str1").set("posmethod", "uniform");
    model.result("pg11").feature("str1").set("recover", "pprint");
    model.result("pg11").feature("str1").set("pointtype", "arrow");
    model.result("pg11").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg11").feature("str1").set("color", "gray");
    model.result().dataset().create("rev2", "Revolve2D");
    model.result().dataset("rev2").set("data", "dset1");
    model.result().dataset("rev2").set("revangle", 225);
    model.result().dataset("rev2").set("startangle", -90);
    model.result().dataset("rev2").set("hasspacevars", false);
    model.result().create("pg12", "PlotGroup3D");
    model.result("pg12").set("data", "rev2");
    model.result("pg12").setIndex("looplevel", 30, 0);
    model.result("pg12").label("\u6d53\u5ea6, Cu, 3D (tcd)");
    model.result("pg12").create("surf1", "Surface");
    model.result("pg12").feature("surf1").set("expr", new String[]{"cCu"});
    model.result("pg12").feature("surf1").set("colortable", "Rainbow");
    model.result("pg12").set("titletype", "custom");
    model.result("pg12").set("typeintitle", false);
    model.result("pg12").set("prefixintitle", "");
    model.result("pg12").set("expressionintitle", false);
    model.result().create("pg13", "PlotGroup2D");
    model.result("pg13").set("data", "dset1");
    model.result("pg13").setIndex("looplevel", 30, 0);
    model.result("pg13").label("\u6d53\u5ea6, Cl (tcd)");
    model.result("pg13").set("titletype", "custom");
    model.result("pg13").set("prefixintitle", "");
    model.result("pg13").set("expressionintitle", false);
    model.result("pg13").set("typeintitle", false);
    model.result("pg13").create("surf1", "Surface");
    model.result("pg13").feature("surf1").set("expr", new String[]{"cCl"});
    model.result("pg13").feature("surf1").set("colortable", "Rainbow");
    model.result("pg13").set("typeintitle", true);
    model.result("pg13").create("str1", "Streamline");
    model.result("pg13").feature("str1").set("expr", new String[]{"tcd.tflux_cClr", "tcd.tflux_cClz"});
    model.result("pg13").feature("str1").set("posmethod", "uniform");
    model.result("pg13").feature("str1").set("recover", "pprint");
    model.result("pg13").feature("str1").set("pointtype", "arrow");
    model.result("pg13").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg13").feature("str1").set("color", "gray");
    model.result().create("pg14", "PlotGroup3D");
    model.result("pg14").set("data", "rev2");
    model.result("pg14").setIndex("looplevel", 30, 0);
    model.result("pg14").label("\u6d53\u5ea6, Cl, 3D (tcd)");
    model.result("pg14").create("surf1", "Surface");
    model.result("pg14").feature("surf1").set("expr", new String[]{"cCl"});
    model.result("pg14").feature("surf1").set("colortable", "Rainbow");
    model.result("pg14").set("titletype", "custom");
    model.result("pg14").set("typeintitle", false);
    model.result("pg14").set("prefixintitle", "");
    model.result("pg14").set("expressionintitle", false);
    model.result().create("pg15", "PlotGroup2D");
    model.result("pg15").set("data", "dset1");
    model.result("pg15").setIndex("looplevel", 30, 0);
    model.result("pg15").label("\u6d53\u5ea6, P (tcd)");
    model.result("pg15").set("titletype", "custom");
    model.result("pg15").set("prefixintitle", "");
    model.result("pg15").set("expressionintitle", false);
    model.result("pg15").set("typeintitle", false);
    model.result("pg15").create("surf1", "Surface");
    model.result("pg15").feature("surf1").set("expr", new String[]{"cP"});
    model.result("pg15").feature("surf1").set("colortable", "Rainbow");
    model.result("pg15").set("typeintitle", true);
    model.result("pg15").create("str1", "Streamline");
    model.result("pg15").feature("str1").set("expr", new String[]{"tcd.tflux_cPr", "tcd.tflux_cPz"});
    model.result("pg15").feature("str1").set("posmethod", "uniform");
    model.result("pg15").feature("str1").set("recover", "pprint");
    model.result("pg15").feature("str1").set("pointtype", "arrow");
    model.result("pg15").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg15").feature("str1").set("color", "gray");
    model.result().create("pg16", "PlotGroup3D");
    model.result("pg16").set("data", "rev2");
    model.result("pg16").setIndex("looplevel", 30, 0);
    model.result("pg16").label("\u6d53\u5ea6, P, 3D (tcd)");
    model.result("pg16").create("surf1", "Surface");
    model.result("pg16").feature("surf1").set("expr", new String[]{"cP"});
    model.result("pg16").feature("surf1").set("colortable", "Rainbow");
    model.result("pg16").set("titletype", "custom");
    model.result("pg16").set("typeintitle", false);
    model.result("pg16").set("prefixintitle", "");
    model.result("pg16").set("expressionintitle", false);
    model.result().create("pg17", "PlotGroup2D");
    model.result("pg17").set("data", "dset1");
    model.result("pg17").setIndex("looplevel", 30, 0);
    model.result("pg17").label("\u53d8\u5f62\u51e0\u4f55");
    model.result("pg17").create("mesh1", "Mesh");
    model.result("pg17").feature("mesh1").set("meshdomain", "surface");
    model.result("pg17").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg17").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg17").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg17").feature("mesh1").create("sel1", "MeshSelection");
    model.result("pg17").feature("mesh1").feature("sel1").selection().set(1);
    model.result("pg17").feature("mesh1").set("qualmeasure", "custom");
    model.result("pg17").feature("mesh1").set("qualexpr", "comp1.material.relVol");
    model.result("pg17").feature("mesh1").set("colorrangeunitinterval", false);
    model.result("pg1").run();
    model.result().dataset().create("mir1", "Mirror3D");
    model.result().dataset("mir1").set("quickplane", "xy");
    model.result("pg14").run();
    model.result("pg14").set("data", "mir1");
    model.result("pg14").create("con1", "Contour");
    model.result("pg14").feature("con1").set("expr", "cCl");
    model.result("pg14").feature("con1").set("descr", "\u6469\u5c14\u6d53\u5ea6\uff0ccCl");
    model.result("pg14").feature("con1").set("coloring", "uniform");
    model.result("pg14").feature("con1").set("color", "black");
    model.result("pg14").feature("con1").set("colorlegend", false);
    model.result("pg14").run();
    model.result().create("pg18", "PlotGroup1D");
    model.result("pg18").run();
    model.result("pg18").label("\u6c89\u79ef\u539a\u5ea6");
    model.result("pg18").set("titletype", "label");
    model.result("pg18").create("lngr1", "LineGraph");
    model.result("pg18").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg18").feature("lngr1").set("linewidth", "preference");
    model.result("pg18").feature("lngr1").selection().set(5);
    model.result("pg18").feature("lngr1").set("expr", "tcd.sbtot");
    model.result("pg18").feature("lngr1").set("unit", "\u00b5m");
    model.result("pg18").feature("lngr1").set("xdata", "expr");
    model.result("pg18").feature("lngr1").set("xdataexpr", "z");
    model.result("pg18").feature("lngr1").set("xdataunit", "\u00b5m");
    model.result("pg18").feature("lngr1").set("xdatadescractive", true);
    model.result("pg18").feature("lngr1")
         .set("xdatadescr", "\u6cbf\u901a\u5b54\u5782\u76f4\u8fb9\u5230\u4e2d\u5fc3\u7684\u8ddd\u79bb");
    model.result("pg18").run();
    model.result("pg18").run();
    model.result().duplicate("pg19", "pg18");
    model.result("pg19").run();
    model.result("pg19").label("\u8868\u9762\u8986\u76d6\u7387\uff0cCl (tcd)");
    model.result("pg19").run();
    model.result("pg19").feature("lngr1").set("expr", "tcd.theta_es1_Cl");
    model.result("pg19").run();

    model.title("\u901a\u5b54\u4e2d\u7684\u94dc\u6c89\u79ef");

    model
         .description("\u672c\u6a21\u578b\u6f14\u793a\u94dc\u7684\u7535\u6c89\u79ef\u8fc7\u7a0b\u4e2d\u901a\u5b54\u7684\u201c\u8774\u8776\u201d\u586b\u5145\u673a\u5236\u3002\u7531\u4e8e\u7535\u89e3\u8d28\u4e2d\u5b58\u5728\u5364\u5316\u7269\u6291\u5236\u6dfb\u52a0\u5242\uff0c\u56e0\u6b64\u7535\u6c89\u79ef\u4f1a\u9009\u62e9\u6027\u5730\u53d1\u751f\u5728\u901a\u5b54\u4e2d\u5fc3\uff0c\u4ece\u800c\u907f\u514d\u5f62\u6210\u7535\u89e3\u8d28\u5916\u58f3\u3002\n\n\u672c\u4f8b\u7ed3\u5408\u4f7f\u7528\u201c\u4e09\u6b21\u7535\u6d41\u5206\u5e03\uff0cNernst Planck\u201d\u63a5\u53e3\u548c\u201c\u53d8\u5f62\u51e0\u4f55\u201d\u6765\u8ddf\u8e2a\u9634\u6781\u8868\u9762\u7684\u79fb\u52a8\u8fb9\u754c\uff0c\u5176\u4e2d\u5305\u542b\u7535\u89e3\u8d28\u7269\u8d28\u4e0e\u7535\u6781\u8868\u9762\u5438\u9644\u7684\u6dfb\u52a0\u5242\u7684\u8d28\u91cf\u5e73\u8861\uff0c\u4ee5\u53ca\u5047\u8bbe\u5b58\u5728\u652f\u6301\u7535\u89e3\u8d28\u7684\u7535\u89e3\u8d28\u7535\u8377\u5e73\u8861\u3002");

    return model;
  }

  public static Model run2(Model model) {

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("cu_deposition_suppressor.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
