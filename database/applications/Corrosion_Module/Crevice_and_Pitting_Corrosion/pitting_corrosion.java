/*
 * pitting_corrosion.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:43 by COMSOL 6.3.0.290. */
public class pitting_corrosion {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Corrosion_Module\\Crevice_and_Pitting_Corrosion");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("tcd", "TertiaryCurrentDistributionNernstPlanck", "geom1");
    model.component("comp1").physics("tcd").field("concentration").field("cNa");
    model.component("comp1").physics("tcd").field("concentration").component(new String[]{"cNa", "cCl", "cFe"});

    model.component("comp1").multiphysics().create("ndbdg1", "NonDeformingBoundaryDeformedGeometry", 1);
    model.component("comp1").multiphysics("ndbdg1").set("Echem_physics", "tcd");
    model.component("comp1").multiphysics("ndbdg1").selection().all();
    model.component("comp1").multiphysics().create("desdg1", "DeformingElectrodeSurfaceDeformedGeometry", 1);
    model.component("comp1").multiphysics("desdg1").set("Echem_physics", "tcd");
    model.component("comp1").multiphysics("desdg1").selection().all();
    model.component("comp1").multiphysics("desdg1").set("embs", "0");

    model.component("comp1").common().create("free1", "DeformingDomainDeformedGeometry");
    model.component("comp1").common("free1").set("smoothingType", "hyperelastic");
    model.component("comp1").common("free1").selection().all();

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("cCl_0", "cNa_0+cH_0+2*cFe_0-cOH_0", "\u672c\u4f53 Cl- \u6d53\u5ea6");
    model.param().set("cFe_0", "Keq_FeOH2/cOH_0^2", "\u672c\u4f53 Fe2+ \u6d53\u5ea6");
    model.param().set("cH_0", "10^(-pKw-pH)[M]", "\u672c\u4f53 H+ \u6d53\u5ea6");
    model.param()
         .set("cH_ref", "1e-7[M]", "\u94c1\u6eb6\u89e3\u52a8\u529b\u5b66\u7684\u8d28\u5b50\u53c2\u8003\u6d53\u5ea6");
    model.param().set("cNa_0", "1[mM]", "\u672c\u4f53 Na+ \u6d53\u5ea6");
    model.param().set("cOH_0", "10^(-pKw+pH)[M]", "\u672c\u4f53 OH- \u6d53\u5ea6");
    model.param().set("D_Cl", "2.0e-9[m^2/s]", "\u6269\u6563\u7cfb\u6570\uff0cNa+");
    model.param().set("D_Fe", "0.72e-9[m^2/s]", "\u6269\u6563\u7cfb\u6570\uff0cFe2+");
    model.param().set("D_H", "9.3e-9[m^2/s]", "\u6269\u6563\u7cfb\u6570\uff0cH+");
    model.param().set("D_Na", "1.3e-9[m^2/s]", "\u6269\u6563\u7cfb\u6570\uff0cCl-");
    model.param().set("D_OH", "5.3e-9[m^2/s]", "\u6269\u6563\u7cfb\u6570\uff0cOH-");
    model.param().set("E_metal", "-0.31[V]", "\u91d1\u5c5e\u6df7\u5408\u7535\u4f4d vs. SHE");
    model.param().set("Eeq_Fe", "-0.44[V]", "\u94c1\u7684\u6eb6\u89e3\u5e73\u8861\u7535\u4f4d vs. SHE");
    model.param()
         .set("epsl_pit", "0.025", "\u51f9\u5751\u4e2d\u7684\u7535\u89e3\u8d28\u4f53\u79ef\u5206\u6570\uff08\u5b54\u9699\u7387\uff09");
    model.param().set("H_pit", "5[um]", "\u521d\u59cb\u51f9\u5751\u9ad8\u5ea6");
    model.param()
         .set("i0_ref_Fe", "1e0[A/m^2]", "\u53c2\u8003\u6761\u4ef6\u4e0b\u7684\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6");
    model.param().set("k_FeOH2", "1e4[m^6/mol^2/s]", "\u901f\u7387\u5e38\u6570");
    model.param().set("Keq_FeOH2", "(7.66e-6[M])^3", "\u5e73\u8861\u5e38\u6570");
    model.param().set("pH", "10", "\u672c\u4f53 pH");
    model.param()
         .set("pKw", "14.94-0.04209*(-273.15+T)/1[K]+1.718E-4*(-273.15+T)^2/1[K^2]", "\u6c34\u7684\u89e3\u79bb\u5e38\u6570");
    model.param().set("R_pit", "0.5[um]", "\u521d\u59cb\u51f9\u5751\u534a\u5f84");
    model.param().set("T", "293.15[K]", "\u6e29\u5ea6");
    model.param().set("rho_Fe", "7860[kg/m^3]", "\u5bc6\u5ea6\uff0c\u94c1");
    model.param().set("M_Fe", "55.8[g/mol]", "\u6469\u5c14\u8d28\u91cf\uff0c\u94c1");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"R_pit*20", "R_pit*10"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"R_pit", "H_pit"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"0", "-H_pit"});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("r1", "r2");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("fil1").selection("point").set("uni1", 4, 5);
    model.component("comp1").geom("geom1").feature("fil1").set("radius", "R_pit");
    model.component("comp1").geom("geom1").run("fil1");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("tcd").prop("SpeciesProperties").set("ChargeTransportModel", "WaterBased");

    model.component("comp1").variable().create("var1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("R_FeOH2_expr", "k_FeOH2*(cFe*(tcd.cOH)^2-Keq_FeOH2)", "FeOH2 \u5f62\u6210\u7387");
    model.component("comp1").variable("var1")
         .set("R_FeOH2", "if(R_FeOH2_expr>0,R_FeOH2_expr,0)", "FeOH2 \u5f62\u6210\u7387\uff0c\u4e0d\u53ef\u9006");
    model.component("comp1").variable("var1")
         .set("epsl", "if(z>0,1,epsl_pit)", "\u7535\u89e3\u8d28\u4f53\u79ef\u5206\u6570");

    model.component("comp1").physics("tcd").feature("sp1").setIndex("z", 1, 0);
    model.component("comp1").physics("tcd").feature("sp1").setIndex("z", -1, 1);
    model.component("comp1").physics("tcd").feature("sp1").setIndex("z", 2, 2);
    model.component("comp1").physics("tcd").create("sep1", "Separator", 2);
    model.component("comp1").physics("tcd").feature("sep1").selection().all();
    model.component("comp1").physics("tcd").feature("sep1")
         .set("D_cNa", new String[]{"D_Na", "0", "0", "0", "D_Na", "0", "0", "0", "D_Na"});
    model.component("comp1").physics("tcd").feature("sep1")
         .set("D_cCl", new String[]{"D_Cl", "0", "0", "0", "D_Cl", "0", "0", "0", "D_Cl"});
    model.component("comp1").physics("tcd").feature("sep1")
         .set("D_cFe", new String[]{"D_Fe", "0", "0", "0", "D_Fe", "0", "0", "0", "D_Fe"});
    model.component("comp1").physics("tcd").feature("sep1")
         .set("DH", new String[]{"D_H", "0", "0", "0", "D_H", "0", "0", "0", "D_H"});
    model.component("comp1").physics("tcd").feature("sep1")
         .set("DOH", new String[]{"D_OH", "0", "0", "0", "D_OH", "0", "0", "0", "D_OH"});
    model.component("comp1").physics("tcd").feature("sep1").set("epsl", "epsl");
    model.component("comp1").physics("tcd").create("reac1", "Reactions", 2);
    model.component("comp1").physics("tcd").feature("reac1").selection().all();
    model.component("comp1").physics("tcd").feature("reac1").setIndex("R_cFe", "-R_FeOH2", 0);
    model.component("comp1").physics("tcd").feature("reac1").set("ROH", "-2*R_FeOH2");
    model.component("comp1").physics("tcd").create("conc1", "Concentration", 1);
    model.component("comp1").physics("tcd").feature("conc1").selection().set(3);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("species", true, 0);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("c0", "cNa_0", 0);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("species", true, 1);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("c0", "cCl_0", 1);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("species", true, 2);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("c0", "cFe_0", 2);
    model.component("comp1").physics("tcd").create("eip1", "ElectrolytePotential", 1);
    model.component("comp1").physics("tcd").feature("eip1").selection().set(3);
    model.component("comp1").physics("tcd").create("es1", "ElectrodeSurface", 1);
    model.component("comp1").physics("tcd").feature("es1").selection().set(4, 5, 7, 8);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Species", "s1", 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("rhos", 8960, 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Ms", 0.06355, 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Species", "s1", 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("rhos", 8960, 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Ms", 0.06355, 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Species", "Fe", 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("rhos", "rho_Fe", 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Ms", "M_Fe", 0, 0);
    model.component("comp1").physics("tcd").feature("es1")
         .set("SolveForDissolvingDepositingConcentrationVariable", false);
    model.component("comp1").physics("tcd").feature("es1").set("phisext0", "E_metal");
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("nm", 2);
    model.component("comp1").physics("tcd").feature("es1").feature("er1").setIndex("Vi0", -1, 2);
    model.component("comp1").physics("tcd").feature("es1").feature("er1").setIndex("Vib", 1, 0, 0);
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("Eeq_ref", "Eeq_Fe");
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("i0_ref", "i0_ref_Fe*(tcd.cH/cH_ref)");
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("alphaa", 0.25);
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "cNa_0", 0);
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "cCl_0", 1);
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "cFe_0", 2);

    model.component("comp1").multiphysics("ndbdg1").set("BoundaryCondition", "ZeroNormalDisplacement");

    model.component("comp1").physics("tcd").prop("ShapeProperty").set("order_concentration", 1);
    model.component("comp1").physics("tcd").prop("ShapeProperty").set("order_electricpotentialionicphase", 1);
    model.component("comp1").physics("tcd").prop("ShapeProperty").set("order_electricpotential", 1);

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().set(4, 7, 8);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hauto", 1);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").selection().geom("geom1", 0);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hauto", 1);
    model.component("comp1").mesh("mesh1").run();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/tcd", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/ndbdg1", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/desdg1", true);
    model.study("std1").feature("stat").setSolveFor("/frame/material1", false);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/ndbdg1", false);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/desdg1", false);
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("tunit", "d");
    model.study("std1").feature("time").set("tlist", "range(0,1,30)");
    model.study("std1").feature("time").set("autoremesh", true);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol3");
    model.sol("sol3").label("\u91cd\u65b0\u5212\u5206\u7f51\u683c\u540e\u7684\u89e3 1");
    model.sol("sol3").study("std1");
    model.sol("sol1").feature("t1").feature("arDef").set("tadapsol", "sol3");
    model.sol("sol1").runAll();

    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("data", "dset3");
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("hasspacevars", true);
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset3");
    model.result("pg1").setIndex("looplevel", 33, 0);
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
    model.result("pg2").setIndex("looplevel", 33, 0);
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
    model.result("pg3").set("data", "dset3");
    model.result("pg3").setIndex("looplevel", 33, 0);
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
    model.result("pg4").setIndex("looplevel", 33, 0);
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
    model.result("pg5").set("data", "dset3");
    model.result("pg5").setIndex("looplevel", 33, 0);
    model.result("pg5").label("\u5bf9\u5730\u7535\u6781\u7535\u4f4d (tcd)");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").set("data", "rev1");
    model.result("pg6").setIndex("looplevel", 33, 0);
    model.result("pg6").label("\u5bf9\u5730\u7535\u6781\u7535\u4f4d, 3D (tcd)");
    model.result("pg5").create("line1", "Line");
    model.result("pg5").feature("line1").set("expr", new String[]{"tcd.phisext"});
    model.result("pg5").feature("line1").set("linetype", "tube");
    model.result("pg5").feature("line1").set("inherittubescale", false);
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", new String[]{"tcd.phisext"});
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").set("data", "dset3");
    model.result("pg7").setIndex("looplevel", 33, 0);
    model.result("pg7").label("\u7535\u6781\u7535\u4f4d vs. \u76f8\u90bb\u53c2\u6bd4\u7535\u4f4d (tcd)");
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").set("data", "rev1");
    model.result("pg8").setIndex("looplevel", 33, 0);
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
    model.result("pg9").set("data", "dset3");
    model.result("pg9").setIndex("looplevel", 33, 0);
    model.result("pg9").label("\u6d53\u5ea6, Na (tcd)");
    model.result("pg9").set("titletype", "custom");
    model.result("pg9").set("prefixintitle", "");
    model.result("pg9").set("expressionintitle", false);
    model.result("pg9").set("typeintitle", false);
    model.result("pg9").create("surf1", "Surface");
    model.result("pg9").feature("surf1").set("expr", new String[]{"cNa"});
    model.result("pg9").feature("surf1").set("colortable", "Rainbow");
    model.result("pg9").set("typeintitle", true);
    model.result("pg9").create("str1", "Streamline");
    model.result("pg9").feature("str1").set("expr", new String[]{"tcd.tflux_cNar", "tcd.tflux_cNaz"});
    model.result("pg9").feature("str1").set("posmethod", "uniform");
    model.result("pg9").feature("str1").set("recover", "pprint");
    model.result("pg9").feature("str1").set("pointtype", "arrow");
    model.result("pg9").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg9").feature("str1").set("color", "gray");
    model.result().dataset().create("rev2", "Revolve2D");
    model.result().dataset("rev2").set("data", "dset3");
    model.result().dataset("rev2").set("revangle", 225);
    model.result().dataset("rev2").set("startangle", -90);
    model.result().dataset("rev2").set("hasspacevars", false);
    model.result().create("pg10", "PlotGroup3D");
    model.result("pg10").set("data", "rev2");
    model.result("pg10").setIndex("looplevel", 33, 0);
    model.result("pg10").label("\u6d53\u5ea6, Na, 3D (tcd)");
    model.result("pg10").create("surf1", "Surface");
    model.result("pg10").feature("surf1").set("expr", new String[]{"cNa"});
    model.result("pg10").feature("surf1").set("colortable", "Rainbow");
    model.result("pg10").set("titletype", "custom");
    model.result("pg10").set("typeintitle", false);
    model.result("pg10").set("prefixintitle", "");
    model.result("pg10").set("expressionintitle", false);
    model.result().create("pg11", "PlotGroup2D");
    model.result("pg11").set("data", "dset3");
    model.result("pg11").setIndex("looplevel", 33, 0);
    model.result("pg11").label("\u6d53\u5ea6, Cl (tcd)");
    model.result("pg11").set("titletype", "custom");
    model.result("pg11").set("prefixintitle", "");
    model.result("pg11").set("expressionintitle", false);
    model.result("pg11").set("typeintitle", false);
    model.result("pg11").create("surf1", "Surface");
    model.result("pg11").feature("surf1").set("expr", new String[]{"cCl"});
    model.result("pg11").feature("surf1").set("colortable", "Rainbow");
    model.result("pg11").set("typeintitle", true);
    model.result("pg11").create("str1", "Streamline");
    model.result("pg11").feature("str1").set("expr", new String[]{"tcd.tflux_cClr", "tcd.tflux_cClz"});
    model.result("pg11").feature("str1").set("posmethod", "uniform");
    model.result("pg11").feature("str1").set("recover", "pprint");
    model.result("pg11").feature("str1").set("pointtype", "arrow");
    model.result("pg11").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg11").feature("str1").set("color", "gray");
    model.result().create("pg12", "PlotGroup3D");
    model.result("pg12").set("data", "rev2");
    model.result("pg12").setIndex("looplevel", 33, 0);
    model.result("pg12").label("\u6d53\u5ea6, Cl, 3D (tcd)");
    model.result("pg12").create("surf1", "Surface");
    model.result("pg12").feature("surf1").set("expr", new String[]{"cCl"});
    model.result("pg12").feature("surf1").set("colortable", "Rainbow");
    model.result("pg12").set("titletype", "custom");
    model.result("pg12").set("typeintitle", false);
    model.result("pg12").set("prefixintitle", "");
    model.result("pg12").set("expressionintitle", false);
    model.result().create("pg13", "PlotGroup2D");
    model.result("pg13").set("data", "dset3");
    model.result("pg13").setIndex("looplevel", 33, 0);
    model.result("pg13").label("\u6d53\u5ea6, Fe (tcd)");
    model.result("pg13").set("titletype", "custom");
    model.result("pg13").set("prefixintitle", "");
    model.result("pg13").set("expressionintitle", false);
    model.result("pg13").set("typeintitle", false);
    model.result("pg13").create("surf1", "Surface");
    model.result("pg13").feature("surf1").set("expr", new String[]{"cFe"});
    model.result("pg13").feature("surf1").set("colortable", "Rainbow");
    model.result("pg13").set("typeintitle", true);
    model.result("pg13").create("str1", "Streamline");
    model.result("pg13").feature("str1").set("expr", new String[]{"tcd.tflux_cFer", "tcd.tflux_cFez"});
    model.result("pg13").feature("str1").set("posmethod", "uniform");
    model.result("pg13").feature("str1").set("recover", "pprint");
    model.result("pg13").feature("str1").set("pointtype", "arrow");
    model.result("pg13").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg13").feature("str1").set("color", "gray");
    model.result().create("pg14", "PlotGroup3D");
    model.result("pg14").set("data", "rev2");
    model.result("pg14").setIndex("looplevel", 33, 0);
    model.result("pg14").label("\u6d53\u5ea6, Fe, 3D (tcd)");
    model.result("pg14").create("surf1", "Surface");
    model.result("pg14").feature("surf1").set("expr", new String[]{"cFe"});
    model.result("pg14").feature("surf1").set("colortable", "Rainbow");
    model.result("pg14").set("titletype", "custom");
    model.result("pg14").set("typeintitle", false);
    model.result("pg14").set("prefixintitle", "");
    model.result("pg14").set("expressionintitle", false);
    model.result().create("pg15", "PlotGroup2D");
    model.result("pg15").set("data", "dset3");
    model.result("pg15").setIndex("looplevel", 33, 0);
    model.result("pg15").label("\u53d8\u5f62\u51e0\u4f55");
    model.result("pg15").create("mesh1", "Mesh");
    model.result("pg15").feature("mesh1").set("meshdomain", "surface");
    model.result("pg15").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg15").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg15").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg15").feature("mesh1").create("sel1", "MeshSelection");
    model.result("pg15").feature("mesh1").feature("sel1").selection().set(1);
    model.result("pg15").feature("mesh1").set("qualmeasure", "custom");
    model.result("pg15").feature("mesh1").set("qualexpr", "comp1.material.relVol");
    model.result("pg15").feature("mesh1").set("colorrangeunitinterval", false);
    model.result("pg1").run();
    model.result("pg4").run();
    model.result("pg4").set("edges", false);
    model.result("pg4").run();
    model.result("pg4").feature("str1").set("arrowlength", "normalized");
    model.result("pg4").run();
    model.result("pg4").feature("str1").feature("col1").active(false);
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("colortable", "AuroraBorealis");
    model.result("pg4").feature("surf1").set("colortabletrans", "reverse");

    model.view("view2").set("showgrid", false);

    model.result("pg4").run();
    model.result().create("pg16", "PlotGroup2D");
    model.result("pg16").run();
    model.result("pg16").label("pH");
    model.result("pg16").set("data", "dset3");
    model.result("pg16").set("looplevel", new int[]{1});
    model.result("pg16").create("surf1", "Surface");
    model.result("pg16").feature("surf1").set("expr", "tcd.pH");
    model.result("pg16").run();
    model.result().create("pg17", "PlotGroup3D");
    model.result("pg17").run();
    model.result("pg17").label("pH\uff0c\u4e09\u7ef4");
    model.result("pg17").create("surf1", "Surface");
    model.result("pg17").feature("surf1").set("expr", "tcd.pH");
    model.result("pg17").run();

    model.view("view2").set("showgrid", true);

    model.result("pg17").run();
    model.result().create("pg18", "PlotGroup1D");
    model.result("pg18").run();
    model.result("pg18").label("\u8150\u8680\u901f\u7387");
    model.result("pg18").set("data", "dset3");
    model.result("pg18").create("lngr1", "LineGraph");
    model.result("pg18").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg18").feature("lngr1").set("linewidth", "preference");
    model.result("pg18").feature("lngr1").selection().set(4, 5, 7, 8);
    model.result("pg18").feature("lngr1").set("expr", "tcd.vbtot");
    model.result("pg18").feature("lngr1").set("descr", "\u7535\u6781\u603b\u53d8\u5316\u901f\u5ea6");
    model.result("pg18").feature("lngr1").set("unit", "mm/yr");
    model.result("pg18").feature("lngr1").set("xdata", "expr");
    model.result("pg18").feature("lngr1").set("xdataexpr", "z");
    model.result("pg18").run();
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("fontsize", "9");
    model.result().export("anim1").set("colortheme", "globaltheme");
    model.result().export("anim1").set("customcolor", new double[]{1, 1, 1});
    model.result().export("anim1").set("background", "color");
    model.result().export("anim1").set("gltfincludelines", "on");
    model.result().export("anim1").set("title1d", "on");
    model.result().export("anim1").set("legend1d", "on");
    model.result().export("anim1").set("logo1d", "on");
    model.result().export("anim1").set("options1d", "on");
    model.result().export("anim1").set("title2d", "on");
    model.result().export("anim1").set("legend2d", "on");
    model.result().export("anim1").set("logo2d", "on");
    model.result().export("anim1").set("options2d", "off");
    model.result().export("anim1").set("title3d", "on");
    model.result().export("anim1").set("legend3d", "on");
    model.result().export("anim1").set("logo3d", "on");
    model.result().export("anim1").set("options3d", "off");
    model.result().export("anim1").set("axisorientation", "on");
    model.result().export("anim1").set("grid", "on");
    model.result().export("anim1").set("axes1d", "on");
    model.result().export("anim1").set("axes2d", "on");
    model.result().export("anim1").set("showgrid", "on");
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("plotgroup", "pg16");
    model.result().export("anim1").set("framesel", "all");
    model.result().export("anim1").run();
    model.result("pg17").run();
    model.result().duplicate("pg19", "pg17");
    model.result("pg19").run();
    model.result("pg19").set("looplevel", new int[]{1});
    model.result("pg19").run();
    model.result().remove("pg19");
    model.result("pg4").run();

    model.title("\u70b9\u8680");

    model
         .description("\u70b9\u8680\u662f\u4e00\u79cd\u5c40\u90e8\u8150\u8680\uff0c\u4f1a\u5728\u6700\u521d\u5149\u6ed1\u7684\u91d1\u5c5e\u8868\u9762\u5f62\u6210\u5c40\u90e8\u7a7a\u6d1e\u548c\u51f9\u5751\u3002\n\n\u51f9\u5751\u53ef\u80fd\u7531\u8868\u9762\u7f3a\u9677\uff08\u4f8b\u5982\u6210\u5206\u6216\u5f62\u72b6\u4e0d\u5747\u5300\uff09\u5f15\u8d77\uff0c\u4e5f\u53ef\u80fd\u662f\u673a\u68b0\u635f\u4f24\u5bfc\u81f4\u7684\u5c0f\u5212\u75d5\u6216\u51f9\u75d5\u3002\n\n\u51f9\u5751\u7684\u751f\u957f\u53d6\u51b3\u4e8e\u8bb8\u591a\u56e0\u7d20\uff0c\u4f8b\u5982\u91d1\u5c5e\u7684\u7c7b\u578b\u3001\u76d0\u5ea6\u3001pH \u503c\u548c\u6e29\u5ea6\u3002\n\n\u672c\u6559\u7a0b\u901a\u8fc7\u6a21\u62df\u7535\u6781\u52a8\u529b\u5b66\u3001\u8d28\u91cf\u4f20\u9012\u3001\u7535\u8377\u4f20\u8f93\uff0c\u4ee5\u53ca\u7531\u6b64\u4ea7\u751f\u7684\u51e0\u4f55\u53d8\u5f62\u6765\u7814\u7a76\u51f9\u5751\u6269\u5c55\u7684\u57fa\u672c\u673a\u7406\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("pitting_corrosion.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
