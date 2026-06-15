/*
 * solar_dish_receiver_designer.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:15 by COMSOL 6.3.0.290. */
public class solar_dish_receiver_designer {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Ray_Optics_Module\\Applications");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("gop", "GeometricalOptics", "geom1");

    model.study().create("std1");
    model.study("std1").create("rtrac", "RayTracing");
    model.study("std1").feature("rtrac").setSolveFor("/physics/gop", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("f", "3[m]", "Focal length");
    model.param().set("phi", "45[deg]", "Rim angle");
    model.param().set("d", "4*f*(csc(phi)-cot(phi))", "Dish diameter");
    model.param().set("A", "pi*d^2/4", "Dish projected surface area");
    model.param().set("psim", "4.65[mrad]", "Maximum solar disc angle");
    model.param().set("sig", "1.75[mrad]", "Surface slope error");
    model.param().set("I0", "1[kW/m^2]", "Solar irradiance");
    model.param().set("cavity", "5", "Cavity geometry");
    model.param().set("cy_r", "0.100[m]", "Radius, cylindrical cavity");
    model.param().set("cy_h", "0.200[m]", "Height, cylindrical cavity");
    model.param().set("do_r", "0.100[m]", "Radius, dome cavity");
    model.param().set("do_h", "0.2200[m]", "Height, dome cavity");
    model.param().set("he_rb1", "0.1[m]", "Bottom radius bottom cone, heteroconical cavity");
    model.param().set("he_h1", "0.15[m]", "Height bottom cone, heteroconical cavity");
    model.param().set("he_rt1", "0.15[m]", "Top radius bottom cone, heteroconical cavity");
    model.param().set("he_ch", "0.02[m]", "Cylinder height, heteroconical cavity");
    model.param().set("he_h2", "0.2[m]", "Height top cone, heteroconical cavity");
    model.param().set("he_rt2", "0.07[m]", "Top radius top cone, heteroconical cavity");
    model.param().set("el_a", "0.1[m]", "Semiaxis-a, elliptical cavity");
    model.param().set("el_b", "0.1[m]", "Semiaxis-b, elliptical cavity");
    model.param().set("el_c", "0.2[m]", "Semiaxis-c, elliptical cavity");
    model.param().set("el_h", "0.3[m]", "Height, elliptical cavity");
    model.param().set("el_aa", "sqrt(el_a^2-(el_a/el_c)^2*(el_h-el_c)^2)", "Aperture semiaxis-a, elliptical cavity");
    model.param().set("el_ba", "sqrt(el_b^2-(el_b/el_c)^2*(el_h-el_c)^2)", "Aperture semiaxis-b, elliptical cavity");
    model.param().set("sp_r", "0.15[m]", "Radius, spherical cavity");
    model.param().set("sp_h", "0.18[m]", "Height, spherical cavity");
    model.param().set("sp_ra", "sqrt(2*sp_r*(sp_h-sp_r)-(sp_h-sp_r)^2)", "Aperture radius, spherical cavity");
    model.param().set("co_rt", "0.05[m]", "Top radius, conical cavity");
    model.param().set("co_rb", "0.1[m]", "Bottom radius, conical cavity");
    model.param().set("co_h", "0.25[m]", "Height, conical cavity");
    model.param()
         .set("rfp", "if(cavity==0,cy_r,if(cavity==1,do_r,if(cavity==2,he_rb1,if(cavity==3,min(el_a,el_b),if(cavity==4,sp_ra,co_rt)))))", "Minimum focal plane radius");
    model.param()
         .set("rwall", "if(cavity==0,cy_r,if(cavity==1,do_r,if(cavity==2,max(he_rt2,max(he_rb1,he_rt1)),if(cavity==3,max(el_a,el_b),if(cavity==4,sp_r,max(co_rt,co_rb))))))", "Maximum cavity radius at the walls");
    model.param()
         .set("hwall", "if(cavity==0,cy_h,if(cavity==1,do_h,if(cavity==2,he_h1+he_ch+he_h2,if(cavity==3,el_h,if(cavity==4,sp_h,co_h)))))", "Cavity height");
    model.param().set("minmesh", "rfp/200", "Minimum mesh size, focal plane");
    model.param().set("maxmesh", "minmesh*10", "Maximum mesh size, focal plane");
    model.param().set("Nrel", "1E3", "Number of rays per release");
    model.param().set("alpha_collector", "0.1", "Solar collector absorption coefficient");
    model.param().set("alpha_cavity", "1.0", "Cavity walls absorption coefficient");

    model.geom()
         .load(new String[]{"part1"}, "Ray_Optics_Module\\3D\\Mirrors\\paraboloidal_reflector_shell_3d.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "d1", "d");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "d2", 0);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "F", "f");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "nix", 0);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "niz", -1);
    model.component("comp1").geom("geom1").feature("pi1").set("displ", new String[]{"0", "0", "-f"});
    model.component("comp1").geom("geom1").run("pi1");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").create("if1", "If");
    model.component("comp1").geom("geom1").feature().createAfter("endif1", "EndIf", "if1");
    model.component("comp1").geom("geom1").feature("if1").label("Is cylindrical");
    model.component("comp1").geom("geom1").feature("if1").set("condition", "cavity==0");
    model.component("comp1").geom("geom1").run("if1");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "cy_r");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "cy_h");
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("elseif1", "ElseIf");
    model.component("comp1").geom("geom1").feature("elseif1").label("Is dome");
    model.component("comp1").geom("geom1").feature("elseif1").set("condition", "cavity==1");
    model.component("comp1").geom("geom1").run("elseif1");
    model.component("comp1").geom("geom1").create("cyl2", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl2").set("r", "do_r");
    model.component("comp1").geom("geom1").feature("cyl2").set("h", "do_h-do_r");
    model.component("comp1").geom("geom1").run("cyl2");
    model.component("comp1").geom("geom1").create("sph1", "Sphere");
    model.component("comp1").geom("geom1").feature("sph1").set("r", "do_r");
    model.component("comp1").geom("geom1").feature("sph1").set("pos", new String[]{"0", "0", "do_h-do_r"});
    model.component("comp1").geom("geom1").run("sph1");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("cyl2", "sph1");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("elseif2", "ElseIf");
    model.component("comp1").geom("geom1").feature("elseif2").label("Is heteroconical");
    model.component("comp1").geom("geom1").feature("elseif2").set("condition", "cavity==2");
    model.component("comp1").geom("geom1").run("elseif2");
    model.component("comp1").geom("geom1").create("cone1", "Cone");
    model.component("comp1").geom("geom1").feature("cone1").set("specifytop", "radius");
    model.component("comp1").geom("geom1").feature("cone1").set("r", "he_rb1");
    model.component("comp1").geom("geom1").feature("cone1").set("h", "he_h1");
    model.component("comp1").geom("geom1").feature("cone1").set("rtop", "he_rt1");
    model.component("comp1").geom("geom1").run("cone1");
    model.component("comp1").geom("geom1").create("cyl3", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl3").set("r", "he_rt1");
    model.component("comp1").geom("geom1").feature("cyl3").set("h", "he_ch");
    model.component("comp1").geom("geom1").feature("cyl3").set("pos", new String[]{"0", "0", "he_h1"});
    model.component("comp1").geom("geom1").run("cyl3");
    model.component("comp1").geom("geom1").create("cone2", "Cone");
    model.component("comp1").geom("geom1").feature("cone2").set("specifytop", "radius");
    model.component("comp1").geom("geom1").feature("cone2").set("r", "he_rt1");
    model.component("comp1").geom("geom1").feature("cone2").set("h", "he_h2");
    model.component("comp1").geom("geom1").feature("cone2").set("rtop", "he_rt2");
    model.component("comp1").geom("geom1").feature("cone2").set("pos", new String[]{"0", "0", "he_h1+he_ch"});
    model.component("comp1").geom("geom1").run("cone2");
    model.component("comp1").geom("geom1").create("elseif3", "ElseIf");
    model.component("comp1").geom("geom1").feature("elseif3").label("Is elliptical");
    model.component("comp1").geom("geom1").feature("elseif3").set("condition", "cavity==3");
    model.component("comp1").geom("geom1").run("elseif3");
    model.component("comp1").geom("geom1").create("elp1", "Ellipsoid");
    model.component("comp1").geom("geom1").feature("elp1").set("semiaxes", new String[]{"el_a", "el_b", "el_c"});
    model.component("comp1").geom("geom1").feature("elp1").set("pos", new String[]{"0", "0", "2*el_c-el_h"});
    model.component("comp1").geom("geom1").run("elp1");
    model.component("comp1").geom("geom1").create("pch1", "ParameterCheck");
    model.component("comp1").geom("geom1").feature("pch1").set("condition", "(el_h>2*el_c)||(el_h<el_c)");
    model.component("comp1").geom("geom1").feature("pch1")
         .set("message", "The height of the elliptical cavity (el_h) must be larger than el_c and smaller than 2*el_c");
    model.component("comp1").geom("geom1").run("pch1");
    model.component("comp1").geom("geom1").create("par1", "Partition");
    model.component("comp1").geom("geom1").feature("par1").selection("input").set("elp1");
    model.component("comp1").geom("geom1").feature("par1").set("partitionwith", "workplane");
    model.component("comp1").geom("geom1").run("par1");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(3);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("par1", 2);
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").create("elseif4", "ElseIf");
    model.component("comp1").geom("geom1").feature("elseif4").label("Is spherical");
    model.component("comp1").geom("geom1").feature("elseif4").set("condition", "cavity==4");
    model.component("comp1").geom("geom1").run("elseif4");
    model.component("comp1").geom("geom1").create("sph2", "Sphere");
    model.component("comp1").geom("geom1").feature("sph2").set("r", "sp_r");
    model.component("comp1").geom("geom1").feature("sph2").set("pos", new String[]{"0", "0", "sp_h-sp_r"});
    model.component("comp1").geom("geom1").run("sph2");
    model.component("comp1").geom("geom1").create("pch2", "ParameterCheck");
    model.component("comp1").geom("geom1").feature("pch2").set("condition", "(sp_h>2*sp_r)||(sp_h<sp_r)");
    model.component("comp1").geom("geom1").feature("pch2")
         .set("message", "The height of the spherical cavity (sp_h) must be larger than sp_r and smaller than 2*sp_r");
    model.component("comp1").geom("geom1").run("pch2");
    model.component("comp1").geom("geom1").create("par2", "Partition");
    model.component("comp1").geom("geom1").feature("par2").selection("input").set("sph2");
    model.component("comp1").geom("geom1").feature("par2").set("partitionwith", "workplane");
    model.component("comp1").geom("geom1").run("par2");
    model.component("comp1").geom("geom1").create("del2", "Delete");
    model.component("comp1").geom("geom1").feature("del2").selection("input").init(3);
    model.component("comp1").geom("geom1").feature("del2").selection("input").set("par2", 2);
    model.component("comp1").geom("geom1").run("del2");
    model.component("comp1").geom("geom1").create("else1", "Else");
    model.component("comp1").geom("geom1").feature("else1").label("Is conical");
    model.component("comp1").geom("geom1").run("else1");
    model.component("comp1").geom("geom1").create("cone3", "Cone");
    model.component("comp1").geom("geom1").feature("cone3").set("specifytop", "radius");
    model.component("comp1").geom("geom1").feature("cone3").set("r", "co_rb");
    model.component("comp1").geom("geom1").feature("cone3").set("h", "co_h");
    model.component("comp1").geom("geom1").feature("cone3").set("rtop", "co_rt");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").set("r", "sqrt(x^2+y^2)");
    model.component("comp1").variable("var1").descr("r", "Radial coordinate in the xy-plane");
    model.component("comp1").variable("var1").set("theta", "atan2(y,x)");
    model.component("comp1").variable("var1").descr("theta", "Azimuthal angle in the xy-plane");
    model.component("comp1").variable("var1").set("crf", "genproj1(gop.wall1.bacc1.rpb)/genproj1(I0)");
    model.component("comp1").variable("var1").descr("crf", "Concentration ratio at the focal plane");
    model.component("comp1").variable("var1").set("qrw", "genproj2(gop.wall2.bsrc1.Qp)/(2*pi*r)");
    model.component("comp1").variable("var1").descr("qrw", "Flux distribution at the cavity side walls");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").all();
    model.component("comp1").selection("sel1").label("Cavity");
    model.component("comp1").selection().create("adj1", "Adjacent");
    model.component("comp1").selection("adj1").label("Cavity, all boundaries");
    model.component("comp1").selection("adj1").set("input", new String[]{"sel1"});
    model.component("comp1").selection().create("box1", "Box");
    model.component("comp1").selection("box1").label("Focal plane");
    model.component("comp1").selection("box1").set("entitydim", 2);
    model.component("comp1").selection("box1").set("zmin", "-eps");
    model.component("comp1").selection("box1").set("zmax", "eps");
    model.component("comp1").selection("box1").set("condition", "allvertices");
    model.component("comp1").selection().create("dif1", "Difference");
    model.component("comp1").selection("dif1").label("Cavity walls");
    model.component("comp1").selection("dif1").set("entitydim", 2);
    model.component("comp1").selection("dif1").set("add", new String[]{"adj1"});
    model.component("comp1").selection("dif1").set("subtract", new String[]{"box1"});
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("Cavity edges");
    model.component("comp1").selection("sel2").all();
    model.component("comp1").selection("sel2").geom("geom1", 3, 1, new String[]{"exterior"});
    model.component("comp1").selection("sel2").all();
    model.component("comp1").selection().create("box2", "Box");
    model.component("comp1").selection("box2").label("Cavity side edge");
    model.component("comp1").selection("box2").set("entitydim", 1);
    model.component("comp1").selection("box2").set("inputent", "selections");
    model.component("comp1").selection("box2").set("input", new String[]{"sel2"});
    model.component("comp1").selection("box2").set("xmin", 0);
    model.component("comp1").selection("box2").set("xmax", 0);
    model.component("comp1").selection("box2").set("ymin", "-eps");
    model.component("comp1").selection("box2").set("ymax", "rwall+eps");
    model.component("comp1").selection("box2").set("zmin", "-eps");
    model.component("comp1").selection("box2").set("zmax", "hwall+eps");
    model.component("comp1").selection("box2").set("condition", "allvertices");
    model.component("comp1").selection().create("com1", "Complement");
    model.component("comp1").selection("com1").label("Solar collector");
    model.component("comp1").selection("com1").set("entitydim", 2);
    model.component("comp1").selection("com1").set("input", new String[]{"adj1"});

    model.component("comp1").cpl().create("genproj1", "GeneralProjection");
    model.component("comp1").cpl("genproj1").selection().geom("geom1", 2);
    model.component("comp1").cpl("genproj1").selection().named("box1");
    model.component("comp1").cpl("genproj1").set("srcmap", new String[]{"r", "theta", "z"});
    model.component("comp1").cpl("genproj1").set("dstmap", new String[]{"r", "y"});
    model.component("comp1").cpl().create("genproj2", "GeneralProjection");
    model.component("comp1").cpl("genproj2").selection().geom("geom1", 2);
    model.component("comp1").cpl("genproj2").selection().named("dif1");
    model.component("comp1").cpl("genproj2").set("srcmap", new String[]{"r", "theta", "z"});
    model.component("comp1").cpl("genproj2").set("dstmap", new String[]{"r", "y"});

    model.component("comp1").physics("gop").selection().set();
    model.component("comp1").physics("gop").prop("MaximumSecondary").setIndex("MaximumSecondary", 0, 0);
    model.component("comp1").physics("gop").prop("IntensityComputation")
         .setIndex("IntensityComputation", "ComputePower", 0);
    model.component("comp1").physics("gop").feature("mp1").set("n_mat", "userdef");
    model.component("comp1").physics("gop").feature("mp1").set("ki_mat", "userdef");
    model.component("comp1").physics("gop").create("ill1", "IlluminatedSurface", 2);
    model.component("comp1").physics("gop").feature("ill1").selection().named("com1");
    model.component("comp1").physics("gop").feature("ill1").set("InitialPosition", "Density");
    model.component("comp1").physics("gop").feature("ill1").setIndex("Nr", "Nrel", 0);
    model.component("comp1").physics("gop").feature("ill1").set("Li", new int[]{0, 0, -1});
    model.component("comp1").physics("gop").feature("ill1").set("alpha", "alpha_collector");
    model.component("comp1").physics("gop").feature("ill1").set("FiniteSource", "SampleFromDistribution");
    model.component("comp1").physics("gop").feature("ill1").set("psim", "psim");
    model.component("comp1").physics("gop").feature("ill1").set("LimbDarkeningModel", "EmpiricalPowerLaw");
    model.component("comp1").physics("gop").feature("ill1").set("IncludeSurfaceRoughness", true);
    model.component("comp1").physics("gop").feature("ill1").set("sigmaphi", "sig");
    model.component("comp1").physics("gop").feature("ill1").set("Psrc", "A*I0");
    model.component("comp1").physics("gop").feature("ill1").set("InitialPolarizationType", "UnPolarized");
    model.component("comp1").physics("gop").create("wall1", "Wall", 2);
    model.component("comp1").physics("gop").feature("wall1").label("Focal plane");
    model.component("comp1").physics("gop").feature("wall1").selection().named("box1");
    model.component("comp1").physics("gop").feature("wall1").set("WallCondition", "Pass");
    model.component("comp1").physics("gop").feature("wall1").create("bacc1", "BoundaryAccumulator", 2);
    model.component("comp1").physics("gop").feature("wall1").feature("bacc1").set("R", "gop.Q");
    model.component("comp1").physics("gop").feature("wall1").feature("bacc1")
         .set("DependentVariableQuantity", "heatflux");
    model.component("comp1").physics("gop").create("wall2", "Wall", 2);
    model.component("comp1").physics("gop").feature("wall2").label("Cavity walls");
    model.component("comp1").physics("gop").feature("wall2").selection().named("dif1");
    model.component("comp1").physics("gop").feature("wall2").set("WallCondition", "DiffuseScattering");
    model.component("comp1").physics("gop").feature("wall2").set("alpha", "alpha_cavity");
    model.component("comp1").physics("gop").feature("wall2").create("bsrc1", "DepositedRayPowerBoundary", 2);

    model.component("comp1").mesh("mesh1").autoMeshSize(3);
    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().all();
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().named("box1");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", "maxmesh");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmin", "minmesh");

    model.study("std1").setGenPlots(false);
    model.study("std1").setGenConv(false);
    model.study("std1").feature("rtrac").set("timestepspec", "specifylength");
    model.study("std1").feature("rtrac").set("llist", "0, 1.5*f");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("ray1", "Ray");
    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").selection().named("box1");
    model.result().dataset().create("cln1", "CutLine3D");
    model.result().dataset("cln1").setIndex("genpoints", "rfp", 1, 0);
    model.result().dataset("cln1").set("snapping", "boundary");
    model.result().dataset().create("edg1", "Edge3D");
    model.result().dataset("edg1").selection().named("box2");
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").run();
    model.result("pg1").label("Flux Distribution in the Focal Plane");
    model.result("pg1").set("data", "surf1");
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "Flux distribution in the focal plane");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", "gop.wall1.bacc1.rpb");
    model.result("pg1").feature("surf1").set("colortable", "Thermal");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("Concentration Ratio in the Focal Plane");
    model.result("pg2").set("data", "cln1");
    model.result("pg2").setIndex("looplevelinput", "last", 0);
    model.result("pg2").create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg2").feature("lngr1").set("linewidth", "preference");
    model.result("pg2").feature("lngr1").set("expr", "crf");
    model.result("pg2").feature("lngr1").set("xdata", "expr");
    model.result("pg2").feature("lngr1").set("xdataexpr", "r");
    model.result("pg2").feature("lngr1").set("xdataunit", "mm");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("Flux Distribution on the Side Walls");
    model.result("pg3").set("data", "edg1");
    model.result("pg3").setIndex("looplevelinput", "last", 0);
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg3").feature("lngr1").set("linewidth", "preference");
    model.result("pg3").feature("lngr1").set("expr", "qrw");
    model.result("pg3").feature("lngr1").set("xdata", "expr");
    model.result("pg3").feature("lngr1").set("xdataexpr", "r");
    model.result("pg3").feature("lngr1").set("unit", "W/mm^2");
    model.result("pg3").run();

    model.study().create("std2");
    model.study("std2").label("Geometry");
    model.study("std2").setGenPlots(false);
    model.study("std2").setGenConv(false);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").run();
    model.result("pg4").label("Geometry");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").set("showlegends", false);
    model.result("pg4").set("titletype", "none");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("coloring", "uniform");
    model.result("pg4").feature("surf1").set("color", "gray");

    model.component("comp1").view("view1").set("showgrid", false);
    model.component("comp1").view("view1").set("showaxisorientation", false);

    model.result("pg4").run();

    model.title(null);

    model.description("");

    model.label("solar_dish_receiver_designer_embedded.mph");

    model.result("pg4").run();

    model.setExpectedComputationTime("4 \u79d2");

    model.result().report().create("rpt1", "Report");
    model.result().report("rpt1").set("filename", "user:///solar_dish_receiver_designer");
    model.result().report("rpt1").feature().create("tp1", "TitlePage");
    model.result().report("rpt1").feature("tp1")
         .set("title", "\u789f\u5f0f\u592a\u9633\u80fd\u63a5\u6536\u5668\u8bbe\u8ba1\u5668");
    model.result().report("rpt1").feature("tp1").set("includeacknowledgment", false);
    model.result().report("rpt1").feature().create("toc1", "TableOfContents");
    model.result().report("rpt1").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec1").label("\u8f6f\u4ef6\u4fe1\u606f");
    model.result().report("rpt1").feature("sec1").feature().create("root1", "Model");
    model.result().report("rpt1").feature("sec1").feature("root1").label("\u5173\u4e8e\u8f6f\u4ef6");
    model.result().report("rpt1").feature("sec1").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec1").feature("sec1").label("\u7814\u7a76");
    model.result().report("rpt1").feature("sec1").feature("sec1").feature().create("std1", "Study");
    model.result().report("rpt1").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec2").label("\u8f93\u5165\u6570\u636e");
    model.result().report("rpt1").feature("sec2").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec2").feature("sec1").label("\u53c2\u6570");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature().create("param1", "Parameter");
    model.result().report("rpt1").feature("sec2").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec2").feature("sec2").label("\u51e0\u4f55");
    model.result().report("rpt1").feature("sec2").feature("sec2").feature().create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("pg1").set("noderef", "pg4");
    model.result().report("rpt1").feature().create("sec3", "Section");
    model.result().report("rpt1").feature("sec3").label("\u7ed3\u679c");
    model.result().report("rpt1").feature("sec3").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec1").label("\u7ed8\u56fe\u7ec4");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec1")
         .label("\u7126\u5e73\u9762\u4e0a\u5149\u901a\u91cf\u5206\u5e03");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec1").feature()
         .create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec2")
         .label("\u7126\u5e73\u9762\u4e0a\u5f84\u5411\u805a\u5149\u6bd4");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec2").feature()
         .create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec2").feature("pg1")
         .set("noderef", "pg2");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature().create("sec3", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec3")
         .label("\u4fa7\u58c1\u4e0a\u5149\u901a\u91cf\u5206\u5e03");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec3").feature()
         .create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec3").feature("pg1")
         .set("noderef", "pg3");
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("pg1").active(false);
    model.result().report("rpt1").feature("sec2").feature("sec2").feature().create("geom1", "Geometry");
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("geom1").setIndex("children", false, 0, 1);
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("geom1").setIndex("children", false, 1, 1);
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("geom1").setIndex("children", false, 2, 1);
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("geom1").setIndex("children", false, 3, 1);
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("geom1").setIndex("children", false, 4, 1);
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("geom1").setIndex("children", false, 5, 1);
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("geom1").setIndex("children", false, 6, 1);
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("geom1").setIndex("children", false, 7, 1);
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("geom1").setIndex("children", false, 8, 1);
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("geom1").setIndex("children", false, 9, 1);
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("geom1")
         .setIndex("children", false, 10, 1);
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("geom1")
         .setIndex("children", false, 11, 1);
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("geom1")
         .setIndex("children", false, 12, 1);
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("geom1")
         .setIndex("children", false, 13, 1);
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("geom1")
         .setIndex("children", false, 14, 1);
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("geom1")
         .setIndex("children", false, 15, 1);
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("geom1")
         .setIndex("children", false, 16, 1);
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("geom1")
         .setIndex("children", false, 17, 1);
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("geom1")
         .setIndex("children", false, 18, 1);
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("geom1")
         .setIndex("children", false, 19, 1);
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("geom1")
         .setIndex("children", false, 20, 1);
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("geom1")
         .setIndex("children", false, 21, 1);
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("geom1")
         .setIndex("children", false, 22, 1);
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("geom1")
         .setIndex("children", false, 23, 1);
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("geom1")
         .setIndex("children", false, 24, 1);
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("geom1")
         .setIndex("children", false, 25, 1);

    return model;
  }

  public static Model run2(Model model) {
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("geom1").set("includeunits", false);
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("geom1").set("includestats", false);

    model.title("\u789f\u5f0f\u592a\u9633\u80fd\u63a5\u6536\u5668\u8bbe\u8ba1\u5668");

    model
         .description("\u592a\u9633\u80fd\u805a\u5149\u5668/\u8154\u5f0f\u63a5\u6536\u5668\u7cfb\u7edf\u53ef\u7528\u4e8e\u5c06\u5165\u5c04\u7684\u592a\u9633\u8f90\u5c04\u805a\u7126\u5230\u4e00\u4e2a\u5c0f\u533a\u57df\uff0c\u4ece\u800c\u4ea7\u751f\u5f3a\u70c8\u7684\u70ed\u91cf\uff0c\u8fd9\u4e9b\u70ed\u91cf\u968f\u540e\u53ef\u4ee5\u8f6c\u5316\u4e3a\u7535\u80fd\u6216\u5316\u5b66\u80fd\u3002\u5728\u592a\u9633\u80fd\u70ed\u53d1\u7535\u7cfb\u7edf\u4e2d\uff0c\u4e00\u4e2a\u5e38\u89c1\u7684\u54c1\u8d28\u56e0\u6570\u662f\u805a\u5149\u6bd4\uff0c\u5373\u63a5\u6536\u5668\u8868\u9762\u6216\u7126\u5e73\u9762\u4e0a\u7684\u592a\u9633\u8f90\u5c04\u901a\u91cf\u4e0e\u73af\u5883\u592a\u9633\u8f90\u5c04\u901a\u91cf\u7684\u6bd4\u503c\u3002\n\n\u672c\u4f8b\u662f\u57fa\u4e8e\u201c\u789f\u5f0f\u592a\u9633\u80fd\u63a5\u6536\u5668\u201d\u6559\u5b66\u6a21\u578b\u7684\u53ef\u8fd0\u884c App\uff0c\u5176\u4e2d\uff0c\u5165\u5c04\u7684\u592a\u9633\u8f90\u5c04\u88ab\u629b\u7269\u9762\u5929\u7ebf\u53cd\u5c04\uff0c\u800c\u805a\u96c6\u7684\u592a\u9633\u8f90\u5c04\u88ab\u6536\u96c6\u5728\u4e00\u4e2a\u5c0f\u8154\u4f53\u4e2d\u3002\u60a8\u4e00\u5171\u53ef\u4ee5\u7814\u7a76\u516d\u79cd\u4e0d\u540c\u7684\u53c2\u6570\u5316\u8154\u4f53\u51e0\u4f55\uff1a\u5706\u67f1\u5f62\u3001\u5706\u9876\u5f62\u3001\u5f02\u8d28\u5706\u9525\u5f62\u3001\u692d\u5706\u5f62\u3001\u7403\u5f62\u548c\u5706\u9525\u5f62\u3002\u6b64\u5916\uff0c\u8fd8\u53ef\u4ee5\u8003\u8651\u591a\u79cd\u4e0d\u540c\u7684\u6270\u52a8\u7c7b\u578b\uff0c\u5305\u62ec\u592a\u9633\u4e34\u8fb9\u660f\u6697\u548c\u8868\u9762\u7c97\u7cd9\u5ea6\u3002\u5bf9\u4e8e\u6bcf\u4e2a\u8154\u4f53\u51e0\u4f55\uff0c\u5185\u7f6e\u7ed8\u56fe\u90fd\u4f1a\u663e\u793a\u7126\u5e73\u9762\u4e0a\u7684\u901a\u91cf\u5206\u5e03\u548c\u805a\u5149\u6bd4\uff0c\u4ee5\u53ca\u8154\u4f53\u5185\u8868\u9762\u7684\u5165\u5c04\u901a\u91cf\u3002\n\n\u60a8\u53ef\u4ee5\u9605\u8bfb\u4ee5\u4e0b\u76f8\u5173\u535a\u5ba2\u6587\u7ae0\uff0c\u4e86\u89e3\u6709\u5173\u672c\u4f8b\u7684\u66f4\u591a\u4fe1\u606f\uff1a\u201c\u9ad8\u6548\u4f18\u5316\u789f\u5f0f\u592a\u9633\u80fd\u63a5\u6536\u5668\u8bbe\u8ba1\u201d\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("solar_dish_receiver_designer.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
