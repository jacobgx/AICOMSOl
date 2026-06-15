/*
 * scatterer_on_substrate.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:41 by COMSOL 6.3.0.290. */
public class scatterer_on_substrate {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Wave_Optics_Module\\Optical_Scattering");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("ewfd", "ElectromagneticWavesFrequencyDomain", "geom1");
    model.component("comp1").physics().create("ewfd2", "ElectromagneticWavesFrequencyDomain", "geom1");

    model.study().create("std1");

    model.param().set("w", "750[nm]");
    model.param().descr("w", "\u7269\u7406\u51e0\u4f55\u5bbd\u5ea6");
    model.param().set("t_pml", "150[nm]");
    model.param().descr("t_pml", "PML \u539a\u5ea6");
    model.param().set("h_air", "400[nm]");
    model.param().descr("h_air", "\u7a7a\u6c14\u57df\u9ad8\u5ea6");
    model.param().set("h_subs", "250[nm]");
    model.param().descr("h_subs", "\u57fa\u677f\u57df\u9ad8\u5ea6");
    model.param().set("na", "1");
    model.param().descr("na", "\u6298\u5c04\u7387\uff0c\u7a7a\u6c14");
    model.param().set("nb", "1.5");
    model.param().descr("nb", "\u6298\u5c04\u7387\uff0c\u57fa\u677f");
    model.param().set("lda0", "500[nm]");
    model.param().descr("lda0", "\u6ce2\u957f");
    model.param().set("phi", "0");
    model.param().descr("phi", "\u4e24\u79cd\u4ecb\u8d28\u4e2d\u7684\u5165\u5c04\u65b9\u4f4d\u89d2");
    model.param().set("theta", "0");
    model.param().descr("theta", "\u7a7a\u6c14\u4e2d\u7684\u5165\u5c04\u6781\u89d2");
    model.param().set("thetab", "asin(na/nb*sin(theta))");
    model.param().descr("thetab", "\u57fa\u677f\u4e2d\u7684\u6781\u89d2");
    model.param().set("I0", "1[MW/m^2]");
    model.param().descr("I0", "\u5165\u5c04\u573a\u5f3a\u5ea6");
    model.param().set("P", "I0*w^2*cos(theta)");
    model.param().descr("P", "\u7aef\u53e3\u529f\u7387");

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "scatterer_on_substrate.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"w+2*t_pml", "w+2*t_pml", "1"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", "h_air+t_pml", 2);
    model.component("comp1").geom("geom1").feature("blk1").set("base", "center");
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new String[]{"0", "0", "(h_air+t_pml)/2"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("layer", "t_pml", 0);
    model.component("comp1").geom("geom1").feature("blk1").set("layerleft", true);
    model.component("comp1").geom("geom1").feature("blk1").set("layerright", true);
    model.component("comp1").geom("geom1").feature("blk1").set("layerfront", true);
    model.component("comp1").geom("geom1").feature("blk1").set("layerback", true);
    model.component("comp1").geom("geom1").feature("blk1").set("layerbottom", false);
    model.component("comp1").geom("geom1").feature("blk1").set("layertop", true);
    model.component("comp1").geom("geom1").feature().duplicate("blk2", "blk1");
    model.component("comp1").geom("geom1").feature("blk2").setIndex("size", "h_subs+t_pml", 2);
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new String[]{"0", "0", "-(h_subs+t_pml)/2"});
    model.component("comp1").geom("geom1").feature("blk2").set("layerbottom", true);
    model.component("comp1").geom("geom1").feature("blk2").set("layertop", false);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").label("\u7269\u7406\u57df");
    model.component("comp1").selection("sel1").set(18, 19, 25);
    model.component("comp1").selection().create("com1", "Complement");
    model.component("comp1").selection("com1").label("PML \u57df");
    model.component("comp1").selection("com1").set("input", new String[]{"sel1"});
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u7eb3\u7c73\u9897\u7c92");
    model.component("comp1").selection("sel2").set(25);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u7eb3\u7c73\u9897\u7c92\u8868\u9762");
    model.component("comp1").selection("sel3").set(25);
    model.component("comp1").selection("sel3").geom("geom1", 3, 2, new String[]{"exterior"});
    model.component("comp1").selection("sel3").set(25);

    model.component("comp1").coordSystem().create("pml1", "PML");
    model.component("comp1").coordSystem("pml1").selection().named("com1");
    model.component("comp1").coordSystem("pml1").set("wavelengthSource", "ewfd2");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").selection().geom("geom1", 3);
    model.component("comp1").variable("var1").selection().named("com1");
    model.component("comp1").variable("var1").set("ewfd.Ex", "0");
    model.component("comp1").variable("var1").set("ewfd.Ey", "0");
    model.component("comp1").variable("var1").set("ewfd.Ez", "0");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u7a7a\u6c14");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").set("n", new String[]{"na"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u57fa\u677f");
    model.component("comp1").material("mat2").selection()
         .set(1, 2, 5, 6, 9, 10, 13, 14, 17, 18, 21, 22, 26, 27, 30, 31, 34, 35);
    model.component("comp1").material("mat2").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").set("n", new String[]{"nb"});
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "\u6298\u5c04\u7387");
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").func()
         .create("int2", "Interpolation");
    model.component("comp1").material("mat3")
         .label("Au (Gold) (Rakic et al. 1998: Brendel-Bormann model; n,k 0.248-6.20 um)");
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").func("int1").set("funcname", "nr");
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").func("int1")
         .set("table", new String[][]{{"2.4797e-01", "1.4943e+00"}, 
         {"2.5201e-01", "1.5158e+00"}, 
         {"2.5612e-01", "1.5382e+00"}, 
         {"2.6030e-01", "1.5611e+00"}, 
         {"2.6454e-01", "1.5844e+00"}, 
         {"2.6886e-01", "1.6078e+00"}, 
         {"2.7324e-01", "1.6306e+00"}, 
         {"2.7770e-01", "1.6527e+00"}, 
         {"2.8222e-01", "1.6733e+00"}, 
         {"2.8683e-01", "1.6921e+00"}, 
         {"2.9150e-01", "1.7085e+00"}, 
         {"2.9626e-01", "1.7220e+00"}, 
         {"3.0109e-01", "1.7322e+00"}, 
         {"3.0600e-01", "1.7387e+00"}, 
         {"3.1099e-01", "1.7411e+00"}, 
         {"3.1606e-01", "1.7391e+00"}, 
         {"3.2121e-01", "1.7327e+00"}, 
         {"3.2645e-01", "1.7219e+00"}, 
         {"3.3177e-01", "1.7071e+00"}, 
         {"3.3718e-01", "1.6887e+00"}, 
         {"3.4268e-01", "1.6677e+00"}, 
         {"3.4827e-01", "1.6452e+00"}, 
         {"3.5395e-01", "1.6224e+00"}, 
         {"3.5972e-01", "1.6006e+00"}, 
         {"3.6559e-01", "1.5810e+00"}, 
         {"3.7155e-01", "1.5645e+00"}, 
         {"3.7761e-01", "1.5515e+00"}, 
         {"3.8377e-01", "1.5419e+00"}, 
         {"3.9002e-01", "1.5351e+00"}, 
         {"3.9638e-01", "1.5301e+00"}, 
         {"4.0285e-01", "1.5255e+00"}, 
         {"4.0942e-01", "1.5196e+00"}, 
         {"4.1609e-01", "1.5107e+00"}, 
         {"4.2288e-01", "1.4971e+00"}, 
         {"4.2977e-01", "1.4771e+00"}, 
         {"4.3678e-01", "1.4493e+00"}, 
         {"4.4390e-01", "1.4126e+00"}, 
         {"4.5114e-01", "1.3661e+00"}, 
         {"4.5850e-01", "1.3095e+00"}, 
         {"4.6598e-01", "1.2427e+00"}, 
         {"4.7358e-01", "1.1664e+00"}, 
         {"4.8130e-01", "1.0821e+00"}, 
         {"4.8915e-01", "9.9182e-01"}, 
         {"4.9712e-01", "8.9849e-01"}, 
         {"5.0523e-01", "8.0543e-01"}, 
         {"5.1347e-01", "7.1590e-01"}, 
         {"5.2184e-01", "6.3260e-01"}, 
         {"5.3035e-01", "5.5731e-01"}, 
         {"5.3900e-01", "4.9085e-01"}, 
         {"5.4779e-01", "4.3326e-01"}, 
         {"5.5672e-01", "3.8405e-01"}, 
         {"5.6580e-01", "3.4242e-01"}, 
         {"5.7503e-01", "3.0751e-01"}, 
         {"5.8440e-01", "2.7843e-01"}, 
         {"5.9393e-01", "2.5437e-01"}, 
         {"6.0362e-01", "2.3457e-01"}, 
         {"6.1346e-01", "2.1841e-01"}, 
         {"6.2346e-01", "2.0533e-01"}, 
         {"6.3363e-01", "1.9487e-01"}, 
         {"6.4396e-01", "1.8664e-01"}, 
         {"6.5446e-01", "1.8030e-01"}, 
         {"6.6514e-01", "1.7558e-01"}, 
         {"6.7598e-01", "1.7227e-01"}, 
         {"6.8701e-01", "1.7016e-01"}, 
         {"6.9821e-01", "1.6911e-01"}, 
         {"7.0959e-01", "1.6897e-01"}, 
         {"7.2117e-01", "1.6966e-01"}, 
         {"7.3292e-01", "1.7107e-01"}, 
         {"7.4488e-01", "1.7313e-01"}, 
         {"7.5702e-01", "1.7577e-01"}, 
         {"7.6937e-01", "1.7895e-01"}, 
         {"7.8191e-01", "1.8262e-01"}, 
         {"7.9466e-01", "1.8675e-01"}, 
         {"8.0762e-01", "1.9129e-01"}, 
         {"8.2079e-01", "1.9621e-01"}, 
         {"8.3418e-01", "2.0151e-01"}, 
         {"8.4778e-01", "2.0715e-01"}, 
         {"8.6160e-01", "2.1311e-01"}, 
         {"8.7565e-01", "2.1938e-01"}, 
         {"8.8993e-01", "2.2594e-01"}, 
         {"9.0445e-01", "2.3278e-01"}, 
         {"9.1919e-01", "2.3989e-01"}, 
         {"9.3418e-01", "2.4725e-01"}, 
         {"9.4942e-01", "2.5486e-01"}, 
         {"9.6490e-01", "2.6271e-01"}, 
         {"9.8063e-01", "2.7079e-01"}, 
         {"9.9662e-01", "2.7909e-01"}, 
         {"1.0129e+00", "2.8761e-01"}, 
         {"1.0294e+00", "2.9634e-01"}, 
         {"1.0462e+00", "3.0528e-01"}, 
         {"1.0632e+00", "3.1442e-01"}, 
         {"1.0806e+00", "3.2376e-01"}, 
         {"1.0982e+00", "3.3329e-01"}, 
         {"1.1161e+00", "3.4302e-01"}, 
         {"1.1343e+00", "3.5293e-01"}, 
         {"1.1528e+00", "3.6304e-01"}, 
         {"1.1716e+00", "3.7334e-01"}, 
         {"1.1907e+00", "3.8382e-01"}, 
         {"1.2101e+00", "3.9449e-01"}, 
         {"1.2299e+00", "4.0535e-01"}, 
         {"1.2499e+00", "4.1641e-01"}, 
         {"1.2703e+00", "4.2765e-01"}, 
         {"1.2910e+00", "4.3909e-01"}, 
         {"1.3121e+00", "4.5072e-01"}, 
         {"1.3335e+00", "4.6256e-01"}, 
         {"1.3552e+00", "4.7459e-01"}, 
         {"1.3773e+00", "4.8684e-01"}, 
         {"1.3998e+00", "4.9930e-01"}, 
         {"1.4226e+00", "5.1198e-01"}, 
         {"1.4458e+00", "5.2488e-01"}, 
         {"1.4694e+00", "5.3801e-01"}, 
         {"1.4933e+00", "5.5138e-01"}, 
         {"1.5177e+00", "5.6499e-01"}, 
         {"1.5424e+00", "5.7885e-01"}, 
         {"1.5676e+00", "5.9297e-01"}, 
         {"1.5931e+00", "6.0736e-01"}, 
         {"1.6191e+00", "6.2203e-01"}, 
         {"1.6455e+00", "6.3699e-01"}, 
         {"1.6723e+00", "6.5224e-01"}, 
         {"1.6996e+00", "6.6780e-01"}, 
         {"1.7273e+00", "6.8368e-01"}, 
         {"1.7555e+00", "6.9989e-01"}, 
         {"1.7841e+00", "7.1645e-01"}, 
         {"1.8132e+00", "7.3336e-01"}, 
         {"1.8428e+00", "7.5065e-01"}, 
         {"1.8728e+00", "7.6831e-01"}, 
         {"1.9034e+00", "7.8638e-01"}, 
         {"1.9344e+00", "8.0486e-01"}, 
         {"1.9660e+00", "8.2377e-01"}, 
         {"1.9980e+00", "8.4313e-01"}, 
         {"2.0306e+00", "8.6294e-01"}, 
         {"2.0637e+00", "8.8324e-01"}, 
         {"2.0974e+00", "9.0404e-01"}, 
         {"2.1316e+00", "9.2535e-01"}, 
         {"2.1663e+00", "9.4720e-01"}, 
         {"2.2016e+00", "9.6961e-01"}, 
         {"2.2375e+00", "9.9259e-01"}, 
         {"2.2740e+00", "1.0162e+00"}, 
         {"2.3111e+00", "1.0404e+00"}, 
         {"2.3488e+00", "1.0652e+00"}, 
         {"2.3871e+00", "1.0907e+00"}, 
         {"2.4260e+00", "1.1169e+00"}, 
         {"2.4656e+00", "1.1438e+00"}, 
         {"2.5058e+00", "1.1715e+00"}, 
         {"2.5467e+00", "1.1999e+00"}, 
         {"2.5882e+00", "1.2291e+00"}, 
         {"2.6304e+00", "1.2592e+00"}, 
         {"2.6733e+00", "1.2901e+00"}, 
         {"2.7169e+00", "1.3219e+00"}, 
         {"2.7612e+00", "1.3545e+00"}, 
         {"2.8062e+00", "1.3882e+00"}, 
         {"2.8520e+00", "1.4228e+00"}, 
         {"2.8985e+00", "1.4584e+00"}, 
         {"2.9457e+00", "1.4951e+00"}, 
         {"2.9938e+00", "1.5328e+00"}, 
         {"3.0426e+00", "1.5717e+00"}, 
         {"3.0922e+00", "1.6117e+00"}, 
         {"3.1426e+00", "1.6529e+00"}, 
         {"3.1939e+00", "1.6954e+00"}, 
         {"3.2460e+00", "1.7391e+00"}, 
         {"3.2989e+00", "1.7842e+00"}, 
         {"3.3527e+00", "1.8306e+00"}, 
         {"3.4074e+00", "1.8784e+00"}, 
         {"3.4629e+00", "1.9277e+00"}, 
         {"3.5194e+00", "1.9785e+00"}, 
         {"3.5768e+00", "2.0308e+00"}, 
         {"3.6351e+00", "2.0847e+00"}, 
         {"3.6944e+00", "2.1403e+00"}, 
         {"3.7546e+00", "2.1976e+00"}, 
         {"3.8159e+00", "2.2566e+00"}, 
         {"3.8781e+00", "2.3175e+00"}, 
         {"3.9413e+00", "2.3802e+00"}, 
         {"4.0056e+00", "2.4449e+00"}, 
         {"4.0709e+00", "2.5116e+00"}, 
         {"4.1373e+00", "2.5803e+00"}, 
         {"4.2048e+00", "2.6511e+00"}, 
         {"4.2733e+00", "2.7242e+00"}, 
         {"4.3430e+00", "2.7995e+00"}, 
         {"4.4138e+00", "2.8771e+00"}, 
         {"4.4858e+00", "2.9572e+00"}, 
         {"4.5589e+00", "3.0397e+00"}, 
         {"4.6333e+00", "3.1247e+00"}, 
         {"4.7088e+00", "3.2124e+00"}, 
         {"4.7856e+00", "3.3028e+00"}, 
         {"4.8637e+00", "3.3960e+00"}, 
         {"4.9430e+00", "3.4921e+00"}, 
         {"5.0236e+00", "3.5911e+00"}, 
         {"5.1055e+00", "3.6932e+00"}, 
         {"5.1888e+00", "3.7985e+00"}, 
         {"5.2734e+00", "3.9069e+00"}, 
         {"5.3594e+00", "4.0187e+00"}, 
         {"5.4468e+00", "4.1340e+00"}, 
         {"5.5356e+00", "4.2528e+00"}, 
         {"5.6258e+00", "4.3752e+00"}, 
         {"5.7176e+00", "4.5013e+00"}, 
         {"5.8108e+00", "4.6314e+00"}, 
         {"5.9056e+00", "4.7653e+00"}, 
         {"6.0019e+00", "4.9034e+00"}, 
         {"6.0997e+00", "5.0456e+00"}, 
         {"6.1992e+00", "5.1922e+00"}});
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").func("int1")
         .set("argunit", new String[]{"um"});
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").func("int2").set("funcname", "ni");
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").func("int2")
         .set("table", new String[][]{{"2.4797e-01", "1.9575e+00"}, 
         {"2.5201e-01", "1.9594e+00"}, 
         {"2.5612e-01", "1.9605e+00"}, 
         {"2.6030e-01", "1.9604e+00"}, 
         {"2.6454e-01", "1.9587e+00"}, 
         {"2.6886e-01", "1.9551e+00"}, 
         {"2.7324e-01", "1.9494e+00"}, 
         {"2.7770e-01", "1.9414e+00"}, 
         {"2.8222e-01", "1.9312e+00"}, 
         {"2.8683e-01", "1.9188e+00"}, 
         {"2.9150e-01", "1.9042e+00"}, 
         {"2.9626e-01", "1.8879e+00"}, 
         {"3.0109e-01", "1.8700e+00"}, 
         {"3.0600e-01", "1.8512e+00"}, 
         {"3.1099e-01", "1.8319e+00"}, 
         {"3.1606e-01", "1.8128e+00"}, 
         {"3.2121e-01", "1.7946e+00"}, 
         {"3.2645e-01", "1.7784e+00"}, 
         {"3.3177e-01", "1.7648e+00"}, 
         {"3.3718e-01", "1.7548e+00"}, 
         {"3.4268e-01", "1.7490e+00"}, 
         {"3.4827e-01", "1.7479e+00"}, 
         {"3.5395e-01", "1.7516e+00"}, 
         {"3.5972e-01", "1.7597e+00"}, 
         {"3.6559e-01", "1.7714e+00"}, 
         {"3.7155e-01", "1.7856e+00"}, 
         {"3.7761e-01", "1.8007e+00"}, 
         {"3.8377e-01", "1.8152e+00"}, 
         {"3.9002e-01", "1.8276e+00"}, 
         {"3.9638e-01", "1.8366e+00"}, 
         {"4.0285e-01", "1.8413e+00"}, 
         {"4.0942e-01", "1.8411e+00"}, 
         {"4.1609e-01", "1.8362e+00"}, 
         {"4.2288e-01", "1.8268e+00"}, 
         {"4.2977e-01", "1.8140e+00"}, 
         {"4.3678e-01", "1.7988e+00"}, 
         {"4.4390e-01", "1.7829e+00"}, 
         {"4.5114e-01", "1.7681e+00"}, 
         {"4.5850e-01", "1.7567e+00"}, 
         {"4.6598e-01", "1.7509e+00"}, 
         {"4.7358e-01", "1.7532e+00"}, 
         {"4.8130e-01", "1.7661e+00"}, 
         {"4.8915e-01", "1.7916e+00"}, 
         {"4.9712e-01", "1.8312e+00"}, 
         {"5.0523e-01", "1.8852e+00"}, 
         {"5.1347e-01", "1.9530e+00"}, 
         {"5.2184e-01", "2.0328e+00"}, 
         {"5.3035e-01", "2.1222e+00"}, 
         {"5.3900e-01", "2.2188e+00"}, 
         {"5.4779e-01", "2.3201e+00"}, 
         {"5.5672e-01", "2.4245e+00"}, 
         {"5.6580e-01", "2.5305e+00"}, 
         {"5.7503e-01", "2.6370e+00"}, 
         {"5.8440e-01", "2.7434e+00"}, 
         {"5.9393e-01", "2.8493e+00"}, 
         {"6.0362e-01", "2.9545e+00"}, 
         {"6.1346e-01", "3.0587e+00"}, 
         {"6.2346e-01", "3.1621e+00"}, 
         {"6.3363e-01", "3.2645e+00"}, 
         {"6.4396e-01", "3.3662e+00"}, 
         {"6.5446e-01", "3.4671e+00"}, 
         {"6.6514e-01", "3.5675e+00"}, 
         {"6.7598e-01", "3.6674e+00"}, 
         {"6.8701e-01", "3.7669e+00"}, 
         {"6.9821e-01", "3.8661e+00"}, 
         {"7.0959e-01", "3.9653e+00"}, 
         {"7.2117e-01", "4.0644e+00"}, 
         {"7.3292e-01", "4.1635e+00"}, 
         {"7.4488e-01", "4.2629e+00"}, 
         {"7.5702e-01", "4.3625e+00"}, 
         {"7.6937e-01", "4.4624e+00"}, 
         {"7.8191e-01", "4.5627e+00"}, 
         {"7.9466e-01", "4.6635e+00"}, 
         {"8.0762e-01", "4.7649e+00"}, 
         {"8.2079e-01", "4.8669e+00"}, 
         {"8.3418e-01", "4.9695e+00"}, 
         {"8.4778e-01", "5.0729e+00"}, 
         {"8.6160e-01", "5.1770e+00"}, 
         {"8.7565e-01", "5.2820e+00"}, 
         {"8.8993e-01", "5.3878e+00"}, 
         {"9.0445e-01", "5.4946e+00"}, 
         {"9.1919e-01", "5.6024e+00"}, 
         {"9.3418e-01", "5.7111e+00"}, 
         {"9.4942e-01", "5.8210e+00"}, 
         {"9.6490e-01", "5.9319e+00"}, 
         {"9.8063e-01", "6.0440e+00"}, 
         {"9.9662e-01", "6.1573e+00"}, 
         {"1.0129e+00", "6.2718e+00"}, 
         {"1.0294e+00", "6.3875e+00"}, 
         {"1.0462e+00", "6.5046e+00"}, 
         {"1.0632e+00", "6.6231e+00"}, 
         {"1.0806e+00", "6.7429e+00"}, 
         {"1.0982e+00", "6.8642e+00"}, 
         {"1.1161e+00", "6.9869e+00"}, 
         {"1.1343e+00", "7.1112e+00"}, 
         {"1.1528e+00", "7.2370e+00"}, 
         {"1.1716e+00", "7.3644e+00"}, 
         {"1.1907e+00", "7.4935e+00"}, 
         {"1.2101e+00", "7.6242e+00"}, 
         {"1.2299e+00", "7.7566e+00"}, 
         {"1.2499e+00", "7.8908e+00"}, 
         {"1.2703e+00", "8.0268e+00"}, 
         {"1.2910e+00", "8.1646e+00"}, 
         {"1.3121e+00", "8.3044e+00"}, 
         {"1.3335e+00", "8.4460e+00"}, 
         {"1.3552e+00", "8.5896e+00"}, 
         {"1.3773e+00", "8.7353e+00"}, 
         {"1.3998e+00", "8.8829e+00"}, 
         {"1.4226e+00", "9.0327e+00"}, 
         {"1.4458e+00", "9.1847e+00"}, 
         {"1.4694e+00", "9.3388e+00"}, 
         {"1.4933e+00", "9.4951e+00"}, 
         {"1.5177e+00", "9.6538e+00"}, 
         {"1.5424e+00", "9.8147e+00"}, 
         {"1.5676e+00", "9.9780e+00"}, 
         {"1.5931e+00", "1.0144e+01"}, 
         {"1.6191e+00", "1.0312e+01"}, 
         {"1.6455e+00", "1.0483e+01"}, 
         {"1.6723e+00", "1.0656e+01"}, 
         {"1.6996e+00", "1.0832e+01"}, 
         {"1.7273e+00", "1.1010e+01"}, 
         {"1.7555e+00", "1.1192e+01"}, 
         {"1.7841e+00", "1.1376e+01"}, 
         {"1.8132e+00", "1.1563e+01"}, 
         {"1.8428e+00", "1.1752e+01"}, 
         {"1.8728e+00", "1.1945e+01"}, 
         {"1.9034e+00", "1.2140e+01"}, 
         {"1.9344e+00", "1.2339e+01"}, 
         {"1.9660e+00", "1.2541e+01"}, 
         {"1.9980e+00", "1.2745e+01"}, 
         {"2.0306e+00", "1.2953e+01"}, 
         {"2.0637e+00", "1.3164e+01"}, 
         {"2.0974e+00", "1.3379e+01"}, 
         {"2.1316e+00", "1.3597e+01"}, 
         {"2.1663e+00", "1.3818e+01"}, 
         {"2.2016e+00", "1.4042e+01"}, 
         {"2.2375e+00", "1.4271e+01"}, 
         {"2.2740e+00", "1.4502e+01"}, 
         {"2.3111e+00", "1.4738e+01"}, 
         {"2.3488e+00", "1.4977e+01"}, 
         {"2.3871e+00", "1.5219e+01"}, 
         {"2.4260e+00", "1.5466e+01"}, 
         {"2.4656e+00", "1.5717e+01"}, 
         {"2.5058e+00", "1.5971e+01"}, 
         {"2.5467e+00", "1.6229e+01"}, 
         {"2.5882e+00", "1.6492e+01"}, 
         {"2.6304e+00", "1.6758e+01"}, 
         {"2.6733e+00", "1.7029e+01"}, 
         {"2.7169e+00", "1.7304e+01"}, 
         {"2.7612e+00", "1.7584e+01"}, 
         {"2.8062e+00", "1.7867e+01"}, 
         {"2.8520e+00", "1.8156e+01"}, 
         {"2.8985e+00", "1.8448e+01"}, 
         {"2.9457e+00", "1.8746e+01"}, 
         {"2.9938e+00", "1.9048e+01"}, 
         {"3.0426e+00", "1.9354e+01"}, 
         {"3.0922e+00", "1.9666e+01"}, 
         {"3.1426e+00", "1.9982e+01"}, 
         {"3.1939e+00", "2.0304e+01"}, 
         {"3.2460e+00", "2.0630e+01"}, 
         {"3.2989e+00", "2.0962e+01"}, 
         {"3.3527e+00", "2.1299e+01"}, 
         {"3.4074e+00", "2.1640e+01"}, 
         {"3.4629e+00", "2.1988e+01"}, 
         {"3.5194e+00", "2.2340e+01"}, 
         {"3.5768e+00", "2.2699e+01"}, 
         {"3.6351e+00", "2.3062e+01"}, 
         {"3.6944e+00", "2.3432e+01"}, 
         {"3.7546e+00", "2.3807e+01"}, 
         {"3.8159e+00", "2.4188e+01"}, 
         {"3.8781e+00", "2.4575e+01"}, 
         {"3.9413e+00", "2.4967e+01"}, 
         {"4.0056e+00", "2.5366e+01"}, 
         {"4.0709e+00", "2.5771e+01"}, 
         {"4.1373e+00", "2.6182e+01"}, 
         {"4.2048e+00", "2.6600e+01"}, 
         {"4.2733e+00", "2.7023e+01"}, 
         {"4.3430e+00", "2.7454e+01"}, 
         {"4.4138e+00", "2.7890e+01"}, 
         {"4.4858e+00", "2.8334e+01"}, 
         {"4.5589e+00", "2.8784e+01"}, 
         {"4.6333e+00", "2.9241e+01"}, 
         {"4.7088e+00", "2.9705e+01"}, 
         {"4.7856e+00", "3.0176e+01"}, 
         {"4.8637e+00", "3.0653e+01"}, 
         {"4.9430e+00", "3.1139e+01"}, 
         {"5.0236e+00", "3.1631e+01"}, 
         {"5.1055e+00", "3.2130e+01"}, 
         {"5.1888e+00", "3.2637e+01"}, 
         {"5.2734e+00", "3.3152e+01"}, 
         {"5.3594e+00", "3.3674e+01"}, 
         {"5.4468e+00", "3.4204e+01"}, 
         {"5.5356e+00", "3.4741e+01"}, 
         {"5.6258e+00", "3.5287e+01"}, 
         {"5.7176e+00", "3.5840e+01"}, 
         {"5.8108e+00", "3.6401e+01"}, 
         {"5.9056e+00", "3.6971e+01"}, 
         {"6.0019e+00", "3.7548e+01"}, 
         {"6.0997e+00", "3.8134e+01"}, 
         {"6.1992e+00", "3.8728e+01"}});
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").func("int2")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").func("int2")
         .set("argunit", new String[]{"um"});
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"nr(c_const/freq)", "0", "0", "0", "nr(c_const/freq)", "0", "0", "0", "nr(c_const/freq)"});
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex")
         .set("ki", new String[]{"ni(c_const/freq)", "0", "0", "0", "ni(c_const/freq)", "0", "0", "0", "ni(c_const/freq)"});
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").addInput("frequency");
    model.component("comp1").material("mat3").selection().named("sel2");

    model.component("comp1").physics("ewfd").selection().named("sel1");
    model.component("comp1").physics("ewfd").create("ps1", "PeriodicStructure", 3);
    model.component("comp1").physics("ewfd").feature("ps1").set("Pin", "P");
    model.component("comp1").physics("ewfd").feature("ps1").set("alpha1_inc", "theta");
    model.component("comp1").physics("ewfd").feature("ps1").set("alpha2_inc", "phi");
    model.component("comp1").physics("ewfd").feature("ps1").create("wee2", "WaveEquationElectric", 3);
    model.component("comp1").physics("ewfd").feature("ps1").feature("wee2").selection().named("sel2");
    model.component("comp1").physics("ewfd").feature("ps1").feature("wee2").set("n_mat", "userdef");
    model.component("comp1").physics("ewfd").feature("ps1").feature("wee2")
         .set("n", new String[]{"na", "0", "0", "0", "na", "0", "0", "0", "na"});
    model.component("comp1").physics("ewfd").feature("ps1").feature("wee2").set("ki_mat", "userdef");
    model.component("comp1").physics("ewfd2").prop("BackgroundField").set("SolveFor", "scatteredField");
    model.component("comp1").physics("ewfd2").prop("BackgroundField")
         .set("Eb", new String[]{"ewfd.Ex", "ewfd.Ey", "ewfd.Ez"});
    model.component("comp1").physics("ewfd2").create("csc1", "CrossSectionCalculation", 3);
    model.component("comp1").physics("ewfd2").feature("csc1").selection().named("sel2");
    model.component("comp1").physics("ewfd2").feature("csc1").set("I0", "I0");
    model.component("comp1").physics("ewfd2").prop("MeshControl").set("ResolveWaveInLossyMedia", true);

    model.study("std1").setGenPlots(false);
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "w", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "w", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "theta", 0);
    model.study("std1").feature("param").setIndex("plistarr", "0 pi/6 pi/6 pi/4", 0);
    model.study("std1").feature("param").setIndex("pname", "w", 1);
    model.study("std1").feature("param").setIndex("plistarr", "", 1);
    model.study("std1").feature("param").setIndex("punit", "m", 1);
    model.study("std1").feature("param").setIndex("pname", "w", 1);
    model.study("std1").feature("param").setIndex("plistarr", "", 1);
    model.study("std1").feature("param").setIndex("punit", "m", 1);
    model.study("std1").feature("param").setIndex("pname", "phi", 1);
    model.study("std1").feature("param").setIndex("plistarr", "0 0 pi/4 pi/4", 1);
    model.study("std1").create("wave", "Wavelength");
    model.study("std1").feature("wave").set("plist", "lda0");
    model.study("std1").feature("wave").setSolveFor("/physics/ewfd2", false);
    model.study("std1").create("wave2", "Wavelength");
    model.study("std1").feature("wave2").set("plist", "lda0");
    model.study("std1").feature("wave2").setSolveFor("/physics/ewfd", false);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().dataset("dset1").label("\u57fa\u677f");
    model.result().dataset("dset1").selection().geom("geom1", 2);
    model.result().dataset("dset1").selection().geom("geom1", 2);
    model.result().dataset("dset1").selection().set(65, 87);
    model.result().dataset().duplicate("dset3", "dset1");
    model.result().dataset("dset3").label("\u9897\u7c92");
    model.result().dataset("dset3").selection().named("sel3");
    model.result().dataset("dset2").selection().geom("geom1", 3);
    model.result().dataset("dset2").selection().named("sel1");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").label("\u80cc\u666f\u573a\uff0cy");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").set("edges", false);
    model.result("pg1").create("slc1", "Slice");
    model.result("pg1").feature("slc1").set("expr", "ewfd.Ey");
    model.result("pg1").feature("slc1").set("descr", "\u7535\u573a\uff0cy \u5206\u91cf");
    model.result("pg1").feature("slc1").set("quickxnumber", 3);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 3, 1);
    model.result("pg1").run();
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("data", "dset1");
    model.result("pg1").feature("surf1").set("expr", "1");
    model.result("pg1").feature("surf1").set("titletype", "none");
    model.result("pg1").feature("surf1").set("coloring", "uniform");
    model.result("pg1").feature("surf1").set("color", "gray");
    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").label("\u80cc\u666f\u573a\uff0c\u6a21");
    model.result("pg2").run();
    model.result("pg2").feature("slc1").set("expr", "ewfd.normE");
    model.result("pg2").feature("slc1").set("descr", "\u7535\u573a\u6a21");
    model.result("pg2").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").set("data", "dset2");
    model.result().numerical("gev1").setIndex("expr", "ewfd.Rport_1", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result("pg1").run();
    model.result().duplicate("pg3", "pg1");
    model.result("pg3").run();
    model.result("pg3").label("\u603b\u573a\uff0cy");
    model.result("pg3").run();
    model.result("pg3").feature("slc1").set("expr", "ewfd2.Ey");
    model.result("pg3").feature("slc1").set("descr", "\u7535\u573a\uff0cy \u5206\u91cf");
    model.result("pg3").run();
    model.result("pg3").create("surf2", "Surface");
    model.result("pg3").feature("surf2").set("data", "dset3");
    model.result("pg3").feature("surf2").set("expr", "1");
    model.result("pg3").feature("surf2").set("titletype", "none");
    model.result("pg3").feature("surf2").create("mtrl1", "MaterialAppearance");
    model.result("pg3").run();
    model.result("pg3").feature("surf2").feature("mtrl1").set("appearance", "custom");
    model.result("pg3").feature("surf2").feature("mtrl1").set("family", "gold");
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg4").label("\u603b\u573a\uff0c\u6a21");
    model.result("pg4").run();
    model.result("pg4").feature("slc1").set("expr", "ewfd2.normE");
    model.result("pg4").feature("slc1").set("descr", "\u7535\u573a\u6a21");
    model.result("pg4").run();
    model.result().numerical().create("gev2", "EvalGlobal");
    model.result().numerical("gev2").set("data", "dset2");
    model.result().numerical("gev2").setIndex("expr", "ewfd2.sigmaAbs", 0);
    model.result().numerical("gev2").setIndex("expr", "ewfd2.sigmaSca", 1);
    model.result().numerical("gev2").setIndex("expr", "ewfd2.sigmaExt", 2);
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u5168\u5c40\u8ba1\u7b97 2");
    model.result().numerical("gev2").set("table", "tbl2");
    model.result().numerical("gev2").setResult();
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").label("\u529f\u7387\u635f\u8017");
    model.result("pg5").run();
    model.result("pg5").feature("slc1").set("expr", "ewfd2.Qh");
    model.result("pg5").feature("slc1").set("descr", "\u603b\u529f\u8017\u5bc6\u5ea6");
    model.result("pg5").feature("slc1").set("quickplane", "xy");
    model.result("pg5").feature("slc1").set("quickzmethod", "coord");
    model.result("pg5").feature("slc1").set("quickz", "50[nm]");
    model.result("pg5").run();
    model.result("pg5").feature("surf2").active(false);
    model.result("pg5").run();
    model.result("pg4").run();

    model.title("\u57fa\u677f\u4e0a\u7684\u9897\u7c92\u6563\u5c04");

    model
         .description("\u672c\u4f8b\u901a\u8fc7\u6a21\u62df\u5e73\u9762\u7535\u78c1\u6ce2\u5165\u5c04\u5230\u4ecb\u7535\u57fa\u677f\u4e0a\u7684\u91d1\u7eb3\u7c73\u9897\u7c92\u8868\u9762\uff0c\u9488\u5bf9\u591a\u79cd\u4e0d\u540c\u5165\u5c04\u6781\u89d2\u548c\u65b9\u4f4d\u89d2\u7684\u60c5\u51b5\u8ba1\u7b97\u9897\u7c92\u7684\u5438\u6536\u548c\u6563\u5c04\u622a\u9762\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();

    model.label("scatterer_on_substrate.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
