/*
 * pulse_reverse_plating.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:00 by COMSOL 6.3.0.290. */
public class pulse_reverse_plating {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Electrodeposition_Module\\Tutorials_with_Deforming_Geometries");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("cd", "SecondaryCurrentDistribution", "geom1");

    model.component("comp1").common().create("free1", "DeformingDomainDeformedGeometry");
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
    model.study("std1").feature("cdi").setSolveFor("/physics/cd", true);
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("ftplistmethod", "manual");
    model.study("std1").feature("time").set("initialtime", "0");
    model.study("std1").feature("time").set("solnum", "auto");
    model.study("std1").feature("time").set("notsolnum", "auto");
    model.study("std1").feature("time").set("outputmap", new String[]{});
    model.study("std1").feature("time").setSolveFor("/physics/cd", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("S_cell", "500[um]", "\u8ba1\u7b97\u7684\u5355\u5143\u957f\u5ea6");
    model.param().set("H_prot", "40[um]", "\u7a81\u8d77\u9ad8\u5ea6");
    model.param().set("W_prot", "40[um]", "\u7a81\u8d77\u5bbd\u5ea6");
    model.param().set("rho", "8960[kg/m^3]", "Cu \u5bc6\u5ea6");
    model.param().set("M", "0.06355[kg/mol]", "Cu \u6469\u5c14\u8d28\u91cf");
    model.param().set("sigma", "5[S/m]", "\u7535\u89e3\u8d28\u7535\u5bfc\u7387");
    model.param().set("T", "293.15[K]", "\u6e29\u5ea6");
    model.param().set("i0", "1e2[A/m^2]", "\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6");
    model.param().set("alpha_a", "1.5", "\u5bf9\u79f0\u56e0\u5b50");
    model.param().set("alpha_c", "0.5", "\u5bf9\u79f0\u56e0\u5b50");
    model.param().set("i_avg", "5[A/dm^2]", "\u65f6\u5747\u7535\u9540\u7535\u6d41\u5bc6\u5ea6");
    model.param().set("i_rev", "-50[A/dm^2]", "\u53cd\u5411\uff08\u6eb6\u89e3\uff09\u7535\u6d41\u5bc6\u5ea6");
    model.param().set("t_fwd", "0.95", "\u6b63\u5411\u7535\u6d41\u5360\u7a7a\u6bd4");
    model.param().set("t_rev", "1-t_fwd", "\u53cd\u5411\u7535\u6d41\u5360\u7a7a\u6bd4");
    model.param()
         .set("i_fwd", "(i_avg-t_rev*i_rev)/t_fwd", "\u6b63\u5411\uff08\u7535\u9540\uff09\u7535\u6d41\u5bc6\u5ea6");

    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1")
         .set("table", new String[][]{{"0", "H_prot"}, 
         {"-W_prot/2", "0"}, 
         {"-S_cell/2", "0"}, 
         {"-S_cell/2", "S_cell"}, 
         {"S_cell/2", "S_cell"}, 
         {"S_cell/2", "0"}, 
         {"W_prot/2", "0"}});
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("fil1").selection("point").set("pol1", 4);
    model.component("comp1").geom("geom1").feature("fil1").set("radius", "H_prot/10");
    model.component("comp1").geom("geom1").run("fil1");
    model.component("comp1").geom("geom1").create("fil2", "Fillet");
    model.component("comp1").geom("geom1").feature("fil2").selection("point").set("fil1", 3, 6);
    model.component("comp1").geom("geom1").feature("fil2").set("radius", "W_prot/2");
    model.component("comp1").geom("geom1").run("fil2");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("cd").feature("ice1").set("sigmal_mat", "userdef");
    model.component("comp1").physics("cd").feature("ice1")
         .set("sigmal", new String[]{"sigma", "0", "0", "0", "sigma", "0", "0", "0", "sigma"});
    model.component("comp1").physics("cd").create("ic1", "ElectrolyteCurrent", 1);
    model.component("comp1").physics("cd").feature("ic1").selection().set(3);
    model.component("comp1").physics("cd").feature("ic1").set("IonicCurrentType", "AverageCurrentDensity");
    model.component("comp1").physics("cd").feature("ic1").set("Ial", "i_avg");
    model.component("comp1").physics("cd").create("es1", "ElectrodeSurface", 1);
    model.component("comp1").physics("cd").feature("es1").selection().set(2, 4, 5, 6, 8, 9, 10);

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(1);
    model.component("comp1").selection("sel1").label("\u7535\u6781\u8868\u9762");
    model.component("comp1").selection("sel1").set(2, 4, 5, 6, 8, 9, 10);

    model.component("comp1").physics("cd").feature("es1").selection().named("sel1");
    model.component("comp1").physics("cd").feature("es1").setIndex("Species", "s1", 0, 0);
    model.component("comp1").physics("cd").feature("es1").setIndex("rhos", 8960, 0, 0);
    model.component("comp1").physics("cd").feature("es1").setIndex("Ms", 0.06355, 0, 0);
    model.component("comp1").physics("cd").feature("es1").setIndex("Species", "s1", 0, 0);
    model.component("comp1").physics("cd").feature("es1").setIndex("rhos", 8960, 0, 0);
    model.component("comp1").physics("cd").feature("es1").setIndex("Ms", 0.06355, 0, 0);
    model.component("comp1").physics("cd").feature("es1").setIndex("Species", "Cu", 0, 0);
    model.component("comp1").physics("cd").feature("es1").setIndex("rhos", "rho", 0, 0);
    model.component("comp1").physics("cd").feature("es1").setIndex("Ms", "M", 0, 0);
    model.component("comp1").physics("cd").feature("es1")
         .set("SolveForDissolvingDepositingConcentrationVariable", false);
    model.component("comp1").physics("cd").feature("es1").feature("er1").set("nm", 2);
    model.component("comp1").physics("cd").feature("es1").feature("er1").setIndex("Vib", 1, 0, 0);
    model.component("comp1").physics("cd").feature("es1").feature("er1").set("ElectrodeKinetics", "ButlerVolmer");
    model.component("comp1").physics("cd").feature("es1").feature("er1").set("i0", "i0");
    model.component("comp1").physics("cd").feature("es1").feature("er1").set("alphaa", "alpha_a");
    model.component("comp1").physics("cd").feature("es1").feature("er1").set("alphac", "alpha_c");

    model.component("comp1").variable().create("var1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("vn_avg", "(t_fwd*cd.vbtot+t_rev*cd2.vbtot)", "\u65f6\u5747\u53cd\u5411\u8109\u51b2\u7535\u9540\u901f\u5ea6");
    model.component("comp1").variable("var1")
         .set("vn_bnd", "-i_avg/(2*F_const)*M/rho", "\u4e0a\u8fb9\u754c\u7684\u8fb9\u754c\u901f\u5ea6");

    model.component("comp1").sorder("linear");

    model.component("comp1").common("free1").set("smoothingType", "hyperelastic");
    model.component("comp1").common().create("pnmd1", "PrescribedNormalMeshDisplacementDeformedGeometry");
    model.component("comp1").common("pnmd1").selection().set(1, 7);
    model.component("comp1").common().create("pnmv1", "PrescribedNormalMeshVelocityDeformedGeometry");
    model.component("comp1").common("pnmv1").selection().set(3);
    model.component("comp1").common("pnmv1").set("prescribedNormalVelocity", "-vn_bnd");
    model.component("comp1").common().create("pnmv2", "PrescribedNormalMeshVelocityDeformedGeometry");
    model.component("comp1").common("pnmv2").selection().named("sel1");
    model.component("comp1").common("pnmv2").set("prescribedNormalVelocity", "cd.vn");
    model.component("comp1").common("pnmv2").set("embs", true);

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().named("sel1");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hgradactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hgrad", 1.1);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tunit", "h");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 11, 0);
    model.result("pg1").label("\u7535\u89e3\u8d28\u7535\u4f4d (cd)");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"phil"});
    model.result("pg1").create("str1", "Streamline");
    model.result("pg1").feature("str1").set("expr", new String[]{"cd.Ilx", "cd.Ily"});
    model.result("pg1").feature("str1").set("posmethod", "uniform");
    model.result("pg1").feature("str1").set("recover", "pprint");
    model.result("pg1").feature("str1").set("pointtype", "arrow");
    model.result("pg1").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg1").feature("str1").set("color", "gray");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 11, 0);
    model.result("pg2").label("\u7535\u89e3\u8d28\u7535\u6d41\u5bc6\u5ea6 (cd)");
    model.result("pg2").create("str1", "Streamline");
    model.result("pg2").feature("str1").set("expr", new String[]{"cd.Ilx", "cd.Ily"});
    model.result("pg2").feature("str1").set("posmethod", "uniform");
    model.result("pg2").feature("str1").set("recover", "pprint");
    model.result("pg2").feature("str1").set("pointtype", "arrow");
    model.result("pg2").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg2").feature("str1").set("color", "gray");
    model.result("pg2").feature("str1").create("col1", "Color");
    model.result("pg2").feature("str1").feature("col1").set("expr", "root.comp1.cd.IlMag");
    model.result("pg2").create("line1", "Line");
    model.result("pg2").feature("line1").set("expr", new String[]{"abs(cd.itot)"});
    model.result("pg2").feature("line1").set("linetype", "tube");
    model.result("pg2").feature("line1").set("inherittubescale", false);
    model.result("pg2").feature("line1").set("inheritplot", "str1");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").setIndex("looplevel", 11, 0);
    model.result("pg3").label("\u7535\u6781\u7535\u4f4d vs. \u76f8\u90bb\u53c2\u6bd4\u7535\u4f4d (cd)");
    model.result("pg3").create("str1", "Streamline");
    model.result("pg3").feature("str1").set("expr", new String[]{"cd.Ilx", "cd.Ily"});
    model.result("pg3").feature("str1").set("posmethod", "uniform");
    model.result("pg3").feature("str1").set("recover", "pprint");
    model.result("pg3").feature("str1").set("pointtype", "arrow");
    model.result("pg3").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg3").feature("str1").set("color", "gray");
    model.result("pg3").create("line1", "Line");
    model.result("pg3").feature("line1").set("expr", new String[]{"cd.Evsref"});
    model.result("pg3").feature("line1").set("linetype", "tube");
    model.result("pg3").feature("line1").set("inherittubescale", false);
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").setIndex("looplevel", 11, 0);
    model.result("pg4").label("\u53d8\u5f62\u51e0\u4f55");
    model.result("pg4").create("mesh1", "Mesh");
    model.result("pg4").feature("mesh1").set("meshdomain", "surface");
    model.result("pg4").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg4").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg4").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg4").feature("mesh1").create("sel1", "MeshSelection");
    model.result("pg4").feature("mesh1").feature("sel1").selection().set(1);
    model.result("pg4").feature("mesh1").set("qualmeasure", "custom");
    model.result("pg4").feature("mesh1").set("qualexpr", "comp1.material.relVol");
    model.result("pg4").feature("mesh1").set("colorrangeunitinterval", false);
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg3").run();

    model.component("comp1").physics("cd")
         .label("\u4e8c\u6b21\u7535\u6d41\u5206\u5e03 - \u6b63\u5411\uff08\u7535\u9540\uff09");
    model.component("comp1").physics("cd").feature("ic1").set("Ial", "i_fwd");
    model.component("comp1").physics().create("cd2", "SecondaryCurrentDistribution", "geom1");

    model.study("std1").feature("cdi").setSolveFor("/physics/cd2", true);
    model.study("std1").feature("time").setSolveFor("/physics/cd2", true);

    model.component("comp1").physics("cd2")
         .label("\u4e8c\u6b21\u7535\u6d41\u5206\u5e03 - \u53cd\u5411\uff08\u6eb6\u89e3\uff09");
    model.component("comp1").physics("cd2").feature("ice1").set("sigmal_mat", "userdef");
    model.component("comp1").physics("cd2").feature("ice1")
         .set("sigmal", new String[]{"sigma", "0", "0", "0", "sigma", "0", "0", "0", "sigma"});
    model.component("comp1").physics("cd2").create("ic1", "ElectrolyteCurrent", 1);
    model.component("comp1").physics("cd2").feature("ic1").selection().set(3);
    model.component("comp1").physics("cd2").feature("ic1").set("IonicCurrentType", "AverageCurrentDensity");
    model.component("comp1").physics("cd2").feature("ic1").set("Ial", "i_rev");
    model.component("comp1").physics("cd2").create("es1", "ElectrodeSurface", 1);
    model.component("comp1").physics("cd2").feature("es1").selection().named("sel1");
    model.component("comp1").physics("cd2").feature("es1").setIndex("Species", "s1", 0, 0);
    model.component("comp1").physics("cd2").feature("es1").setIndex("rhos", 8960, 0, 0);
    model.component("comp1").physics("cd2").feature("es1").setIndex("Ms", 0.06355, 0, 0);
    model.component("comp1").physics("cd2").feature("es1").setIndex("Species", "s1", 0, 0);
    model.component("comp1").physics("cd2").feature("es1").setIndex("rhos", 8960, 0, 0);
    model.component("comp1").physics("cd2").feature("es1").setIndex("Ms", 0.06355, 0, 0);
    model.component("comp1").physics("cd2").feature("es1").setIndex("Species", "Cu", 0, 0);
    model.component("comp1").physics("cd2").feature("es1").setIndex("rhos", "rho", 0, 0);
    model.component("comp1").physics("cd2").feature("es1").setIndex("Ms", "M", 0, 0);
    model.component("comp1").physics("cd2").feature("es1")
         .set("SolveForDissolvingDepositingConcentrationVariable", false);
    model.component("comp1").physics("cd2").feature("es1").feature("er1").set("nm", 2);
    model.component("comp1").physics("cd2").feature("es1").feature("er1").setIndex("Vib", 1, 0, 0);
    model.component("comp1").physics("cd2").feature("es1").feature("er1").set("ElectrodeKinetics", "ButlerVolmer");
    model.component("comp1").physics("cd2").feature("es1").feature("er1").set("i0", "i0");
    model.component("comp1").physics("cd2").feature("es1").feature("er1").set("alphaa", "alpha_a");
    model.component("comp1").physics("cd2").feature("es1").feature("er1").set("alphac", "alpha_c");

    model.component("comp1").common("pnmv2").set("prescribedNormalVelocity", "-vn_avg");

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "S_cell", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "S_cell", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "t_fwd", 0);
    model.study("std1").feature("param").setIndex("plistarr", "1 0.95 0.85", 0);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol3");
    model.sol("sol3").study("std1");
    model.sol("sol3").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol3");
    model.batch("p1").run("compute");

    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").set("data", "dset3");
    model.result("pg5").setIndex("looplevel", 11, 0);
    model.result("pg5").setIndex("looplevel", 3, 1);
    model.result("pg5").label("\u7535\u89e3\u8d28\u7535\u4f4d (cd) 1");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"phil"});
    model.result("pg5").create("str1", "Streamline");
    model.result("pg5").feature("str1").set("expr", new String[]{"cd.Ilx", "cd.Ily"});
    model.result("pg5").feature("str1").set("posmethod", "uniform");
    model.result("pg5").feature("str1").set("recover", "pprint");
    model.result("pg5").feature("str1").set("pointtype", "arrow");
    model.result("pg5").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg5").feature("str1").set("color", "gray");
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").set("data", "dset3");
    model.result("pg6").setIndex("looplevel", 11, 0);
    model.result("pg6").setIndex("looplevel", 3, 1);
    model.result("pg6").label("\u7535\u89e3\u8d28\u7535\u6d41\u5bc6\u5ea6 (cd) 1");
    model.result("pg6").create("str1", "Streamline");
    model.result("pg6").feature("str1").set("expr", new String[]{"cd.Ilx", "cd.Ily"});
    model.result("pg6").feature("str1").set("posmethod", "uniform");
    model.result("pg6").feature("str1").set("recover", "pprint");
    model.result("pg6").feature("str1").set("pointtype", "arrow");
    model.result("pg6").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg6").feature("str1").set("color", "gray");
    model.result("pg6").feature("str1").create("col1", "Color");
    model.result("pg6").feature("str1").feature("col1").set("expr", "root.comp1.cd.IlMag");
    model.result("pg6").create("line1", "Line");
    model.result("pg6").feature("line1").set("expr", new String[]{"abs(cd.itot)"});
    model.result("pg6").feature("line1").set("linetype", "tube");
    model.result("pg6").feature("line1").set("inherittubescale", false);
    model.result("pg6").feature("line1").set("inheritplot", "str1");
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").set("data", "dset3");
    model.result("pg7").setIndex("looplevel", 11, 0);
    model.result("pg7").setIndex("looplevel", 3, 1);
    model.result("pg7").label("\u7535\u6781\u7535\u4f4d vs. \u76f8\u90bb\u53c2\u6bd4\u7535\u4f4d (cd) 1");
    model.result("pg7").create("str1", "Streamline");
    model.result("pg7").feature("str1").set("expr", new String[]{"cd.Ilx", "cd.Ily"});
    model.result("pg7").feature("str1").set("posmethod", "uniform");
    model.result("pg7").feature("str1").set("recover", "pprint");
    model.result("pg7").feature("str1").set("pointtype", "arrow");
    model.result("pg7").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg7").feature("str1").set("color", "gray");
    model.result("pg7").create("line1", "Line");
    model.result("pg7").feature("line1").set("expr", new String[]{"cd.Evsref"});
    model.result("pg7").feature("line1").set("linetype", "tube");
    model.result("pg7").feature("line1").set("inherittubescale", false);
    model.result().create("pg8", "PlotGroup2D");
    model.result("pg8").set("data", "dset3");
    model.result("pg8").setIndex("looplevel", 11, 0);
    model.result("pg8").setIndex("looplevel", 3, 1);
    model.result("pg8").label("\u7535\u89e3\u8d28\u7535\u4f4d (cd2)");
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("expr", new String[]{"phil2"});
    model.result("pg8").create("str1", "Streamline");
    model.result("pg8").feature("str1").set("expr", new String[]{"cd2.Ilx", "cd2.Ily"});
    model.result("pg8").feature("str1").set("posmethod", "uniform");
    model.result("pg8").feature("str1").set("recover", "pprint");
    model.result("pg8").feature("str1").set("pointtype", "arrow");
    model.result("pg8").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg8").feature("str1").set("color", "gray");
    model.result().create("pg9", "PlotGroup2D");
    model.result("pg9").set("data", "dset3");
    model.result("pg9").setIndex("looplevel", 11, 0);
    model.result("pg9").setIndex("looplevel", 3, 1);
    model.result("pg9").label("\u7535\u89e3\u8d28\u7535\u6d41\u5bc6\u5ea6 (cd2)");
    model.result("pg9").create("str1", "Streamline");
    model.result("pg9").feature("str1").set("expr", new String[]{"cd2.Ilx", "cd2.Ily"});
    model.result("pg9").feature("str1").set("posmethod", "uniform");
    model.result("pg9").feature("str1").set("recover", "pprint");
    model.result("pg9").feature("str1").set("pointtype", "arrow");
    model.result("pg9").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg9").feature("str1").set("color", "gray");
    model.result("pg9").feature("str1").create("col1", "Color");
    model.result("pg9").feature("str1").feature("col1").set("expr", "root.comp1.cd2.IlMag");
    model.result("pg9").create("line1", "Line");
    model.result("pg9").feature("line1").set("expr", new String[]{"abs(cd2.itot)"});
    model.result("pg9").feature("line1").set("linetype", "tube");
    model.result("pg9").feature("line1").set("inherittubescale", false);
    model.result("pg9").feature("line1").set("inheritplot", "str1");
    model.result().create("pg10", "PlotGroup2D");
    model.result("pg10").set("data", "dset3");
    model.result("pg10").setIndex("looplevel", 11, 0);
    model.result("pg10").setIndex("looplevel", 3, 1);
    model.result("pg10").label("\u7535\u6781\u7535\u4f4d vs. \u76f8\u90bb\u53c2\u6bd4\u7535\u4f4d (cd2)");
    model.result("pg10").create("str1", "Streamline");
    model.result("pg10").feature("str1").set("expr", new String[]{"cd2.Ilx", "cd2.Ily"});
    model.result("pg10").feature("str1").set("posmethod", "uniform");
    model.result("pg10").feature("str1").set("recover", "pprint");
    model.result("pg10").feature("str1").set("pointtype", "arrow");
    model.result("pg10").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg10").feature("str1").set("color", "gray");
    model.result("pg10").create("line1", "Line");
    model.result("pg10").feature("line1").set("expr", new String[]{"cd2.Evsref"});
    model.result("pg10").feature("line1").set("linetype", "tube");
    model.result("pg10").feature("line1").set("inherittubescale", false);
    model.result().create("pg11", "PlotGroup2D");
    model.result("pg11").set("data", "dset3");
    model.result("pg11").setIndex("looplevel", 11, 0);
    model.result("pg11").setIndex("looplevel", 3, 1);
    model.result("pg11").label("\u53d8\u5f62\u51e0\u4f55 1");
    model.result("pg11").create("mesh1", "Mesh");
    model.result("pg11").feature("mesh1").set("meshdomain", "surface");
    model.result("pg11").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg11").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg11").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg11").feature("mesh1").create("sel1", "MeshSelection");
    model.result("pg11").feature("mesh1").feature("sel1").selection().set(1);
    model.result("pg11").feature("mesh1").set("qualmeasure", "custom");
    model.result("pg11").feature("mesh1").set("qualexpr", "comp1.material.relVol");
    model.result("pg11").feature("mesh1").set("colorrangeunitinterval", false);
    model.result("pg5").run();
    model.result().create("pg12", "PlotGroup1D");
    model.result("pg12").run();
    model.result("pg12").label("\u7535\u6781\u5f62\u72b6\u6bd4\u8f83");
    model.result("pg12").set("data", "dset3");
    model.result("pg12").setIndex("looplevelinput", "last", 0);
    model.result("pg12").create("lngr1", "LineGraph");
    model.result("pg12").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg12").feature("lngr1").set("linewidth", "preference");
    model.result("pg12").feature("lngr1").selection().named("sel1");
    model.result("pg12").feature("lngr1").set("expr", "y");
    model.result("pg12").feature("lngr1").set("xdata", "expr");
    model.result("pg12").feature("lngr1").set("xdataexpr", "x");
    model.result("pg12").feature("lngr1").set("legend", true);
    model.result("pg12").run();
    model.result("pg12").set("axislimits", true);
    model.result("pg12").set("ymin", 0);
    model.result("pg12").set("ymax", "2e-4");
    model.result("pg12").run();
    model.result("pg12").setIndex("looplevelinput", "first", 1);
    model.result("pg12").setIndex("looplevelinput", "all", 0);
    model.result("pg12").run();
    model.result("pg12").setIndex("looplevelinput", "last", 1);
    model.result("pg12").run();
    model.result("pg1").run();
    model.result().remove("pg1");
    model.result().remove("pg2");
    model.result().remove("pg3");
    model.result().remove("pg4");
    model.result("pg5").run();
    model.result("pg5").label("\u7535\u89e3\u8d28\u7535\u4f4d (cd) - \u6b63\u5411");
    model.result("pg5").run();
    model.result("pg6").run();
    model.result("pg6").label("\u7535\u89e3\u8d28\u7535\u6d41\u5bc6\u5ea6 (cd) - \u6b63\u5411");
    model.result("pg7").run();
    model.result("pg7")
         .label("\u7535\u6781\u7535\u4f4d Vs. \u76f8\u90bb\u53c2\u6bd4\u7535\u4f4d (cd) - \u6b63\u5411");
    model.result("pg8").run();
    model.result("pg8").label("\u7535\u89e3\u8d28\u7535\u4f4d (cd2) - \u53cd\u5411");
    model.result("pg9").run();
    model.result("pg9").label("\u7535\u89e3\u8d28\u7535\u6d41\u5bc6\u5ea6 (cd2) - \u53cd\u5411");
    model.result("pg10").run();
    model.result("pg10")
         .label("\u7535\u6781\u7535\u4f4d Vs. \u76f8\u90bb\u53c2\u6bd4\u7535\u4f4d (cd2) - \u53cd\u5411");

    model.title("\u53cd\u5411\u8109\u51b2\u7535\u9540");

    model
         .description("\u672c\u6559\u7a0b\u63a2\u8ba8\u5982\u4f55\u5728\u94dc\u91d1\u5c5e\u6c89\u79ef\u671f\u95f4\u5c06\u53cd\u5411\u8109\u51b2\u7535\u9540\u7528\u4f5c\u907f\u514d\u4ea7\u751f\u5fae\u5c0f\u7a81\u8d77\u7684\u65e0\u6dfb\u52a0\u5242\u66ff\u4ee3\u65b9\u6848\u3002\u901a\u8fc7\u5339\u914d\u5de5\u827a\u53c2\u6570\uff08\u5305\u62ec\u6b63\u5411\u548c\u53cd\u5411\u8109\u51b2\u957f\u5ea6\uff0c\u5373\u5360\u7a7a\u6bd4\uff09\uff0c\u53ef\u4ee5\u521b\u5efa\u660e\u4eae\u5982\u955c\u7684\u91d1\u5c5e\u8868\u9762\u3002\n\n\u6a21\u578b\u5047\u8bbe\u5728\u6bcf\u4e2a\u8109\u51b2\u671f\u95f4\u5feb\u901f\u5f62\u6210\u51c6\u9759\u6001\u7535\u6d41\u5206\u5e03\uff0c\u4ee5\u4fbf\u5728\u77ac\u6001\u53d8\u5f62\u51e0\u4f55\u4eff\u771f\u4e2d\u4f7f\u7528\u6b63\u5411\u548c\u53cd\u5411\u6c89\u79ef\u901f\u7387\u7684\u5e73\u5747\u503c\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();

    model.label("pulse_reverse_plating.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
