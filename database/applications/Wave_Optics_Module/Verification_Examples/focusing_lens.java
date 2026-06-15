/*
 * focusing_lens.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:42 by COMSOL 6.3.0.290. */
public class focusing_lens {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Wave_Optics_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ewfd", "ElectromagneticWavesFrequencyDomain", "geom1");

    model.study().create("std1");
    model.study("std1").create("wave", "Wavelength");
    model.study("std1").feature("wave").set("ftplistmethod", "manual");
    model.study("std1").feature("wave").set("solnum", "auto");
    model.study("std1").feature("wave").set("notsolnum", "auto");
    model.study("std1").feature("wave").set("outputmap", new String[]{});
    model.study("std1").feature("wave").set("ngenAUX", "1");
    model.study("std1").feature("wave").set("goalngenAUX", "1");
    model.study("std1").feature("wave").set("ngenAUX", "1");
    model.study("std1").feature("wave").set("goalngenAUX", "1");
    model.study("std1").feature("wave").setSolveFor("/physics/ewfd", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("wl", "1[um]", "\u6ce2\u957f");
    model.param().set("k", "2*pi/wl", "\u6ce2\u6570");
    model.param().set("w", "50[um]", "\u4e00\u534a\u5149\u675f\u5927\u5c0f");
    model.param().set("n_lens", "1.5", "\u900f\u955c\u6298\u5c04\u7387");
    model.param().set("W_lens", "15[um]", "\u900f\u955c\u539a\u5ea6");
    model.param().set("H", "200[um]", "\u57df\u9ad8\u5ea6");
    model.param().set("R", "500[um]", "\u900f\u955c\u66f2\u7387\u534a\u5f84");
    model.param().set("W_air", "1100[um]", "\u7a7a\u6c14\u57df\u5927\u5c0f");
    model.param().set("f", "0.94[mm]", "\u7126\u70b9\u4f4d\u7f6e");
    model.param().set("n_AR", "sqrt(n_lens)", "\u589e\u900f\u819c\u7684\u6298\u5c04\u7387");
    model.param().set("d_AR", "wl/4/n_AR", "\u589e\u900f\u819c\u7684\u539a\u5ea6");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"W_lens", "H"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"0", "-H/2"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "R");
    model.component("comp1").geom("geom1").feature("c1").set("pos", new String[]{"R+1[um]", "0"});
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("par1", "Partition");
    model.component("comp1").geom("geom1").feature("par1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("par1").selection("tool").set("c1");
    model.component("comp1").geom("geom1").run("par1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"W_air", "H"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"W_lens", "-H/2"});
    model.component("comp1").geom("geom1").feature("r2").setIndex("layer", "wl", 0);
    model.component("comp1").geom("geom1").feature("r2").set("layerright", true);
    model.component("comp1").geom("geom1").feature("r2").set("layertop", true);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").label("\u7a7a\u6c14");
    model.component("comp1").selection("sel1").set(1, 3, 4, 5, 6, 7, 8);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u900f\u955c");
    model.component("comp1").selection("sel2").set(2);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u8fd1\u573a");
    model.component("comp1").selection("sel3").geom(1);
    model.component("comp1").selection("sel3").set(6, 8, 10);
    model.component("comp1").selection().create("adj1", "Adjacent");
    model.component("comp1").selection("adj1").label("\u4e0e\u7a7a\u6c14\u76f8\u90bb\u7684\u8fb9\u754c");
    model.component("comp1").selection("adj1").set("input", new String[]{"sel1"});
    model.component("comp1").selection().create("adj2", "Adjacent");
    model.component("comp1").selection("adj2").label("\u4e0e\u900f\u955c\u76f8\u90bb\u7684\u8fb9\u754c");
    model.component("comp1").selection("adj2").set("input", new String[]{"sel2"});
    model.component("comp1").selection().create("int1", "Intersection");
    model.component("comp1").selection("int1")
         .label("\u4e0e\u7a7a\u6c14\u548c\u900f\u955c\u76f8\u90bb\u7684\u8fb9\u754c");
    model.component("comp1").selection("int1").set("entitydim", 1);
    model.component("comp1").selection("int1").set("input", new String[]{"adj1", "adj2"});
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").label("PML");
    model.component("comp1").selection("sel4").set(3, 5, 6, 7, 8);
    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").label("\u8f93\u5165\u8fb9\u754c");
    model.component("comp1").selection("sel5").geom(1);
    model.component("comp1").selection("sel5").set(1);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("n", "1");
    model.component("comp1").variable("var1").descr("n", "\u7a7a\u6c14");
    model.component("comp1").variable("var1").selection().geom("geom1", 2);
    model.component("comp1").variable("var1").selection().named("sel1");
    model.component("comp1").variable().create("var2");
    model.component("comp1").variable("var2").set("n", "n_lens", "\u7a7a\u6c14");
    model.component("comp1").variable("var2").descr("n", "\u900f\u955c");
    model.component("comp1").variable("var2").selection().geom("geom1", 2);
    model.component("comp1").variable("var2").selection().named("sel2");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop1").selection().named("sel3");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u7a7a\u6c14");
    model.component("comp1").material("mat1").selection().named("sel1");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").set("n", new String[]{"1"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u73bb\u7483");
    model.component("comp1").material("mat2").selection().named("sel2");
    model.component("comp1").material("mat2").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").set("n", new String[]{"n_lens"});

    model.component("comp1").physics("ewfd").selection().set(1, 2);
    model.component("comp1").physics("ewfd").prop("components").set("components", "outofplane");
    model.component("comp1").physics("ewfd").create("sctr1", "Scattering", 1);
    model.component("comp1").physics("ewfd").feature("sctr1").selection().named("sel5");
    model.component("comp1").physics("ewfd").feature("sctr1").set("IncidentField", "EField");
    model.component("comp1").physics("ewfd").feature("sctr1")
         .set("E0i", new String[]{"0", "0", "1[V/m]*(abs(y)<w)"});
    model.component("comp1").physics("ewfd").create("sctr2", "Scattering", 1);
    model.component("comp1").physics("ewfd").feature("sctr2").selection().named("sel3");
    model.component("comp1").physics("ewfd").create("trans1", "TransitionBoundaryCondition", 1);
    model.component("comp1").physics("ewfd").feature("trans1").selection().named("adj2");
    model.component("comp1").physics("ewfd").feature("trans1").set("d", "d_AR");

    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").label("AR \u6d82\u5c42");
    model.component("comp1").material("mat3").selection().geom("geom1", 1);
    model.component("comp1").material("mat3").selection().named("adj2");
    model.component("comp1").material("mat3").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").set("n", new String[]{"n_AR"});

    model.study("std1").feature("wave").set("plist", "wl");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u7535\u573a (ewfd)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").run();

    model.component("comp1").view().create("view2", "geom1");
    model.component("comp1").view("view2").axis().set("viewscaletype", "manual");
    model.component("comp1").view("view2").axis().set("xscale", 2);

    model.result("pg1").run();
    model.result("pg1").set("view", "view2");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "ewfd.Ez");
    model.result("pg1").feature("surf1").set("colortable", "WaveLight");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u8fd1\u573a");
    model.result("pg2").create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg2").feature("lngr1").set("linewidth", "preference");
    model.result("pg2").feature("lngr1").selection().named("sel3");
    model.result("pg2").feature("lngr1").set("expr", "ewfd.Ez");
    model.result("pg2").feature("lngr1").set("xdata", "expr");
    model.result("pg2").feature("lngr1").set("xdataexpr", "y");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").set("titletype", "none");
    model.result().dataset().create("grid1", "Grid1D");
    model.result().dataset("grid1").set("source", "data");
    model.result().dataset("grid1").set("par1", "u");
    model.result().dataset("grid1").set("parmin1", "-H/2");
    model.result().dataset("grid1").set("parmax1", "H/2");
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u7126\u5e73\u9762");
    model.result("pg3").set("titletype", "none");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "\u7535\u573a\u6a21 (V/m)");
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg3").feature("lngr1").set("linewidth", "preference");
    model.result("pg3").feature("lngr1").set("data", "grid1");
    model.result("pg3").feature("lngr1")
         .set("expr", "3/2.5*sqrt(1/wl/f*intop1(ewfd.Ez*exp(-i*k*y^2/(2*f))*exp(i*2*pi*dest(u)*y/(wl*f)))*conj(intop1(ewfd.Ez*exp(-i*k*y^2/(2*f))*exp(i*2*pi*dest(u)*y/(wl*f)))))");
    model.result("pg3").feature("lngr1").set("legend", true);
    model.result("pg3").feature("lngr1").set("legendmethod", "manual");
    model.result("pg3").feature("lngr1").setIndex("legends", "FD+\u83f2\u6d85\u5c14\u516c\u5f0f", 0);
    model.result("pg3").run();

    model.component("comp1").physics().create("ewbe", "ElectromagneticWavesBeamEnvelopes", "geom1");

    model.study("std1").feature("wave").setSolveFor("/physics/ewbe", true);

    model.component("comp1").coordSystem().create("pml1", "PML");
    model.component("comp1").coordSystem("pml1").selection().named("sel4");

    model.component("comp1").physics("ewbe").prop("components").set("components", "outofplane");
    model.component("comp1").physics("ewbe").prop("WaveVector").set("dirCount", "UniDirectionality");
    model.component("comp1").physics("ewbe").prop("WaveVector").set("k1", new String[]{"k*n", "0", "0"});
    model.component("comp1").physics("ewbe").create("mbc1", "MatchedBoundaryCondition", 1);
    model.component("comp1").physics("ewbe").feature("mbc1").selection().named("sel5");
    model.component("comp1").physics("ewbe").feature("mbc1").set("IncidentField", "EField");
    model.component("comp1").physics("ewbe").feature("mbc1").set("E0i", new String[]{"0", "0", "1[V/m]*(abs(y)<w)"});
    model.component("comp1").physics("ewbe").create("trans1", "TransitionBoundaryCondition", 1);
    model.component("comp1").physics("ewbe").feature("trans1").selection().named("adj2");
    model.component("comp1").physics("ewbe").feature("trans1").set("d", "d_AR");

    model.component("comp1").coordSystem("pml1").set("wavelengthSource", "ewbe");

    model.component("comp1").mesh("mesh1").contribute("physics/ewbe", false);
    model.component("comp1").mesh().create("mesh2");
    model.component("comp1").mesh("mesh2").create("map1", "Map");
    model.component("comp1").mesh("mesh2").feature("map1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh2").feature("map1").selection().named("sel4");
    model.component("comp1").mesh("mesh2").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh2").feature("map1").feature("dis1").selection().set(19, 20, 22);
    model.component("comp1").mesh("mesh2").feature("map1").feature("dis1").set("numelem", 10);
    model.component("comp1").mesh("mesh2").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh2").feature("size").set("hmax", "wl");
    model.component("comp1").mesh("mesh2").create("ftri1", "FreeTri");

    model.study().create("std2");
    model.study("std2").create("wave", "Wavelength");
    model.study("std2").feature("wave").set("plotgroup", "Default");
    model.study("std2").feature("wave").set("ftplistmethod", "manual");
    model.study("std2").feature("wave").set("solnum", "auto");
    model.study("std2").feature("wave").set("notsolnum", "auto");
    model.study("std2").feature("wave").set("outputmap", new String[]{});
    model.study("std2").feature("wave").set("ngenAUX", "1");
    model.study("std2").feature("wave").set("goalngenAUX", "1");
    model.study("std2").feature("wave").set("ngenAUX", "1");
    model.study("std2").feature("wave").set("goalngenAUX", "1");
    model.study("std2").feature("wave").setSolveFor("/physics/ewfd", false);
    model.study("std2").feature("wave").setSolveFor("/physics/ewbe", true);
    model.study("std2").feature("wave").set("plist", "wl");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u7535\u573a (ewbe)");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").setIndex("looplevel", 1, 0);
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").label("\u7535\u573a");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("expr", "ewbe.normE");
    model.result("pg4").feature("surf1").set("smooth", "internal");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result("pg4").run();

    model.component("comp1").view().create("view3", "geom1");
    model.component("comp1").view("view3").axis().set("viewscaletype", "manual");
    model.component("comp1").view("view3").axis().set("yscale", 2);

    model.result("pg4").run();
    model.result("pg4").set("view", "view3");
    model.result().dataset().create("cln1", "CutLine2D");
    model.result().dataset("cln1").set("data", "dset2");
    model.result().dataset("cln1").setIndex("genpoints", "f", 0, 0);
    model.result().dataset("cln1").setIndex("genpoints", "-H", 0, 1);
    model.result().dataset("cln1").setIndex("genpoints", "f", 1, 0);
    model.result().dataset("cln1").setIndex("genpoints", "H", 1, 1);
    model.result("pg3").run();
    model.result("pg3").create("lngr2", "LineGraph");
    model.result("pg3").feature("lngr2").set("markerpos", "datapoints");
    model.result("pg3").feature("lngr2").set("linewidth", "preference");
    model.result("pg3").feature("lngr2").set("data", "cln1");
    model.result("pg3").feature("lngr2").set("expr", "ewbe.normE");
    model.result("pg3").feature("lngr2").set("legend", true);
    model.result("pg3").feature("lngr2").set("legendmethod", "manual");
    model.result("pg3").feature("lngr2").setIndex("legends", "\u6ce2\u675f\u5305\u7edc", 0);
    model.result("pg3").run();
    model.result().dataset().create("cln2", "CutLine2D");
    model.result().dataset("cln2").set("data", "dset2");
    model.result().dataset("cln2").setIndex("genpoints", "W_lens+W_air-wl", 1, 0);
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u4f20\u64ad\u65b9\u5411");
    model.result("pg5").set("data", "cln2");
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("xlabel", "\u4f20\u64ad\u957f\u5ea6 (mm)");
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg5").feature("lngr1").set("linewidth", "preference");
    model.result("pg5").feature("lngr1").set("expr", "ewbe.normE");
    model.result("pg5").run();

    model.study("std1").feature("wave").setSolveFor("/physics/ewbe", false);

    model.result("pg4").run();

    model.title("\u805a\u7126\u900f\u955c");

    model
         .description("\u7531\u4e8e\u5728\u6a21\u62df\u6beb\u7c73\u7ea7\u5149\u5b66\u900f\u955c\u65f6\u9700\u8981\u5927\u91cf\u7684\u6709\u9650\u5143\u7f51\u683c\u5355\u5143\uff0c\u56e0\u6b64\u65e0\u6cd5\u5728\u6807\u51c6\u5de5\u4f5c\u7ad9\u4e0a\u4f7f\u7528\u201c\u7535\u78c1\u6ce2\uff0c\u9891\u57df\u201d\u63a5\u53e3\u8f7b\u677e\u5206\u6790\u6b64\u7c7b\u900f\u955c\u3002\n\n\u672c\u6a21\u578b\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u201c\u7535\u78c1\u6ce2\uff0c\u6ce2\u675f\u5305\u7edc\u201d\u63a5\u53e3\u6765\u5206\u6790\u7126\u8ddd\u4e3a 1 mm \u7684\u5e73\u51f8\u900f\u955c\uff0c\u5e76\u5c06\u4eff\u771f\u7ed3\u679c\u4e0e\u83f2\u6d85\u5c14\u884d\u5c04\u516c\u5f0f\u8fdb\u884c\u6bd4\u8f83\u3002\u4e24\u4e2a\u7ed3\u679c\u5448\u73b0\u51fa\u9ad8\u5ea6\u7684\u4e00\u81f4\u6027\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("focusing_lens.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
