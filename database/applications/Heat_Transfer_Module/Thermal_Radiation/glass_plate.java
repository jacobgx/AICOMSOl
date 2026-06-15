/*
 * glass_plate.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:31 by COMSOL 6.3.0.290. */
public class glass_plate {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Heat_Transfer_Module\\Thermal_Radiation");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("ht", "HeatTransfer", "geom1");
    model.component("comp1").physics().create("rpm", "ParticipatingMediaRadiation", "geom1");

    model.component("comp1").multiphysics().create("htrpm1", "HeatTransferWithRadiationInParticipatingMedia", 3);
    model.component("comp1").multiphysics("htrpm1").set("Heat_physics", "ht");
    model.component("comp1").multiphysics("htrpm1").set("RPM_physics", "rpm");
    model.component("comp1").multiphysics("htrpm1").selection().all();

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/ht", true);
    model.study("std1").feature("time").setSolveFor("/physics/rpm", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/htrpm1", true);

    model.param().set("nr", "1.45");
    model.param().descr("nr", "\u6298\u5c04\u7387");
    model.param().set("T0", "600[degC]");
    model.param().descr("T0", "\u521d\u59cb\u6e29\u5ea6");
    model.param().set("T_amb", "20[degC]");
    model.param().descr("T_amb", "\u73af\u5883\u6e29\u5ea6");
    model.param().set("k", "5[1/m]");
    model.param().descr("k", "\u5438\u6536\u7cfb\u6570");

    model.component("comp1").geom("geom1").lengthUnit("cm");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", 5);
    model.component("comp1").geom("geom1").feature("cyl1").set("h", 1.5);
    model.component("comp1").geom("geom1").run("fin");

    model.material().create("mat1", "Common", "");
    model.material("mat1").label("\u73bb\u7483");
    model.component("comp1").material().create("matlnk1", "Link");
    model.component("comp1").material().create("matlnk2", "Link");
    model.component("comp1").material("matlnk2").selection().geom("geom1", 2);
    model.component("comp1").material("matlnk2").selection().all();
    model.material("mat1").propertyGroup("def").set("thermalconductivity", new String[]{"1.2[W/(m*K)]"});
    model.material("mat1").propertyGroup("def").set("density", new String[]{"2200[kg/m^3]"});
    model.material("mat1").propertyGroup("def").set("heatcapacity", new String[]{"850[J/(kg*K)]"});
    model.material("mat1").propertyGroup("def").set("absorption", new String[]{"k"});
    model.material("mat1").propertyGroup("def").set("scattering", new String[]{"0[1/m]"});
    model.material("mat1").propertyGroup("def").set("emissivity", new String[]{"1"});

    model.component("comp1").physics("rpm").prop("ParticipatingMediaSettingsProperty").set("PerfIndex", 0.6);
    model.component("comp1").physics("rpm").prop("ParticipatingMediaSettingsProperty").set("nrad", "nr");
    model.component("comp1").physics("ht").feature("init1").set("Tinit", "T0");
    model.component("comp1").physics("ht").create("sar1", "SurfaceToAmbientRadiation", 2);
    model.component("comp1").physics("ht").feature("sar1").selection().all();
    model.component("comp1").physics("ht").feature("sar1").set("Tamb", "T_amb");

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(4);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature().create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hauto", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().set(4, 5, 8, 11);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature().create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hauto", 4);
    model.component("comp1").mesh("mesh1").run("ftri1");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 8);
    model.component("comp1").mesh("mesh1").run("swe1");

    model.study("std1").label("\u7814\u7a76 1\uff1aDOM");
    model.study("std1").feature("time").set("tlist", 10);
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "nr", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "", 0);
    model.study("std1").feature("param").setIndex("pname", "nr", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "", 0);
    model.study("std1").feature("param").setIndex("pname", "k", 0);
    model.study("std1").feature("param").setIndex("plistarr", "5 70 120", 0);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u6e29\u5ea6 (ht)");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").setIndex("looplevel", 2, 0);
    model.result("pg1").setIndex("looplevel", 3, 1);
    model.result("pg1").feature().create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("showsolutionparams", "on");
    model.result("pg1").feature("vol1").set("solutionparams", "parent");
    model.result("pg1").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg1").feature("vol1").set("smooth", "internal");
    model.result("pg1").feature("vol1").set("showsolutionparams", "on");
    model.result("pg1").feature("vol1").set("data", "parent");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u5165\u5c04\u8f90\u5c04 (rpm)");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").setIndex("looplevel", 2, 0);
    model.result("pg2").setIndex("looplevel", 3, 1);
    model.result("pg2").feature().create("mslc1", "Multislice");
    model.result("pg2").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg2").feature("mslc1").set("expr", "rpm.G");
    model.result("pg2").feature("mslc1").set("smooth", "internal");
    model.result("pg2").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg2").feature("mslc1").set("data", "parent");
    model.result("pg1").run();

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 3);
    model.component("comp2").geom("geom2").geomRep("comsol");

    model.component("comp2").mesh().create("mesh2");
    model.component("comp2").mesh("mesh2").contribute("geom/detail", true);

    model.component("comp2").geom("geom2").lengthUnit("cm");
    model.component("comp2").geom("geom2").run();

    model.component("comp2").mesh("mesh2").create("imp1", "Import");
    model.component("comp2").mesh("mesh2").feature("imp1").set("source", "sequence");
    model.component("comp2").mesh("mesh2").feature("imp1").set("buildsource", true);
    model.component("comp2").mesh("mesh2").feature("imp1").importData();
    model.component("comp2").mesh("mesh2").run();

    model.component("comp2").material().create("matlnk3", "Link");
    model.component("comp2").material().create("matlnk4", "Link");
    model.component("comp2").material("matlnk4").selection().geom("geom2", 2);
    model.component("comp2").material("matlnk4").selection().all();

    model.component("comp2").physics().copy("ht2", "ht");
    model.component("comp2").physics().copy("rpm2", "rpm");

    model.component("comp2").multiphysics().create("htrpm2", "HeatTransferWithRadiationInParticipatingMedia", 3);

    model.component("comp2").physics("rpm2").prop("ParticipatingMediaSettingsProperty")
         .set("RadiationDiscretizationMethod", "P1Approximation");

    model.study().create("std2");
    model.study("std2").label("\u7814\u7a76 2\uff1aP1");
    model.study("std2").feature().copy("param", "std1/param");
    model.study("std2").feature().copy("time", "std1/time");
    model.study("std2").feature("time").setSolveFor("/physics/ht", false);
    model.study("std2").feature("time").setSolveFor("/physics/rpm", false);
    model.study("std2").feature("time").setSolveFor("/multiphysics/htrpm1", false);
    model.study("std1").feature("time").setSolveFor("/physics/ht2", false);
    model.study("std1").feature("time").setSolveFor("/physics/rpm2", false);
    model.study("std1").feature("time").setSolveFor("/multiphysics/htrpm2", false);
    model.study("std2").createAutoSequences("all");

    model.sol().create("sol7");
    model.sol("sol7").study("std2");
    model.sol("sol7").label("\u53c2\u6570\u5316\u89e3 2");

    model.batch("p2").feature("so1").set("psol", "sol7");
    model.batch("p2").run("compute");

    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u6e29\u5ea6 (ht2)");
    model.result("pg3").set("data", "dset6");
    model.result("pg3").setIndex("looplevel", 2, 0);
    model.result("pg3").setIndex("looplevel", 3, 1);
    model.result("pg3").feature().create("vol1", "Volume");
    model.result("pg3").feature("vol1").set("showsolutionparams", "on");
    model.result("pg3").feature("vol1").set("solutionparams", "parent");
    model.result("pg3").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg3").feature("vol1").set("smooth", "internal");
    model.result("pg3").feature("vol1").set("showsolutionparams", "on");
    model.result("pg3").feature("vol1").set("data", "parent");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u5165\u5c04\u8f90\u5c04 (rpm2)");
    model.result("pg4").set("data", "dset6");
    model.result("pg4").setIndex("looplevel", 2, 0);
    model.result("pg4").setIndex("looplevel", 3, 1);
    model.result("pg4").feature().create("mslc1", "Multislice");
    model.result("pg4").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg4").feature("mslc1").set("expr", "rpm2.G");
    model.result("pg4").feature("mslc1").set("smooth", "internal");
    model.result("pg4").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg4").feature("mslc1").set("data", "parent");
    model.result("pg3").run();

    model.component().create("comp3", true);

    model.component("comp3").geom().create("geom3", 3);
    model.component("comp3").geom("geom3").geomRep("comsol");

    model.component("comp3").mesh().create("mesh3");
    model.component("comp3").mesh("mesh3").contribute("geom/detail", true);

    model.component("comp3").geom("geom3").lengthUnit("cm");
    model.component("comp3").geom("geom3").run();

    model.component("comp3").mesh("mesh3").create("imp1", "Import");
    model.component("comp3").mesh("mesh3").feature("imp1").set("source", "sequence");
    model.component("comp3").mesh("mesh3").feature("imp1").set("buildsource", true);
    model.component("comp3").mesh("mesh3").feature("imp1").importData();
    model.component("comp3").mesh("mesh3").run();

    model.component("comp3").material().create("matlnk5", "Link");
    model.component("comp3").material().create("matlnk6", "Link");
    model.component("comp3").material("matlnk6").selection().geom("geom3", 2);
    model.component("comp3").material("matlnk6").selection().all();

    model.component("comp3").physics().copy("ht3", "ht2");
    model.component("comp3").physics("ht3").feature("solid1")
         .create("otpm1", "OpticallyThickParticipatingMedium", 3);
    model.component("comp3").physics("ht3").feature("solid1").feature("otpm1").set("nrad", "nr");

    model.study().create("std3");
    model.study("std3").label("\u7814\u7a76 3\uff1aRosseland");
    model.study("std3").feature().copy("param", "std2/param");
    model.study("std3").feature().copy("time", "std2/time");
    model.study("std3").feature("time").setSolveFor("/physics/ht2", false);
    model.study("std3").feature("time").setSolveFor("/physics/rpm2", false);
    model.study("std3").feature("time").setSolveFor("/multiphysics/htrpm2", false);
    model.study("std1").feature("time").setSolveFor("/physics/ht3", false);
    model.study("std2").feature("time").setSolveFor("/physics/ht3", false);
    model.study("std3").createAutoSequences("all");

    model.sol().create("sol12");
    model.sol("sol12").study("std3");
    model.sol("sol12").label("\u53c2\u6570\u5316\u89e3 3");

    model.batch("p3").feature("so1").set("psol", "sol12");
    model.batch("p3").run("compute");

    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("\u6e29\u5ea6 (ht3)");
    model.result("pg5").set("data", "dset12");
    model.result("pg5").setIndex("looplevel", 2, 0);
    model.result("pg5").setIndex("looplevel", 3, 1);
    model.result("pg5").feature().create("vol1", "Volume");
    model.result("pg5").feature("vol1").set("showsolutionparams", "on");
    model.result("pg5").feature("vol1").set("solutionparams", "parent");
    model.result("pg5").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg5").feature("vol1").set("smooth", "internal");
    model.result("pg5").feature("vol1").set("showsolutionparams", "on");
    model.result("pg5").feature("vol1").set("data", "parent");
    model.result("pg5").run();
    model.result("pg4").run();
    model.result().dataset().create("cln1", "CutLine3D");
    model.result().dataset("cln1").set("data", "dset2");
    model.result().dataset("cln1").setIndex("genpoints", 0, 1, 0);
    model.result().dataset("cln1").setIndex("genpoints", 1.5, 1, 2);
    model.result().dataset().duplicate("cln2", "cln1");
    model.result().dataset("cln2").set("data", "dset6");
    model.result().dataset().duplicate("cln3", "cln2");
    model.result().dataset("cln3").set("data", "dset12");
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("k = 5 \u65f6\u7684\u4e2d\u5fc3\u7ebf\u6e29\u5ea6");
    model.result("pg6").set("titletype", "manual");
    model.result("pg6").set("title", "\u4e2d\u5fc3\u7ebf\u6e29\u5ea6");
    model.result("pg6").create("lngr1", "LineGraph");
    model.result("pg6").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg6").feature("lngr1").set("linewidth", "preference");
    model.result("pg6").feature("lngr1").set("data", "cln1");
    model.result("pg6").feature("lngr1").setIndex("looplevelinput", "first", 1);
    model.result("pg6").feature("lngr1").setIndex("looplevelinput", "last", 0);
    model.result("pg6").feature("lngr1").set("legendmethod", "manual");
    model.result("pg6").feature("lngr1").set("legend", true);
    model.result("pg6").feature("lngr1").setIndex("legends", "DOM", 0);
    model.result("pg6").run();
    model.result("pg6").feature().duplicate("lngr2", "lngr1");
    model.result("pg6").run();
    model.result("pg6").feature("lngr2").set("data", "cln2");
    model.result("pg6").feature("lngr2").setIndex("legends", "P1", 0);
    model.result("pg6").feature().duplicate("lngr3", "lngr2");
    model.result("pg6").run();
    model.result("pg6").feature("lngr3").set("data", "cln3");
    model.result("pg6").feature("lngr3").setIndex("legends", "Rosseland", 0);
    model.result("pg6").run();
    model.result("pg6").run();
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").run();
    model.result("pg7").label("k = 120 \u65f6\u7684\u4e2d\u5fc3\u7ebf\u6e29\u5ea6");
    model.result("pg7").run();
    model.result("pg7").feature("lngr1").setIndex("looplevelinput", "last", 1);
    model.result("pg7").run();
    model.result("pg7").feature("lngr2").setIndex("looplevelinput", "last", 1);
    model.result("pg7").run();
    model.result("pg7").feature("lngr3").setIndex("looplevelinput", "last", 1);
    model.result("pg7").run();

    model.title("\u73bb\u7483\u677f\u8f90\u5c04\u51b7\u5374");

    model
         .description("\u73bb\u7483\u7194\u4f53\u901a\u8fc7\u8f90\u5c04\u8fdb\u884c\u51b7\u5374\u6210\u578b\uff0c\u8f90\u5c04\u4eff\u771f\u4f7f\u7528 COMSOL Multiphysics \u4e2d\u63d0\u4f9b\u7684\u4e09\u4e2a\u53c2\u4e0e\u4ecb\u8d28\u4e2d\u7684\u8f90\u5c04\u6a21\u578b\u6765\u6267\u884c\uff0c\u4ee5\u6bd4\u8f83\u7cbe\u5ea6\u548c\u8ba1\u7b97\u6210\u672c\u3002");

    model.component("comp1").mesh("mesh1").clearMesh();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol8").clearSolutionData();
    model.sol("sol9").clearSolutionData();
    model.sol("sol10").clearSolutionData();
    model.sol("sol11").clearSolutionData();
    model.sol("sol12").clearSolutionData();
    model.sol("sol13").clearSolutionData();
    model.sol("sol14").clearSolutionData();
    model.sol("sol15").clearSolutionData();

    model.label("glass_plate.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
